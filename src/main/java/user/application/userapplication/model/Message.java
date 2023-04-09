package user.application.userapplication.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Getter @Setter
@EqualsAndHashCode
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String text;
    @ElementCollection
    @CollectionTable(name = "message_files", joinColumns = @JoinColumn(name = "message_id"))
    @Column(name = "file_name")
    private List<String> files;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dialogue_id")
    private Dialogue dialogue;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime dateTime;

    public Message(String text) {
        this.text = text;
    }

    public Message() {
    }

    public String getFormattedDateTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");

        return dateTime.format(formatter);
    }

    public String getAuthorUsername() {
        return user.getUsername();
    }

    public String getAuthorAvatar(){
        return user.getAvatarImage();
    }

    public void addFile(String fileName) {
        files.add(fileName);
    }

//    public String getFormattedFileName() {
//        assert file != null;
//        return file.substring(37);
//    }
}
