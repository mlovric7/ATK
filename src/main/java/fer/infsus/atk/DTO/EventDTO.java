package fer.infsus.atk.DTO;

import fer.infsus.atk.model.Event;

import java.time.format.DateTimeFormatter;

public record EventDTO(Integer id, String start, String end, Integer numberOfSeats, String name, String description,
                       String address, OrganizerDTO organizer) {

    public static EventDTO from(Event event) {
        String start = event.getStartDate().format(DateTimeFormatter.ofPattern("dd. MM. yyyy."));
        String end = event.getEndDate().format(DateTimeFormatter.ofPattern("dd. MM. yyyy."));
        return new EventDTO(event.getId(), start, end, event.getNumberOfSeats(), event.getName(), event.getDescription(), event.getAddress(),
                new OrganizerDTO(event.getOrganizer().getId(), event.getOrganizer().getName(), event.getOrganizer().getContact()));
    }

    public static EventDTO addId(Integer id, EventDTO eventDTO) {
        return new EventDTO(id, eventDTO.start, eventDTO.end, eventDTO.numberOfSeats, eventDTO.name, eventDTO.description, eventDTO.address(),
                eventDTO.organizer);
    }
}
