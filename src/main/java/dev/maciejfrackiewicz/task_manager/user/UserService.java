package dev.maciejfrackiewicz.task_manager.user;

import dev.maciejfrackiewicz.task_manager.user.dto.CreateUserRequest;
import dev.maciejfrackiewicz.task_manager.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse addUser(CreateUserRequest request) {
        return toResponse(userRepository.save(toEntity(request)));

    }

    public Optional<UserResponse> getUserById(UUID id) {
        return userRepository.findById(id).map(this::toResponse);

    }



    public UserResponse updateUser(CreateUserRequest request) {
        return toResponse(userRepository.save(toEntity(request)));
    }

    public void deleteUser(UUID id)
    {
        userRepository.deleteById(id);
    }


    private UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getEmail(), user.getUsername(), user.getCreatedAt());

    }

    private User toEntity(CreateUserRequest request)
    {
        return User.builder()
                .email(request.email())
                .username(request.username())
                .passwordHash(request.password())
                .build();
    }

}
