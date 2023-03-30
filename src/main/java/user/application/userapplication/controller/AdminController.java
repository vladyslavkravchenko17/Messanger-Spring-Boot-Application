package user.application.userapplication.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import user.application.userapplication.model.User;
import user.application.userapplication.service.UserService;

@Controller
@Api("Admin controller")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    @ApiOperation("Show all users")
    public String adminPanel(Model model) {
        model.addAttribute("users", userService.getAllUsers());

        return "admin";
    }

    @GetMapping("/admin-{username}")
    @ApiOperation("Show user's edit admin panel")
    public String editUserAsAdmin(Model model,
                                  @PathVariable("username") String username) {
        User user = userService.getUserByUsername(username);


        model.addAttribute("user", user);

        return "editUserAsAdmin";
    }

    @PostMapping("/admin-{username}")
    @ApiOperation("Change activity of the user")
    public String userActivity(@PathVariable("username") String username) {
        User user = userService.getUserByUsername(username);

        if (user.isActive()) {
            user.setActive(false);
        } else {
            user.setActive(true);
        }

        userService.saveUser(user);

        return "redirect:/admin-" + username;
    }

}
