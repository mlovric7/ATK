package fer.infsus.atk.controller;

import fer.infsus.atk.DTO.EventDTO;
import fer.infsus.atk.DTO.EventDetailDTO;
import fer.infsus.atk.DTO.FeedbackDTO;
import fer.infsus.atk.model.Event;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/event")
public class EventController {

    @GetMapping
    public List<EventDTO> getEvents() {
        return List.of();
    }

    @GetMapping("/{id}")
    public EventDetailDTO getEvent(@PathVariable int id) {
        return new EventDetailDTO();
    }

    @PostMapping
    public EventDTO createEvent(@RequestBody EventDTO event) {
        return new EventDTO();
    }

    @PutMapping("/{id}")
    public EventDTO updateEvent(@RequestBody EventDTO event, @PathVariable String id) {
        return new EventDTO();
    }

    @DeleteMapping("/{id}")
    public EventDTO deleteEvent(@PathVariable int id) {
        return new EventDTO();
    }

    @PostMapping("/{id}")
    public FeedbackDTO addFeedback(@PathVariable int id, @RequestBody FeedbackDTO feedback) {
        return new FeedbackDTO();
    }
}
