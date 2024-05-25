package fer.infsus.atk.service;

import fer.infsus.atk.DTO.FeedbackDTO;

public interface FeedbackService {
    FeedbackDTO getFeedback(Integer id);

    FeedbackDTO deleteFeedback(Integer id);

    void updateFeedback(Integer id, FeedbackDTO feedbackDTO);
}
