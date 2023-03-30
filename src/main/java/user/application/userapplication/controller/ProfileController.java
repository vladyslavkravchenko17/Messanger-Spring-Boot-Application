package user.application.userapplication.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import user.application.userapplication.model.User;
import user.application.userapplication.service.UserService;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.Objects;
import java.util.UUID;

@Controller
@Api("Profiles controller")
public class ProfileController {

    @Value("${upload.path}")
    private String uploadPath;

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
                                @RequestParam String description) throws IOException {
        User currentUser = userService.getUserByUsername(principal.getName());

        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFileName));
            currentUser.setAvatarImage(resultFileName);
        }

        if (!description.isEmpty()){
            currentUser.setDescription(description);
        }

        User userFromDb = userService.getUserByUsername(username);
        if (!username.isEmpty() && userFromDb == null){
            currentUser.setUsername(username);
            userService.saveUser(currentUser);
            return "redirect:/login?logout";
        }

        userService.saveUser(currentUser);
        return "redirect:/profile";
    }
}
