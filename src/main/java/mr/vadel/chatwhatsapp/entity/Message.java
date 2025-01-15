package mr.vadel.chatwhatsapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mr.vadel.chatwhatsapp.common.BaseAuditingEntity;
import mr.vadel.chatwhatsapp.enumm.MessageState;
import mr.vadel.chatwhatsapp.enumm.MessageType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "message")
public class Message extends BaseAuditingEntity {
    @Id
    @SequenceGenerator(name = "msg_seq" , sequenceName = "msg_seq" , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "msg_seq")
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String content;
    @Enumerated(EnumType.STRING)
    private MessageState state;
    @Enumerated(EnumType.STRING)
    private MessageType type;
    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;
    @Column(name = "sender_id" ,nullable = false)
    private String senderId;
    @Column(name = "receive_id" ,nullable = false)
    private String receiveId;

}