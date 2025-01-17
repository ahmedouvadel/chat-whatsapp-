package mr.vadel.chatwhatsapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mr.vadel.chatwhatsapp.common.BaseAuditingEntity;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.OrderBy;
import mr.vadel.chatwhatsapp.constants.ChatConstant;
import mr.vadel.chatwhatsapp.enumm.MessageState;
import mr.vadel.chatwhatsapp.enumm.MessageType;

import static jakarta.persistence.GenerationType.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chat")
@NamedQuery(name = ChatConstant.FIND_CHAT_BY_SENDER_ID,
        query = "SELECT DISTINCT c FROM Chat c WHERE c.sender.id = :senderId OR c.recipient.id = :senderId ORDER BY c.createdDate DESC")
@NamedQuery(name = ChatConstant.FIND_CHAT_BY_SENDER_ID_AND_RECIPIENT_ID,
        query = "SELECT DISTINCT c FROM Chat c WHERE (c.sender.id = :senderId AND c.recipient.id = :recipientId) OR (c.sender.id = :recipientId AND c.recipient.id = :senderId)")
public class Chat extends BaseAuditingEntity {

    @Id
    @GeneratedValue(strategy = UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "render_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private User recipient;
    @OneToMany(mappedBy = "chat", fetch = FetchType.EAGER)
    @OrderBy("createdDate DESC")
    private List<Message> messages;

    @Transient
    public String getChatName(final String senderId) {
        if(sender.getId().equals(senderId))
            return recipient.getFirstName() + " " + recipient.getLastName();
        return recipient.getFirstName() + "" + recipient.getLastName();
    }

    @Transient
    public long getUnreadMessages(final String senderId) {
        return messages.stream().filter(message -> message.getReceiverId().equals(senderId))
                .filter(message -> MessageState.SEEN == message.getState())
                .count();
    }

    @Transient
    public String getLastMessage() {
        if(messages != null && !messages.isEmpty()){
            if (messages.get(0).getType() != MessageType.TEXT){
                return "Attachment";
            }
                return messages.get(0).getContent();
        }
        return null;
    }

    @Transient
    public LocalDateTime getLastMessageDate() {
        if(messages != null && !messages.isEmpty()){
            return messages.get(0).getCreatedDate();
        }
        return null;
    }
}
