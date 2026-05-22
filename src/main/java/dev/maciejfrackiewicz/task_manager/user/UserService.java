package dev.maciejfrackiewicz.task_manager.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User addUser(User user) {
        return userRepository.save(user);

    }

    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);

    }



    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(UUID id)
    {
        userRepository.deleteById(id);
    }

}
