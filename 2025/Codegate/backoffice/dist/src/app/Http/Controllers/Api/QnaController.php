<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use Illuminate\Http\JsonResponse;
use Illuminate\Http\Request;
use App\Services\QnaService;
use Illuminate\Http\Response;
use Illuminate\Support\Facades\Response as fResponse;
use Symfony\Component\HttpFoundation\BinaryFileResponse;

class QnaController extends Controller
{
    public function __construct(QnaService $qnaService)
    {
        $this->qnaService = $qnaService;
    }

    public function create(Request $request): JsonResponse
    {
        if ($request->hasFile('qna_file')) {
            $fileObj = $request->file('qna_file');
        } else {
            $fileObj = null;
        }

        $result = $this->qnaService->create($request->user(), $request->only('title', 'password', 'content'), $fileObj);
        if (!$result) {
            return response()->json(["data" => ["msg" => "Something wrong", "data" => ""]], 400)
                ->header('Content-Type', 'application/json; charset=UTF-8');
        }

        return response()->json(["data" => ["msg" => "Successfully", "data" => ""]])
            ->header('Content-Type', 'application/json; charset=UTF-8');
    }

    public function getQnaList(Request $request): JsonResponse
    {
        $result = $this->qnaService->getMyQnas($request->user());
        if (!$result) {
            return response()->json(["data" => ["msg" => "QnA information not founded", "data" => $result]], 404)
                ->header('Content-Type', 'application/json; charset=UTF-8');
        }

        return response()->json(["data" => ["msg" => "Successfully", "data" => $result]])
            ->header('Content-Type', 'application/json; charset=UTF-8');
    }

    public function getQna(Request $request, string $qnaId): JsonResponse
    {
        $result = $this->qnaService->getMyQna($request->user(), $qnaId);
        if (!$result) {
            return response()->json(["data" => ["msg" => "QnA information not founded", "data" => $result]], 404)
                ->header('Content-Type', 'application/json; charset=UTF-8');
        }

        return response()->json(["data" => ["msg" => "Successfully", "data" => $result]])
            ->header('Content-Type', 'application/json; charset=UTF-8');
    }

    public function getFile(Request $request): Response|JsonResponse|BinaryFileResponse
    {
        list($filepath, $filename, $filemime) = $this->qnaService->getQnaFileDownload($request->user(), $request->only('qna_id', 'dwn_policy', 'dwn_strNm', 'dwn_strView'));
        if (!$filepath || !$filename || !$filemime) {
            return response()->noContent(404);
        }

        return response()->download($filepath, $filename, ["Content-Type" => "$filemime", "Content-Disposition" => "attachment; filename=$filename"]);
    }
}
