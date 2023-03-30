package user.application.userapplication.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class UserDialogueId implements Serializable {
    private Long userId;
    private Long dialogueId;
}
