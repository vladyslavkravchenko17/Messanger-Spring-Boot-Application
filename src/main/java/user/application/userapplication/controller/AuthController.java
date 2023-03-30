package user.application.userapplication.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import user.application.userapplication.model.User;
import user.application.userapplication.service.UserService;

import javax.validation.Valid;

@Api("Auth controller")
@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    @ApiOperation("Register new user")
    public String addUser(@Valid User user,
                          BindingResult bindingResult,
                          Model model) {
        boolean containsErrors = false;
        if (bindingResult.hasErrors()) {
            model.mergeAttributes(ControllerUtils.getErrors(bindingResult));
            model.addAttribute("user", user);
            containsErrors = true;
        }
        if (user.getPassword() != null && !user.getPassword().equals(user.getPassword2())) {
            model.addAttribute("passwordError", "Passwords don't match!");
            containsErrors = true;
        }
        if (containsErrors) {
            return "registration";
        }

        if (userService.addUser(user)) {
            model.addAttribute("message", "Account successfully created");
            return "login";
        } else {
            model.addAttribute("message", "This user already exists");
        }
        return "registration";
    }

    @GetMapping("/")
    @ApiOperation("Redirect from '/' to '/dialogues'")
    public String mainPage() {
        return "redirect:/dialogues";
    }
}
