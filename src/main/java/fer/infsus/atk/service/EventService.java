package fer.infsus.atk.service;

import fer.infsus.atk.DTO.EventDTO;
import fer.infsus.atk.DTO.EventDetailDTO;
import fer.infsus.atk.DTO.FeedbackDTO;

import java.util.List;

public interface EventService {
    List<EventDTO> getAllEvents();

    EventDetailDTO getEvent(Integer id);

    Integer createEvent(EventDTO event);

    void updateEvent(Integer id, EventDTO event);

    EventDTO deleteEvent(Integer id);

    FeedbackDTO addFeedback(Integer id, FeedbackDTO feedback);
}
