package mr.vadel.chatwhatsapp.services.service;

import mr.vadel.chatwhatsapp.dto.ChatResponse;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IChat {

    List<ChatResponse> getChatsByReceiverId(Authentication currentUser);
    String CreateChat(String senderId, String receiverId);
}
