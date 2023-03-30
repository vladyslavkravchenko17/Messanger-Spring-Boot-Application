package user.application.userapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import user.application.userapplication.model.Dialogue;
import user.application.userapplication.model.Message;
import user.application.userapplication.model.User;
import user.application.userapplication.repository.MessageRepository;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public void createNewMessage(Message message, User user, Dialogue dialogue) {
        message.setDateTime(LocalDateTime.now());
        message.setUser(user);
        message.setDialogue(dialogue);

        messageRepository.save(message);
    }
    public List<Message> getMessagesByDialogue(long dialogueId) {
        return messageRepository.findAllByDialogueId(dialogueId);
    }

    public List<Message> getMessagesByDialogueAndPage(long dialogueId, int pageNumber) {
        int pageSize = 10;
        int offset = (pageNumber - 1) * pageSize;

        return messageRepository.findMessagesByDialogueIdAndPage(dialogueId, offset, pageSize);

    }

}
