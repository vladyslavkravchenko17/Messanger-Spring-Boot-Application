package user.application.userapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.application.userapplication.model.Dialogue;
import user.application.userapplication.model.Message;
import user.application.userapplication.model.User;
import user.application.userapplication.repository.DialogueRepository;
import user.application.userapplication.repository.MessageRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DialogueService {

    @Autowired
    private DialogueRepository dialogueRepository;

    @Autowired
    private MessageRepository messageRepository;

    public Dialogue findBy2Users(User user1, User user2) {
        List<Dialogue> dialogues = dialogueRepository.findAllByUserId(user1.getId());
        for (Dialogue d : dialogues) {
            if (d.getUsers().contains(user2)) {
                return d;
            }
        }
        return null;
    }

    public void createNewDialogue(User user1, User user2) {
        Dialogue dialogue = new Dialogue();
        dialogue.getUsers().add(user1);
        dialogue.getUsers().add(user2);
        dialogue.setDateTime(LocalDateTime.now());

        dialogueRepository.save(dialogue);

    }

    public void sortDialogues(List<Dialogue> dialogues, User user) {
        dialogues
                .forEach(dialogue -> dialogue.setInterlocutorUserByPrincipal(user.getUsername()));
        dialogues
                .forEach(dialogue -> dialogue.setLastMessage(messageRepository.findMessageWithMaxIdByDialogueId(dialogue.getId())));

        dialogues.sort((o1, o2) -> {
            Message message1 = messageRepository.findMessageWithMaxIdByDialogueId(o1.getId());
            Message message2 = messageRepository.findMessageWithMaxIdByDialogueId(o2.getId());
            LocalDateTime o1DT;
            LocalDateTime o2DT;
            if (message1 != null) {
                o1DT = message1.getDateTime();
            } else {
                o1DT = o1.getDateTime();
            }
            if (message2 != null) {
                o2DT = message2.getDateTime();
            } else {
                o2DT = o2.getDateTime();
            }

            if (o1DT.isBefore(o2DT)) return 1;
            else if (o1DT.isAfter(o2DT)) return -1;
            else  return 0;
        });
    }

    public Dialogue getDialogueById(long id) {
        Optional<Dialogue> dialogueOptional = dialogueRepository.findById(id);
        return dialogueOptional.orElse(null);
    }

    public List<Dialogue> getUserDialogues(long userId) {
        return dialogueRepository.findAllByUserId(userId);
    }
}
