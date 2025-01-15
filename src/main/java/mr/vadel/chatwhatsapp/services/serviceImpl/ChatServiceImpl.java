package mr.vadel.chatwhatsapp.services.serviceImpl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import mr.vadel.chatwhatsapp.dto.ChatResponse;
import mr.vadel.chatwhatsapp.entity.Chat;
import mr.vadel.chatwhatsapp.entity.User;
import mr.vadel.chatwhatsapp.mappers.ChatMapper;
import mr.vadel.chatwhatsapp.repository.ChatRepository;
import mr.vadel.chatwhatsapp.repository.UserRepository;
import mr.vadel.chatwhatsapp.services.service.IChat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements IChat {

    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final ChatMapper chatMapper;


    @Override
    @Transactional(readOnly = true)
    public List<ChatResponse> getChatsByReceiverId(Authentication currentUser) {
        final String userId = currentUser.getName();
        return chatRepository.findChatsBySenderId(userId)
                .stream()
                .map(c -> chatMapper.toChatResponse(c , userId))
                .toList();
    }

    @Override
    public String CreateChat(String senderId, String receiverId) {
        Optional<Chat> exitingChat = chatRepository.findChatByReceiverAndSender(senderId, receiverId);
        if (exitingChat.isPresent()) {
            return exitingChat.get().getId();
        }
        User sender = userRepository.findByPublicId(senderId).orElseThrow(
                () -> new EntityNotFoundException("Sender with Id "+ senderId + " not found"));
        User receiver = userRepository.findByPublicId(receiverId).orElseThrow(
                () -> new EntityNotFoundException("receiver with Id "+ receiverId + " not found"));

        Chat chat = new Chat();
        chat.setSender(sender);
        chat.setRecipient(receiver);
        Chat savedChat = chatRepository.save(chat);
        return savedChat.getId();
    }
}
