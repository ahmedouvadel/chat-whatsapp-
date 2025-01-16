package mr.vadel.chatwhatsapp.dto;

import lombok.*;
import mr.vadel.chatwhatsapp.enumm.MessageType;
import mr.vadel.chatwhatsapp.enumm.NotificationType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {

    private String chatId;
    private String content;
    private String senderId;
    private String receiverId;
    private String chatName;
    private MessageType messageType;
    private NotificationType notificationType;
    private byte[] media;
}
