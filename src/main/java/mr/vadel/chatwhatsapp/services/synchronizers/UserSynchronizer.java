package mr.vadel.chatwhatsapp.services.synchronizers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mr.vadel.chatwhatsapp.entity.User;
import mr.vadel.chatwhatsapp.mappers.UserMapper;
import mr.vadel.chatwhatsapp.repository.UserRepository;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserSynchronizer {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public void synchronizeWithIdp(Jwt token) {
        log.info("Synchronizing user with IDP");
        getUserEmail(token).ifPresent(userEmail -> {
            log.info("Synchronizing user having email {} ", userEmail);
            //Optional<User> optUser = userRepository.findByEmail(userEmail);
            User user = userMapper.fromTokenAttributes(token.getClaims());
            //optUser.ifPresent(value -> user.setId(optUser.get().getId()));
            userRepository.save(user);
        });
    }

    private Optional<String> getUserEmail(Jwt token) {
        Map<String, Object> claims = token.getClaims();
        if (claims.containsKey("email")) {
            return Optional.of(claims.get("email").toString());
        }
        return Optional.empty();
    }
}
