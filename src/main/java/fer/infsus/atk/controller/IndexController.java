package fer.infsus.atk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/organizers")
    public String organizer() {
        return "organizer";
    }

    @GetMapping("/events")
    public String event() {
        return "event";
    }

    @GetMapping("/event-details/{id}")
    public String eventDetails(@PathVariable String id) {
        return "event-details";
    }

    @GetMapping("/add-event")
    public String addEvent(){
        return "add-event";
    }
}
