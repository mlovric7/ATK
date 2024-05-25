package fer.infsus.atk.controller;

import fer.infsus.atk.DTO.FeedbackDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {

    @GetMapping("/{id}")
    public FeedbackDTO getFeedback(@PathVariable int id) {
        return new FeedbackDTO();
    }

    @PutMapping("/{id}")
    public FeedbackDTO updateFeedback(@PathVariable int id, @RequestBody FeedbackDTO feedback) {
        return new FeedbackDTO();
    }

    @DeleteMapping("/{id}")
    public FeedbackDTO deleteFeedback(@PathVariable int id) {
        return new FeedbackDTO();
    }

}
