package mr.vadel.chatwhatsapp.controller;

import lombok.RequiredArgsConstructor;
import mr.vadel.chatwhatsapp.constants.ApiConstant;
import mr.vadel.chatwhatsapp.dto.MessageRequest;
import mr.vadel.chatwhatsapp.dto.MessageResponse;
import mr.vadel.chatwhatsapp.services.service.IChat;
import mr.vadel.chatwhatsapp.services.service.IMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(ApiConstant.MESSAGE_URL)
@RequiredArgsConstructor
public class MessageController {

    private final IMessage message;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveMessage(@RequestBody MessageRequest messageRequest) {
        message.saveMessage(messageRequest);
    }

    @PostMapping(value = ApiConstant.MESSAGE_URL_UPLOAD_MEDIA, consumes = ApiConstant.CONSUMES_FILE)
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadMediaMessage(@RequestParam(name = "chat-id") String chatId,
                                   @RequestParam(name = "file") MultipartFile file,
                                   Authentication authentication) {
        message.uploadMediaMessage(chatId, file, authentication);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void setMessageToSeen(@RequestParam(name = "chat-id") String chatId, Authentication authentication) {
        message.setMessageToSeen(chatId, authentication);
    }

    @GetMapping(value = ApiConstant.GET_MESSAGES)
    public ResponseEntity<List<MessageResponse>> getMessages(
            @RequestParam(name = "chat-id") String chatId) {
        return ResponseEntity.ok(message.findChatMessages(chatId));
    }

}
