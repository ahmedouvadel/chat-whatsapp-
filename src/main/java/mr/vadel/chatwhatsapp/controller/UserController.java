package mr.vadel.chatwhatsapp.controller;


import lombok.RequiredArgsConstructor;
import mr.vadel.chatwhatsapp.constants.ApiConstant;
import mr.vadel.chatwhatsapp.dto.UserResponse;
import mr.vadel.chatwhatsapp.services.service.IUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiConstant.USER_URL)
@RequiredArgsConstructor
public class UserController {

    private final IUser userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsersExceptSelf(Authentication authentication) {
        return ResponseEntity.ok(userService.getAllUsersExceptSelf(authentication));
    }


}
