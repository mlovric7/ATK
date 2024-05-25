package fer.infsus.atk.service.impl;

import fer.infsus.atk.DTO.UserDTO;
import fer.infsus.atk.repository.UserRepository;
import fer.infsus.atk.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(user -> new UserDTO(user.getId(), user.getUsername())).toList();
    }
}
