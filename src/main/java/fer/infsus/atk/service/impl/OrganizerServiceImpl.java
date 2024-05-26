package fer.infsus.atk.service.impl;

import fer.infsus.atk.DTO.OrganizerDTO;
import fer.infsus.atk.model.Organizer;
import fer.infsus.atk.repository.OrganizerRepository;
import fer.infsus.atk.service.OrganizerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizerServiceImpl implements OrganizerService {

    private final OrganizerRepository organizerRepository;

    public OrganizerServiceImpl(OrganizerRepository organizerRepository) {
        this.organizerRepository = organizerRepository;
    }

    @Override
    public List<OrganizerDTO> getAllOrganizers() {
        List<Organizer> organizers = organizerRepository.findAll();
        return organizers.stream().map(o -> new OrganizerDTO(o.getId(), o.getName(), o.getContact())).toList();
    }

    @Override
    public OrganizerDTO getOrganizer(Integer id) {
        Organizer organizer = organizerRepository.findById(id).orElse(null);
        if (organizer == null) return null; // TODO add error handling if i have enough time
        return new OrganizerDTO(organizer.getId(), organizer.getName(), organizer.getContact());
    }

    @Override
    public Integer createOrganizer(OrganizerDTO organizerDTO) {
        Organizer organizer = new Organizer();
        organizer.setContact(organizerDTO.contact());
        organizer.setName(organizerDTO.name());
        organizer = organizerRepository.save(organizer);
        return organizer.getId();
    }

    @Override
    public void updateOrganizer(Integer id, OrganizerDTO organizerDTO) {
        Organizer organizer = organizerRepository.findById(id).orElse(null);
        if (organizer == null) return; // TODO error handling
        organizer.setName(organizerDTO.name());
        organizer.setContact(organizerDTO.contact());
        organizerRepository.save(organizer);
    }

    @Override
    public OrganizerDTO deleteOrganizer(Integer id) {
        Organizer organizer = organizerRepository.findById(id).orElse(null);
        if (organizer == null) return null;
        OrganizerDTO organizerDTO = new OrganizerDTO(organizer.getId(), organizer.getName(), organizer.getContact());
        organizerRepository.delete(organizer);
        return organizerDTO;
    }
}
