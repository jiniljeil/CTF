<?php

namespace App\Services;

use App\Http\Controllers\Web\PageController;
use App\Models\User;
use App\Models\Qna;
use Illuminate\Database\QueryException;
use Illuminate\Http\UploadedFile;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Validator;
use Exception;
use Illuminate\Validation\Rules\File;

class QnaService
{
    public function __construct()
    {
        $this->POLICY = ["IMAGE_DOWN", "ZIP_DOWN", "TEXT_DOWN"];
        $this->VIEW = true;
    }

    public function create(User $currentUser, array $request, ?UploadedFile $uploadedFile): bool
    {
        try {
            $validator = Validator::make($request, config('constants.VALIDATION.QNA'));

            if ($validator->fails()) {
                return false;
            }

            if (!$uploadedFile) {
                Qna::create([
                    'writer_id' => $currentUser->id,
                    'title' => $request['title'],
                    'password' => Hash::make($request['password']),
                    'content' => $request['content'],
                    'file_name' => null,
                    'file_path' => null,
                    'file_type' => null,
                    'file_size' => null,
                    'file_ext' => null,
                    'is_read' => false
                ]);
            }

            if ($uploadedFile) {
                if (!in_array($uploadedFile->getMimeType(), config('constants.FILE.MIME')) ||
                    $uploadedFile->getSize() > config('constants.FILE.SIZE') ||
                    !in_array($uploadedFile->getClientOriginalExtension(), config('constants.FILE.EXTS'))
                ) {
                    return false;
                }

                $filename = config('constants.FILE.PREFIX') . time() . "." . $uploadedFile->getClientOriginalExtension();

                $currentDate = date("Ymd", time());
                $filepath = env('FILE_STORAGE', '/var/www/storage') . "/uploads/" . $currentDate;

                if (!is_dir($filepath)) {
                    mkdir($filepath, 0755, true);
                }

                $fileinfo = [
                    "filename" => $uploadedFile->getClientOriginalName(),
                    "size" => (int)$uploadedFile->getSize(),
                    "ext" => $uploadedFile->getClientOriginalExtension(),
                ];
                $uploadedFile->move($filepath, $filename);

                if (file_exists($filepath . "/" . $filename)) {
                    Qna::create([
                        'writer_id' => $currentUser->id,
                        'title' => $request['title'],
                        'password' => Hash::make($request['password']),
                        'content' => $request['content'],
                        'file_name' => $filename,
                        'real_name' => $fileinfo['filename'],
                        'file_path' => $filepath,
                        'file_size' => $fileinfo['size'],
                        'file_ext' => $fileinfo['ext'],
                        'is_read' => false
                    ]);
                } else {
                    return false;
                }
            }
        } catch (QueryException|Exception $e) {
            if (file_exists($filepath . "/" . $filename)) unlink($filepath . "/" . $filename);
            return false;
        }

        return true;
    }

    public function getMyQnas(User $currentUser): array
    {
        try {
            $allQnas = Qna::select('id', 'writer_id', 'title', 'content', 'file_name', 'created_at')->get();
            if (!$allQnas) {
                return [];
            }

            $allQnas = $allQnas->toArray();
            if ($currentUser->role === config('constants.ROLE.USER')) {
                foreach ($allQnas as &$qna) {
                    $qna['writer_id'] = User::find($qna['writer_id'])->email;
                    if ($currentUser['email'] !== $qna['writer_id']) {
                        $qna['writer_id'] = preg_replace('/^(.{1})(.*)(@.*)$/', '$1****$3', $qna['writer_id']);
                        $qna['title'] = "비밀글입니다.";
                        $qna['content'] = "비밀글입니다.";
                        $qna['file_name'] = "N";
                    }
                    if (!$qna['file_name']) {
                        $qna['file_name'] = "N";
                    }
                }
            }
            if ($currentUser->role === config('constants.ROLE.ADMIN')) {
                foreach ($allQnas as &$qna) {
                    $qna['writer_id'] = User::find($qna['writer_id'])->email;
                    if (!$qna['file_name']) {
                        $qna['file_name'] = "N";
                    }
                }
            }
        } catch (QueryException|Exception $e) {
            return [];
        }

        return $allQnas;
    }

    public function getMyQna(User $currentUser, string $qnaId): array
    {
        try {
            $qna = Qna::where('writer_id', $currentUser->id)->where('id', $qnaId)->first();
            if (!$qna) {
                return [];
            }

            $qna = $qna->toArray();
            $qna['writer_id'] = User::find($qna['writer_id'])->email;
        } catch (QueryException|Exception $e) {
            return [];
        }

        return $qna;
    }


    public function getQnaFileDownload(User $currentUser, array $request): array
    {
        $filepath = null;
        $filename = null;
        $filemime = null;
        try {
            if ($currentUser->role === config('constants.ROLE.ADMIN')) {
                $qna = Qna::where('id', $request['qna_id'])->first();
            } else {
                $qna = Qna::where('writer_id', $currentUser->id)->where('id', $request['qna_id'])->first();
            }
            if (!$qna) {
                return [null, null, null];
            }

            $qna = $qna->toArray();

            if (!$request['dwn_strNm'] === true) {
                $request['dwn_strNm'] = $qna['file_name'];
            }

            if ($request['dwn_strNm'] === $qna['file_name']) {
                if (!file_exists($qna['file_path'] . "/" . $qna['file_name']) || is_dir($qna['file_path'] . "/" . $qna['file_name'])) return [null, null, null];

                $filepath = $qna['file_path'] . "/" . $qna['file_name'];
                $filename = $qna['file_name'];
            }

            if ($request['dwn_strNm'] !== $qna['file_name']) {
                if (!file_exists($qna['file_path'] . "/" . $request['dwn_strNm']) || is_dir($qna['file_path'] . "/" . $request['dwn_strNm'])) return [null, null, null];

                $filepath = $qna['file_path'] . "/" . $request['dwn_strNm'];
                $filename = $request['dwn_strNm'];
            }

            if (!in_array($request['dwn_policy'], $this->POLICY)) {
                $request['dwn_policy'] = "IMAGE_DOWN";
            }

            if ($request['dwn_policy'] === 'IMAGE_DOWN') {
                $filemime = 'image/png';
            }
            if ($request['dwn_policy'] === 'ETC_DOWN') {
                $filemime = 'application/octet-stream';
            }
            if ($request['dwn_policy'] === 'TEXT_DOWN') {
                $filemime = 'text/plain';
            }
        } catch (QueryException|Exception $e) {
            return [null, null, null];
        }

        // secure coding for protection from arbitrary file download
        if (strpos($filename, './') || strpos($filename, '../')) {
            $filename = $qna['file_name'];
        }

        return [$filepath, $filename, $filemime];
    }
}