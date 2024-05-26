package fer.infsus.atk.service.impl;

import fer.infsus.atk.DTO.EventDTO;
import fer.infsus.atk.DTO.EventDetailDTO;
import fer.infsus.atk.DTO.FeedbackDTO;
import fer.infsus.atk.DTO.UserDTO;
import fer.infsus.atk.model.Event;
import fer.infsus.atk.model.Feedback;
import fer.infsus.atk.repository.EventRepository;
import fer.infsus.atk.repository.FeedbackRepository;
import fer.infsus.atk.repository.OrganizerRepository;
import fer.infsus.atk.repository.UserRepository;
import fer.infsus.atk.service.EventService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final FeedbackRepository feedbackRepository;
    private final OrganizerRepository organizerRepository;
    private final UserRepository userRepository;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd. MM. yyyy.");

    public EventServiceImpl(EventRepository eventRepository, FeedbackRepository feedbackRepository, OrganizerRepository organizerRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.feedbackRepository = feedbackRepository;
        this.organizerRepository = organizerRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll().stream().map(EventDTO::from).toList();
    }

    @Override
    public EventDetailDTO getEvent(Integer id) {
        Event event = eventRepository.findById(id).orElse(null);
        if (event == null) return null; // TODO error handling
        List<FeedbackDTO> feedbackDTOs = feedbackRepository.findByEvent_Id(id).stream().map(feedback -> new FeedbackDTO(feedback.getId(), feedback.getRating(), feedback.getComment(),
                new UserDTO(feedback.getUser().getId(), feedback.getUser().getUsername()))).toList();
        return new EventDetailDTO(EventDTO.from(event), feedbackDTOs);
    }

    @Override
    public Integer createEvent(EventDTO eventDTO) {
        Event event = new Event();
        event.setName(eventDTO.name());
        event.setAddress(eventDTO.address());
        event.setNumberOfSeats(eventDTO.numberOfSeats());
        event.setDescription(eventDTO.description());
        event.setOrganizer(organizerRepository.getReferenceById(eventDTO.organizer().id()));
        event.setStartDate(LocalDate.parse(eventDTO.start(), dateTimeFormatter));
        event.setEndDate(LocalDate.parse(eventDTO.end(), dateTimeFormatter));
        event = eventRepository.save(event);
        return event.getId();
    }

    @Override
    public void updateEvent(Integer id, EventDTO eventDTO) {
        Event event = eventRepository.findById(id).orElse(null);
        if (event == null) return; // TODO error handling
        event.setName(eventDTO.name());
        event.setAddress(eventDTO.address());
        event.setNumberOfSeats(eventDTO.numberOfSeats());
        event.setDescription(eventDTO.description());
        event.setOrganizer(organizerRepository.getReferenceById(eventDTO.organizer().id()));
        event.setStartDate(LocalDate.parse(eventDTO.start(), dateTimeFormatter));
        event.setEndDate(LocalDate.parse(eventDTO.end(), dateTimeFormatter));
        eventRepository.save(event);
    }

    @Override
    public EventDTO deleteEvent(Integer id) {
        Event event = eventRepository.findById(id).orElse(null);
        if (event == null) return null; // TODO error handling
        EventDTO eventDTO = EventDTO.from(event);
        eventRepository.delete(event);
        return eventDTO;
    }

    @Override
    public Integer addFeedback(Integer id, FeedbackDTO feedbackDTO) {
        Feedback feedback = new Feedback();
        feedback.setComment(feedbackDTO.comment());
        feedback.setRating(feedbackDTO.rating());
        feedback.setUser(userRepository.getReferenceById(feedbackDTO.user().id()));
        feedback.setEvent(eventRepository.getReferenceById(id));
        feedback = feedbackRepository.save(feedback);
        return feedback.getId();
    }
}
