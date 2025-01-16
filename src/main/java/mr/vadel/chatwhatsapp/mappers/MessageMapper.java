package mr.vadel.chatwhatsapp.mappers;

import mr.vadel.chatwhatsapp.dto.MessageResponse;
import mr.vadel.chatwhatsapp.entity.Message;
import mr.vadel.chatwhatsapp.utils.FileUtils;
import org.springframework.stereotype.Service;

@Service
public class MessageMapper {
    public MessageResponse toMessageResponse(Message message) {
        return MessageResponse.builder()
                .id(message.getId())
                .content(message.getContent())
                .senderId(message.getSenderId())
                .receiverId(message.getReceiverId())
                .state(message.getState())
                .createAt(message.getCreatedDate())
                .media(FileUtils.readFileFromLocation(message.getMediaFilePath()))
                .build();
    }
}
