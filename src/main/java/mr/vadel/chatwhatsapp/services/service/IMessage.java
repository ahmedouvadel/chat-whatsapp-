package mr.vadel.chatwhatsapp.services.service;

import mr.vadel.chatwhatsapp.dto.MessageRequest;
import mr.vadel.chatwhatsapp.dto.MessageResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IMessage {
     void saveMessage(MessageRequest messageRequest);
     List<MessageResponse> findChatMessages(String chatId);
     void setMessageToSeen(String chatId, Authentication authentication);
     void uploadMediaMessage(String chatId, MultipartFile file, Authentication authentication);

}
