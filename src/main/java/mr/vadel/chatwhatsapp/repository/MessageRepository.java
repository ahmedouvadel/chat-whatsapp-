package mr.vadel.chatwhatsapp.repository;

import mr.vadel.chatwhatsapp.constants.MessageConstant;
import mr.vadel.chatwhatsapp.entity.Chat;
import mr.vadel.chatwhatsapp.entity.Message;
import mr.vadel.chatwhatsapp.enumm.MessageState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query(name = MessageConstant.FIND_MESSAGE_BY_CHAT_ID)
    List<Message> findMessagesByChatId(@Param("chatId") String chatId);

    @Query(name = MessageConstant.SET_MESSAGES_TO_SEEN_BY_CHAT)
    @Modifying
    void setMessagesToSeenByChatId(@Param("chatId") String chatId, @Param("newState") MessageState state);
}
