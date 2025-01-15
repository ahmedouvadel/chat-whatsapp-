package mr.vadel.chatwhatsapp.controller;

import lombok.RequiredArgsConstructor;
import mr.vadel.chatwhatsapp.common.StringResponse;
import mr.vadel.chatwhatsapp.constants.ApiConstant;
import mr.vadel.chatwhatsapp.dto.ChatResponse;
import mr.vadel.chatwhatsapp.services.service.IChat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( ApiConstant.CHAT_URL)
@RequiredArgsConstructor
public class ChatController {

    private final IChat chatService;

    @PostMapping
    public ResponseEntity<StringResponse> createChat(
            @RequestParam(name = "sender-id") String senderId,
            @RequestParam(name = "receiver-id") String receiverId) {
        final String chatId =  chatService.CreateChat(senderId, receiverId);
        StringResponse response = StringResponse.builder()
                .response(chatId)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ChatResponse>> getChatsByReceiver(Authentication authentication) {
        return ResponseEntity.ok(chatService.getChatsByReceiverId(authentication));
    }
}