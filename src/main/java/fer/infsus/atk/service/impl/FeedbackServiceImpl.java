package fer.infsus.atk.service.impl;

import fer.infsus.atk.DTO.FeedbackDTO;
import fer.infsus.atk.DTO.UserDTO;
import fer.infsus.atk.model.Feedback;
import fer.infsus.atk.repository.FeedbackRepository;
import fer.infsus.atk.repository.UserRepository;
import fer.infsus.atk.service.FeedbackService;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, UserRepository userRepository) {
        this.feedbackRepository = feedbackRepository;
        this.userRepository = userRepository;
    }

    @Override
    public FeedbackDTO getFeedback(Integer id) {
        Feedback feedback = feedbackRepository.findById(id).orElse(null);
        if (feedback == null) return null; // TODO error handling
        return new FeedbackDTO(feedback.getId(), feedback.getRating(), feedback.getComment(),
                new UserDTO(feedback.getUser().getId(), feedback.getUser().getUsername()));
    }

    @Override
    public FeedbackDTO deleteFeedback(Integer id) {
        Feedback feedback = feedbackRepository.findById(id).orElse(null);
        if (feedback == null) return null;
        FeedbackDTO feedbackDTO = new FeedbackDTO(feedback.getId(), feedback.getRating(), feedback.getComment(),
                new UserDTO(feedback.getUser().getId(), feedback.getUser().getUsername()));
        feedbackRepository.deleteById(id);
        return feedbackDTO;
    }

    @Override
    public void updateFeedback(Integer id, FeedbackDTO feedbackDTO) {
        Feedback feedback = feedbackRepository.findById(id).orElse(null);
        if (feedback == null) return;
        feedback.setComment(feedbackDTO.comment());
        feedback.setRating(feedbackDTO.rating());
        feedback.setUser(userRepository.getReferenceById(feedbackDTO.user().id()));
        feedbackRepository.save(feedback);
    }
}
