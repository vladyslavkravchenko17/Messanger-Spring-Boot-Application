package user.application.userapplication.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users_dialogue")
@Getter
@Setter
public class UserDialogue {
    @EmbeddedId
    private UserDialogueId id;
    @ManyToOne
    @MapsId("userId")
    private User user;
    @ManyToOne
    @MapsId("dialogueId")
    private Dialogue dialogue;
}

