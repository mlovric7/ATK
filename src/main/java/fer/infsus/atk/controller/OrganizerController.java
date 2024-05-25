package fer.infsus.atk.controller;

import fer.infsus.atk.DTO.OrganizerDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/organizer")
public class OrganizerController {

    @GetMapping
    public List<OrganizerDTO> getOrganizers() {
        return List.of();
    }

    @GetMapping("/{id}")
    public OrganizerDTO getOrganizer(@PathVariable String id) {
        return new OrganizerDTO();
    }

    @PostMapping
    public OrganizerDTO createOrganizer(@RequestBody OrganizerDTO organizerDTO) {
        return organizerDTO;
    }

    @PutMapping("/{id}")
    public OrganizerDTO updateOrganizer(@RequestBody OrganizerDTO organizerDTO, @PathVariable String id) {
        return organizerDTO;
    }

    @DeleteMapping("/{id}")
    public OrganizerDTO deleteOrganizer(@PathVariable String id) {
        return new OrganizerDTO();
    }
}
