package fer.infsus.atk.controller;

import fer.infsus.atk.DTO.OrganizerDTO;
import fer.infsus.atk.service.OrganizerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizer")
public class OrganizerController {

    private final OrganizerService organizerService;

    public OrganizerController(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }

    @GetMapping
    public List<OrganizerDTO> getOrganizers() {
        return organizerService.getAllOrganizers();
    }

    @GetMapping("/{id}")
    public OrganizerDTO getOrganizer(@PathVariable Integer id) {
        return organizerService.getOrganizer(id);
    }

    @PostMapping
    public OrganizerDTO createOrganizer(@RequestBody OrganizerDTO organizerDTO) {
        Integer id = organizerService.createOrganizer(organizerDTO);
        return new OrganizerDTO(id, organizerDTO.name(), organizerDTO.contact());
    }

    @PutMapping("/{id}")
    public OrganizerDTO updateOrganizer(@RequestBody OrganizerDTO organizerDTO, @PathVariable Integer id) {
        organizerService.updateOrganizer(id, organizerDTO);
        return organizerDTO;
    }

    @DeleteMapping("/{id}")
    public OrganizerDTO deleteOrganizer(@PathVariable Integer id) {
        return organizerService.deleteOrganizer(id);
    }
}
