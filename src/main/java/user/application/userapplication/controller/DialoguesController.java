package user.application.userapplication.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import user.application.userapplication.model.Dialogue;
import user.application.userapplication.model.Message;
import user.application.userapplication.model.User;
import user.application.userapplication.service.DialogueService;
import user.application.userapplication.service.MessageService;
import user.application.userapplication.service.UserService;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@Api("Dialogue controller")
public class DialoguesController {

    @Autowired
    private DialogueService dialogueService;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;


    @GetMapping("/dialogues")
    @ApiOperation("Show all user's dialogues")
    public String showDialogues(@AuthenticationPrincipal User user,
                                Model model) {
        List<Dialogue> dialogues = dialogueService.getUserDialogues(user.getId());
        dialogueService.sortDialogues(dialogues, user);

        model.addAttribute("dialogues", dialogues);


        return "dialogues";
    }


    @PostMapping("/dialogues")
    @ApiOperation("Create new dialogue with another user")
    @Transactional
    public String createNewDialogue(@AuthenticationPrincipal User user,
                                    @RequestParam String username,
                                    Model model) {
        User user2 = userService.getUserByUsername(username);

        if (user2 == null) {
            model.addAttribute("error", "There is no such user :(");
            return "redirect:/dialogues";
        }

        if (user2.equals(user)) {
            model.addAttribute("error", "You cannot create dialogue with yourself!");
            return "redirect:/dialogues";
        }

        Dialogue dialogue = dialogueService.findBy2Users(user, user2);
        if (dialogue != null) {
            return "redirect:/dialogues-" + dialogue.getId();
        }

        dialogueService.createNewDialogue(user, user2);

        return "redirect:/dialogues";
    }


    @GetMapping("/dialogue-{dialogueId}")
    @ApiOperation("Show dialogue with another user")
    public String showDialogueById(@RequestParam(defaultValue = "1") int pageNumber,
                                   @AuthenticationPrincipal User user,
                                   @PathVariable long dialogueId,
                                   Model model) {
        Dialogue dialogue = dialogueService.getDialogueById(dialogueId);
        if (dialogue == null) {
            return "redirect:/dialogues";
        }
        List<Dialogue> dialogues = dialogueService.getUserDialogues(user.getId());
        if (!dialogues.contains(dialogue)) {
            return "redirect:/dialogues";
        }
        dialogue.setInterlocutorUserByPrincipal(user.getUsername());
        List<Message> messages = messageService.getMessagesByDialogueAndPage(dialogueId, pageNumber);

        model.addAttribute("messages", messages);
        model.addAttribute("dialogueId", dialogueId);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("interlocutor", dialogue.getInterlocutorUser());

        return "dialogue";
    }


    @PostMapping("/dialogue-{dialogueId}")
    @ApiOperation("Send message to the dialogue with another user")
    public String addMessage(@AuthenticationPrincipal User user,
                             @RequestParam("messageFile") MultipartFile[] messageFiles,
                             @PathVariable long dialogueId,
                             @Valid Message message,
                             BindingResult bindingResult,
                             Model model) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);

            return "redirect:/dialogue-" + dialogueId;
        }

        messageService.createNewMessage(message, user, dialogueService.getDialogueById(dialogueId), messageFiles);

        return "redirect:/dialogue-" + dialogueId;
    }
}
