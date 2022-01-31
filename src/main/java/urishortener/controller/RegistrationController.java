package urishortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import urishortener.entities.Reference1;
import urishortener.entities.SystemUser;
import urishortener.repositories.UserRepository;
import urishortener.services.UserService;

import javax.transaction.Transactional;
import java.net.UnknownHostException;
import java.security.Principal;

@RestController ("api/v1/registration")
@RequestMapping ("/reg")
public class RegistrationController {

    private UserService userService;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public ModelAndView registration() {
        return new ModelAndView("registration");
    }

    @PostMapping("/createUser")
    @Transactional
    public ModelAndView createUser(Model model, @RequestBody SystemUser systemUser) {
        if (!systemUser.getPassword().equals(systemUser.getMatchingPassword())) {
            model.addAttribute("text", "passwords not matching");
            return new ModelAndView("oops");
        } else if (userService.findByUsername(systemUser.getUsername()) != null) {
            model.addAttribute("text", "login allready existing");
            return new ModelAndView("oops");
        } else {
            userService.createUser(systemUser.getUsername(), passwordEncoder.encode(systemUser.getPassword()));
        }
        model.addAttribute("textAllCool","Приветствую, новый владыка мощи уменьшателя!");
        model.addAttribute("textLogin","Login = " + systemUser.getUsername());
        model.addAttribute("textPass","Password = " + systemUser.getPassword());
        return new ModelAndView ("login");
        }

}


