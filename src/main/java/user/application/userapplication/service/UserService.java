package user.application.userapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import user.application.userapplication.model.Role;
import user.application.userapplication.model.User;
import user.application.userapplication.repository.UserRepository;

import javax.transaction.Transactional;
import java.io.File;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
public class UserService implements UserDetailsService {

    @Value("${images.upload.absolute.path}")
    private String uploadPath;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void setAvatarImage(MultipartFile file, User user) {
        String resultFileName = ServiceUtils.uploadFile(file, uploadPath);
        if (!resultFileName.equals("")) {
            user.setAvatarImage(resultFileName);
        }

        userRepository.save(user);
    }

    public boolean addUser(User user) {
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if (userFromDb != null) {
            return false;
        }
        user.setDateOfCreation(LocalDate.now());
        user.setDescription(null);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setAvatarImage("anonymous.jpg");
        userRepository.save(user);

        return true;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
