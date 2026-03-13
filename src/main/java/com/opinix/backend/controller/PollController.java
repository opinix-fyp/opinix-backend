package com.opinix.backend.controller;

import com.opinix.backend.PollMapper;
import com.opinix.backend.dto.PollDto;
import com.opinix.backend.dto.PollQuestionDto;
import com.opinix.backend.model.Poll;
import com.opinix.backend.model.SentimentResult;
import com.opinix.backend.service.PollImportService;
import com.opinix.backend.service.PollSentimentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/polls")
public class PollController {
    private final PollImportService pollImportService;
    private final PollSentimentService pollSentimentService;

    public PollController(PollImportService pollImportService, PollSentimentService pollSentimentService) {
        this.pollImportService = pollImportService;
        this.pollSentimentService = pollSentimentService;
    }

    @PostMapping("/import")
    public PollDto importPoll(
            @RequestParam("file")MultipartFile file,
            @RequestParam String title,
            @RequestParam(defaultValue = "GOOGLE_FORMS") String source
        ) throws Exception {
            Poll poll = pollImportService.importFromCSV(title, source, file.getInputStream());
            return PollMapper.toDto(poll);
    }

    @PostMapping("/{pollId}/analyze")
    public SentimentResult analyzePoll(@PathVariable Long pollId){
        return pollSentimentService.analyzePoll(pollId);
    }

    //for overiding roles/types from postman
//    @PatchMapping("/{pollId}/questions/{questionId}")
//    public PollQuestionDto updateQuestionRole(
//            @PathVariable Long pollId,
//            @PathVariable Long questionId,
//            @RequestBody UpdateQuestionRoleRequest request
//    ) {
//        //if needed, do stuff
//    }

}
