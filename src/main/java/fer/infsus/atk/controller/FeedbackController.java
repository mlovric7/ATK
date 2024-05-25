package fer.infsus.atk.controller;

import fer.infsus.atk.DTO.FeedbackDTO;
import fer.infsus.atk.service.FeedbackService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping("/{id}")
    public FeedbackDTO getFeedback(@PathVariable Integer id) {
        return feedbackService.getFeedback(id);
    }

    @PutMapping("/{id}")
    public FeedbackDTO updateFeedback(@PathVariable Integer id, @RequestBody FeedbackDTO feedbackDTO) {
        feedbackService.updateFeedback(id, feedbackDTO);
        return feedbackDTO;
    }

    @DeleteMapping("/{id}")
    public FeedbackDTO deleteFeedback(@PathVariable Integer id) {
        return feedbackService.deleteFeedback(id);
    }

}
