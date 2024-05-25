package fer.infsus.atk.service;

import fer.infsus.atk.DTO.OrganizerDTO;

import java.util.List;

public interface OrganizerService {
    List<OrganizerDTO> getAllOrganizers();

    OrganizerDTO getOrganizer(Integer id);

    Integer createOrganizer(OrganizerDTO organizerDTO);

    void updateOrganizer(Integer id, OrganizerDTO organizerDTO);

    OrganizerDTO deleteOrganizer(Integer id);
}
