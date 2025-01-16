package mr.vadel.chatwhatsapp.services.serviceImpl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import mr.vadel.chatwhatsapp.dto.MessageRequest;
import mr.vadel.chatwhatsapp.dto.MessageResponse;
import mr.vadel.chatwhatsapp.entity.Chat;
import mr.vadel.chatwhatsapp.entity.Message;
import mr.vadel.chatwhatsapp.enumm.MessageState;
import mr.vadel.chatwhatsapp.enumm.MessageType;
import mr.vadel.chatwhatsapp.mappers.MessageMapper;
import mr.vadel.chatwhatsapp.repository.ChatRepository;
import mr.vadel.chatwhatsapp.repository.MessageRepository;
import mr.vadel.chatwhatsapp.services.service.IMessage;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements IMessage {

    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private MessageMapper messageMapper;
    private final FileService fileService;

    @Override
    public void saveMessage(MessageRequest messageRequest) {
        Chat chat = chatRepository.findById(messageRequest.getChatId()).orElseThrow(
                () -> new EntityNotFoundException("Chat not found"));

        Message message = new Message();
        message.setContent(messageRequest.getContent());
        message.setChat(chat);
        message.setSenderId(messageRequest.getSenderId());
        message.setReceiveId(messageRequest.getReceiverId());
        message.setState(MessageState.SENT);
        messageRepository.save(message);

    }

    @Override
    public List<MessageResponse> findChatMessages(String chatId) {
        return messageRepository.findMessagesByChatId(chatId).stream()
                .map(messageMapper::toMessageResponse)
                .toList();
    }

    @Override
    @Transactional
    public void setMessageToSeen(String chatId, Authentication authentication) {
        Chat chat = chatRepository.findById(chatId).orElseThrow(
                () -> new EntityNotFoundException("Chat not found"));

        //final String recipientId = getRecipientId(chat, authentication); // This line for Notfication part
        messageRepository.setMessagesToSeenByChatId(chatId, MessageState.SEEN);
    }

    @Override
    public void uploadMediaMessage(String chatId, MultipartFile file, Authentication authentication) {
        Chat chat = chatRepository.findById(chatId).orElseThrow(
                () -> new EntityNotFoundException("Chat not found"));

        final String recipientId = getRecipientId(chat, authentication);
        final String senderId = getSenderId(chat, authentication);
        final String filePath = fileService.saveFile(file, senderId);

        Message message = new Message();
        message.setChat(chat);
        message.setSenderId(senderId);
        message.setReceiveId(recipientId);
        message.setType(MessageType.IMAGE);
        message.setState(MessageState.SENT);
        message.setMediaFilePath(filePath);
        messageRepository.save(message);
    }

    private String getSenderId(Chat chat, Authentication authentication) {
        if (chat.getSender().getId().equals(authentication.getName())) {
            return chat.getSender().getId();
        }
        return chat.getRecipient().getId();
    }

    private String getRecipientId(Chat chat, Authentication authentication) {
        if (chat.getRecipient().getId().equals(authentication.getName())) {
            return chat.getRecipient().getId();
        }
        return chat.getSender().getId();
    }
}
