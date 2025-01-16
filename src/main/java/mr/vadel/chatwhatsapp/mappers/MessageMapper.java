package mr.vadel.chatwhatsapp.mappers;

import mr.vadel.chatwhatsapp.dto.MessageResponse;
import mr.vadel.chatwhatsapp.entity.Message;
import org.springframework.stereotype.Service;

@Service
public class MessageMapper {
    public MessageResponse toMessageResponse(Message message) {
        return MessageResponse.builder()
                .id(message.getId())
                .content(message.getContent())
                .senderId(message.getSenderId())
                .receiverId(message.getReceiveId())
                .state(message.getState())
                .createAt(message.getCreatedDate())
                // read media from message
                .build();
    }
}
