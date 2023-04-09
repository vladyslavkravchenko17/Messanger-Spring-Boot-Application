package user.application.userapplication.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import user.application.userapplication.model.User;
import user.application.userapplication.service.UserService;

import java.security.Principal;

@Controller
@Api("Profiles controller")
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    @ApiOperation("Show principal profile")
    public String profilePage(Principal principal,
                              Model model) {
        User currentUser = userService.getUserByUsername(principal.getName());
        model.addAttribute("user", currentUser);
        return "profile";
    }

    @PostMapping("/profile")
    @ApiOperation("Change principal profile")
    public String changeProfile(Principal principal,
                                @RequestParam("file") MultipartFile file,
                                @RequestParam String username,
                                @RequestParam String description) {
        User currentUser = userService.getUserByUsername(principal.getName());

        userService.setAvatarImage(file, currentUser);


        if (!description.isEmpty()) {
            currentUser.setDescription(description);
        }

        User userFromDb = userService.getUserByUsername(username);
        if (!username.isEmpty() && userFromDb == null) {
            currentUser.setUsername(username);
            userService.saveUser(currentUser);
            return "redirect:/login?logout";
        }

        userService.saveUser(currentUser);
        return "redirect:/profile";
    }
}
