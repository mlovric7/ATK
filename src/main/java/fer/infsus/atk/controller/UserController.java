package fer.infsus.atk.controller;

import fer.infsus.atk.DTO.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public List<UserDTO> getUsers(){
        return List.of();
    }
}
