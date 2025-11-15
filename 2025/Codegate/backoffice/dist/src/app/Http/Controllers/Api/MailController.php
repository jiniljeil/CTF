<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use Illuminate\Http\JsonResponse;
use Illuminate\Http\Request;
use App\Services\MailService;
use Illuminate\Http\Response;
use Twig\Environment;

class MailController extends Controller
{
    public function __construct(MailService $mailService)
    {
        $this->mailService = $mailService;
    }

    public function getMyInbox(Request $request): JsonResponse
    {
        list($receive_mails, $sent_mails) = $this->mailService->getMyInbox($request->user());
        if (!$receive_mails && !$sent_mails) {
            return response()->json(["data" => ["msg" => "Mail information not founded", "data" => ['inbox' => [], 'outbox' => []]]], 404)
                ->header('Content-Type', 'application/json; charset=UTF-8');
        }

        return response()->json(["data" => ["msg" => "Successfully", "data" => ['inbox' => $receive_mails, 'outbox' => $sent_mails]]])
            ->header('Content-Type', 'application/json; charset=UTF-8');
    }

    public function read(Request $request, string $mailId): JsonResponse
    {
        $result = $this->mailService->readMail($request->user(), $mailId);
        if (!$result) {
            return response()->json(["data" => ["msg" => "Mail information not founded", "data" => ""]], 404)
                ->header('Content-Type', 'application/json; charset=UTF-8');
        }

        return response()->json(["data" => ["msg" => "Successfully", "data" => $result]])
            ->header('Content-Type', 'application/json; charset=UTF-8');
    }

    public function sent(Request $request): JsonResponse
    {
        $result = $this->mailService->create($request->user(), $request->only('receiver_email', 'subject', 'body'));
        if (!$result) {
            return response()->json(["data" => ["msg" => "Something wrong", "data" => ""]], 400)
                ->header('Content-Type', 'application/json; charset=UTF-8');
        }

        return response()->json(["data" => ["msg" => "Successfully", "data" => ""]])
            ->header('Content-Type', 'application/json; charset=UTF-8');
    }

    public function createMailTemplate(Request $request): JsonResponse|Response
    {
        list($env, $key) = $this->mailService->generateTemplate($request->user(), $request->only('template_id', 'data'));
        if (!$env || !$key) {
            return response()->json(["data" => ["msg" => "Mail template not founded", "data" => ""]], 404)
                ->header('Content-Type', 'application/json; charset=UTF-8');
        }
        return response($env->render($key));
    }

    public function getMailTemplate(Request $request, string $templateId): JsonResponse
    {
        $result = $this->mailService->getTemplate($templateId);
        if (!$result) {
            return response()->json(["data" => ["msg" => "Mail template not founded", "data" => ""]], 404)
                ->header('Content-Type', 'application/json; charset=UTF-8');
        }

        return response()->json(["data" => ["msg" => "Successfully", "data" => $result]])
            ->header('Content-Type', 'application/json; charset=UTF-8');
    }
}
