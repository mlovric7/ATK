package fer.infsus.atk.controller;

import fer.infsus.atk.DTO.EventDTO;
import fer.infsus.atk.DTO.EventDetailDTO;
import fer.infsus.atk.DTO.FeedbackDTO;
import fer.infsus.atk.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<EventDTO> getEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public EventDetailDTO getEvent(@PathVariable Integer id) {
        return eventService.getEvent(id);
    }

    @PostMapping
    public EventDTO createEvent(@RequestBody EventDTO event) {
        Integer id = eventService.createEvent(event);
        return new EventDTO(); // TODO add all the fields
    }

    @PutMapping("/{id}")
    public EventDTO updateEvent(@RequestBody EventDTO event, @PathVariable Integer id) {
        eventService.updateEvent(id, event);
        return event;
    }

    @DeleteMapping("/{id}")
    public EventDTO deleteEvent(@PathVariable Integer id) {
        return eventService.deleteEvent(id);
    }

    @PostMapping("/{id}")
    public FeedbackDTO addFeedback(@PathVariable Integer id, @RequestBody FeedbackDTO feedback) {
        return eventService.addFeedback(id, feedback);
    }
}
