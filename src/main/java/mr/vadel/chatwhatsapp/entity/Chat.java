package mr.vadel.chatwhatsapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mr.vadel.chatwhatsapp.common.BaseAuditingEntity;

import java.util.List;
import jakarta.persistence.OrderBy;

import static jakarta.persistence.GenerationType.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chat")
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
}
