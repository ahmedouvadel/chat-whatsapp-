package mr.vadel.chatwhatsapp.repository;

import mr.vadel.chatwhatsapp.constants.ChatConstant;
import mr.vadel.chatwhatsapp.dto.ChatResponse;
import mr.vadel.chatwhatsapp.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, String> {
    @Query(name = ChatConstant.FIND_CHAT_BY_SENDER_ID)
    List<Chat> findChatsBySenderId(@Param("senderId") String userId);
    @Query(name = ChatConstant.FIND_CHAT_BY_SENDER_ID_AND_RECIPIENT_ID )
    Optional<Chat> findChatByReceiverAndSender(@Param("senderId") String senderId,@Param("recipientId") String receiverId);

}
