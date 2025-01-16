package mr.vadel.chatwhatsapp.dto;

import lombok.*;
import mr.vadel.chatwhatsapp.enumm.MessageState;
import mr.vadel.chatwhatsapp.enumm.MessageType;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageResponse {
    private Long id;
    private String content;
    private MessageType type;
    private MessageState state;
    private String senderId;
    private String receiverId;
    private LocalDateTime createAt;
    private byte[] media;
}
