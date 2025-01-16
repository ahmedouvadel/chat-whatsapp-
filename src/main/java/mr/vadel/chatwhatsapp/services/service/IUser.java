package mr.vadel.chatwhatsapp.services.service;

import mr.vadel.chatwhatsapp.dto.UserResponse;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IUser {
    List<UserResponse> getAllUsersExceptSelf(Authentication authentication);
}
