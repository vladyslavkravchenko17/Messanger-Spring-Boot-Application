package user.application.userapplication.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import user.application.userapplication.model.Dialogue;
import user.application.userapplication.model.User;
import user.application.userapplication.service.DialogueService;
import user.application.userapplication.service.UserService;

@Controller
@Api("User controller")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private DialogueService dialogueService;

    @GetMapping("/user-{username}")
    @ApiOperation("Show user's profile")
    public String userProfile(@PathVariable String username,
                              Model model) {
        User user = userService.getUserByUsername(username);
        model.addAttribute("user", user);
        return "userProfile";
    }

    @PostMapping("/user-{username}")
    @ApiOperation("Go to dialogue with user")
    public String goToChatWithUser(@PathVariable String username,
                                   @AuthenticationPrincipal User user) {
        User user2 = userService.getUserByUsername(username);
        Dialogue dialogue = dialogueService.findBy2Users(user, user2);
        if (dialogue != null) {
            return "redirect:/dialogue-" + dialogue.getId();
        }
        dialogueService.createNewDialogue(user, user2);
        dialogue = dialogueService.findBy2Users(user, user2);

        return "redirect:/dialogue-" + dialogue.getId();
    }
}
