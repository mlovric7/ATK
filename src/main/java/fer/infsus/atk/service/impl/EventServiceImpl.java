package fer.infsus.atk.service.impl;

import fer.infsus.atk.DTO.EventDTO;
import fer.infsus.atk.DTO.EventDetailDTO;
import fer.infsus.atk.DTO.FeedbackDTO;
import fer.infsus.atk.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    @Override
    public List<EventDTO> getAllEvents() {
        return List.of();
    }

    @Override
    public EventDetailDTO getEvent(Integer id) {
        return null;
    }

    @Override
    public Integer createEvent(EventDTO event) {
        return 0;
    }

    @Override
    public void updateEvent(Integer id, EventDTO event) {

    }

    @Override
    public EventDTO deleteEvent(Integer id) {
        return null;
    }

    @Override
    public FeedbackDTO addFeedback(Integer id, FeedbackDTO feedback) {
        return null;
    }
}
