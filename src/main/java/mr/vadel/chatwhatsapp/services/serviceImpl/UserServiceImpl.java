package mr.vadel.chatwhatsapp.services.serviceImpl;

import lombok.RequiredArgsConstructor;
import mr.vadel.chatwhatsapp.dto.UserResponse;
import mr.vadel.chatwhatsapp.mappers.UserMapper;
import mr.vadel.chatwhatsapp.repository.UserRepository;
import mr.vadel.chatwhatsapp.services.service.IUser;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUser {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public List<UserResponse> getAllUsersExceptSelf(Authentication connectedUser) {
        return userRepository.findAllUsersExpectSelf(connectedUser.getName()).stream()
                .map(userMapper::toUserResponse)
                .toList();
    }
}
