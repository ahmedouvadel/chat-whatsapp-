package mr.vadel.chatwhatsapp.services.serviceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mr.vadel.chatwhatsapp.dto.Notification;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public void sendNotification(String userId, Notification notification) {
        log.info("Sending notification to user: {} with peyload : {}", userId, notification);
        simpMessagingTemplate
                .convertAndSendToUser(userId, "/chat", notification);
    }
}
