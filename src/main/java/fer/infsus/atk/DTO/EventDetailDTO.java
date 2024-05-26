package fer.infsus.atk.DTO;

import java.util.List;

public record EventDetailDTO(EventDTO event, List<FeedbackDTO> feedbacks) {
}
