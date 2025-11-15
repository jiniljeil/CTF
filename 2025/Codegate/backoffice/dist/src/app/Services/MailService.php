<?php

namespace App\Services;

use App\Models\Mail;
use App\Models\User;
use Illuminate\Database\QueryException;
use Exception;
use Illuminate\Support\Facades\Validator;
use Twig\Environment;
use Twig\Loader\ArrayLoader;


class MailService
{
    protected string $TEMPLATE_PATH;
    protected array $OPTIONS;

    public function __construct()
    {
        $this->TEMPLATE_PATH = env("FILE_STORAGE", '/var/www/storage') . "/templates/template_%s.html";
        $this->OPTIONS = ['date' => ["YYYY-MM-DD", "MM/DD/YYYY", "YYYY.MM.DD"], "time" => ["HH:mm:ss", "HH-mm-ss", "timestamp"]];
    }

    function escapeTemplate(string $template, array $data): string
    {
        return preg_replace_callback('/\{\{\s*(\w+)\s*(?:\|\s*(\w+)\s*)?\}\}/', function ($matches) use ($data) {
            return $data[$matches[1]] ?? ($matches[2] ?? $matches[0]);
        }, $template);
    }


    public function create(User $currentUser, array $request): bool
    {
        try {
            $validator = Validator::make($request, config('constants.VALIDATION.MAIL'));

            if ($validator->fails()) {
                return false;
            }

            if ($currentUser->email === $request['receiver_email']) {
                return false;
            }

            $sender = User::where('email', $currentUser->email)->first()->id;
            $receiver = User::where('email', $request['receiver_email'])->first()->id;

            Mail::create([
                'sender_id' => $sender,
                'receiver_id' => $receiver,
                'subject' => $request['subject'],
                'body' => $request['body'],
                'is_read' => false
            ]);
        } catch (QueryException|Exception $e) {
            return false;
        }

        return true;
    }


    public function readMail(User $request, string $mailId): array
    {
        try {
            $mail = Mail::find($mailId);
            if ($mail->sender_id !== $request->id) {
                return [];
            }

            if (!$mail->is_read) $mail->update(['is_read' => true]);

            $updatedEmail = Mail::find($mailId)->first()->toArray();
            $updatedEmail['sender_id'] = User::find($updatedEmail['sender_id'])->email;
            $updatedEmail['receiver_id'] = User::find($updatedEmail['receiver_id'])->email;
        } catch (QueryException|Exception $e) {
            return [];
        }

        return $updatedEmail;
    }

    public function getMyInbox(User $currentUser): array
    {
        try {
            $receive_mails = Mail::where('receiver_id', $currentUser->id)->get();
            $sent_mails = Mail::where('sender_id', $currentUser->id)->get();

            if (!$sent_mails && !$receive_mails) {
                return [null, null];
            }
            if ($sent_mails) {
                $sent_mails = $sent_mails->toArray();

                foreach ($sent_mails as &$sent_mail) {
                    $sent_mail['sender_id'] = User::find($sent_mail['sender_id'])->email;
                    $sent_mail['receiver_id'] = User::find($sent_mail['receiver_id'])->email;
                }
            }
            if ($receive_mails) {
                $receive_mails = $receive_mails->toArray();

                foreach ($receive_mails as &$receive_mail) {
                    $receive_mail['sender_id'] = User::find($receive_mail['sender_id'])->email;
                    $receive_mail['receiver_id'] = User::find($receive_mail['receiver_id'])->email;
                }
            }
        } catch (QueryException|Exception $e) {
            return [null, null];
        }
        return [$receive_mails, $sent_mails];
    }

    public function generateTemplate(User $currentUser, array $request): array
    {
        if ($currentUser->role < config('constants.ROLE.ADMIN')) {
            return [null, null];
        }

        if (!array_key_exists('template_id', $request) || !array_key_exists('data', $request) || !preg_match('/^[0-9]$/', $request['template_id'])) {
            return [null, null];
        }

        $validator = Validator::make($request['data'], config("constants.VALIDATION.TEMPLATE_" . $request['template_id']));
        if ($validator->fails()) {
            return [null, null];
        }
        $file = sprintf($this->TEMPLATE_PATH, $request['template_id']);
        $templateKey = "mail";

        try {
            if (!file_exists($file)) {
                throw new Exception('File not found');
            }

            $templateData = file_get_contents($file);

            $templateLoader = [$templateKey => self::escapeTemplate($templateData, $request['data'])];
            $render = new Environment(new ArrayLoader($templateLoader), ['charset' => 'UTF-8']);
            return [$render, $templateKey];
        } catch (Exception $e) {
            return [null, null];
        }
    }

    public function getTemplate(string $templateId): string
    {
        try {
            if (!preg_match('/^[0-9]$/', $templateId)) {
                $templateId = 0;
            }

            $file = sprintf($this->TEMPLATE_PATH, $templateId);
            if (file_exists($file)) {
                $template = file_get_contents($file);
            } else {
                $template = file_get_contents(sprintf($this->TEMPLATE_PATH, 0));
            }

            return $template;
        } catch (Exception $e) {
            return "";
        }
    }
}