package mr.vadel.chatwhatsapp.dto;

import lombok.*;
import mr.vadel.chatwhatsapp.enumm.MessageType;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageRequest {

    private String content;
    private String senderId;
    private String receiverId;
    private MessageType type;
    private String chatId;
}
