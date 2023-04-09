package user.application.userapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import user.application.userapplication.model.Dialogue;
import user.application.userapplication.model.Message;
import user.application.userapplication.model.User;
import user.application.userapplication.repository.MessageRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class MessageService {
    @Value("${files.upload.absolute.path}")
    private String uploadPath;

    @Autowired
    private MessageRepository messageRepository;

    public void createNewMessage(Message message, User user, Dialogue dialogue, MultipartFile[] files) {
        message.setDateTime(LocalDateTime.now());
        message.setUser(user);
        message.setDialogue(dialogue);
        downloadFiles(message, files);

        messageRepository.save(message);
    }

    private void downloadFiles(Message message, MultipartFile[] files) {
        List<CompletableFuture<String>> uploadFutures = new ArrayList<>();

        for (MultipartFile file : files) {
            CompletableFuture<String> uploadFuture = CompletableFuture.supplyAsync(() -> {
                String resultFileName = ServiceUtils.uploadFile(file, uploadPath);
                if (!resultFileName.equals("")) {
                    return resultFileName;
                }
                return null;
            });
            uploadFutures.add(uploadFuture);
        }

        CompletableFuture.allOf(uploadFutures.toArray(new CompletableFuture[0]))
                .join();

        List<String> fileNames = uploadFutures.stream()
                .map(CompletableFuture::join)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        message.setFiles(fileNames);
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
