package user.application.userapplication.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Dialogue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "users_dialogue",
            joinColumns = @JoinColumn(name = "dialogue_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<>();
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime dateTime;
    @Transient
    private User interlocutorUser;
    @Transient
    private Message lastMessage;


    public void setInterlocutorUserByPrincipal(String principalUsername) {
        for (User user : users) {
            if (!user.getUsername().equals(principalUsername)) {
                interlocutorUser = user;
                break;
            }
        }
    }
}
