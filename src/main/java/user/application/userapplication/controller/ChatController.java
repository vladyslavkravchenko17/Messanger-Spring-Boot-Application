package user.application.userapplication.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import user.application.userapplication.model.Dialogue;
import user.application.userapplication.model.Message;
import user.application.userapplication.model.User;
import user.application.userapplication.service.DialogueService;
import user.application.userapplication.service.MessageService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@Api("Chat controller")
public class ChatController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private DialogueService dialogueService;


    @GetMapping("/chat")
    @ApiOperation("Show chat")
    public String mainPage(Model model,
                           @RequestParam(defaultValue = "1") int pageNumber) {
        Dialogue dialogue = dialogueService.getDialogueById(1);
        List<Message> messages = messageService.getMessagesByDialogueAndPage(dialogue.getId(), pageNumber);

        model.addAttribute("messages", messages);
        model.addAttribute("currentPage", pageNumber);
        return "chat";
    }

    @PostMapping("/chat")
    @ApiOperation("Send message to global chat")
    public String addMessage(@AuthenticationPrincipal User user,
                             @RequestParam("messageFile") MultipartFile[] messageFiles,
                             @Valid Message message,
                             BindingResult bindingResult,
                             Model model) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);

            return "redirect:/chat";
        }

        messageService.createNewMessage(message, user, dialogueService.getDialogueById(1), messageFiles);

        return "redirect:/chat";
    }

}
