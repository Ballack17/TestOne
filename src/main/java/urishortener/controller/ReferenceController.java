package urishortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import urishortener.entities.Reference1;
import urishortener.entities.SystemReference1;
import urishortener.entities.SystemReferenceCreating;
import urishortener.repositories.ReferenceRepository;
import urishortener.repositories.RoleRepository;
import urishortener.services.UserService;

import java.net.UnknownHostException;
import java.security.Principal;
import java.util.List;


@RestController ("api/v1/reference")
@RequestMapping ("/auth")
public class ReferenceController {

    private final String hostname = "http://213.108.211.138:8189/";

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private ReferenceRepository referenceRepository;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public ModelAndView reference(Model model, Principal principal) {
        List<Reference1> reference1List = referenceRepository.findAllByIdUser(userService.findByUsername(principal.getName()));
        model.addAttribute("text", "те самые ссылки");
        model.addAttribute("textHost", hostname);

       System.out.println(reference1List.toString());

        model.addAttribute("reference1List", reference1List);
        return new ModelAndView("reference");
    }

    @GetMapping ("/admin")
    public ModelAndView reference1(Model model, Principal principal) {
        List<Reference1> reference1List = referenceRepository.findAll();
        model.addAttribute("text", "ссылки всех");
        System.out.println(reference1List.toString());

        model.addAttribute("reference1List", reference1List);
        return new ModelAndView("reference");
    }


    @PostMapping("/create")
    public ModelAndView reference_created(Model model, @RequestBody SystemReferenceCreating systemReferenceCreating, Principal principal) throws UnknownHostException {
        System.out.println(systemReferenceCreating.toString());
        Reference1 referenceNew = new Reference1(systemReferenceCreating.getReferenceUser(), userService.findByUsername(principal.getName()), systemReferenceCreating.getLifetime());
        referenceNew.setReferenceShort(referenceNew.GenerationShortReference());
        referenceRepository.save(referenceNew);
        model.addAttribute("text", hostname + referenceNew.getReferenceShort());
        System.out.println(userService.findIdByUsername(principal.getName()));
        return new ModelAndView("reference_created");
    }

//    @PostMapping("/create1")
//    public String role_name(@RequestBody Role name) {
//        Role rnNew = new Role();
//        rnNew.setRoleName(name.getRoleName());
//        return roleRepository.save(rnNew).getRoleName();
//    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody SystemReference1 systemReference1, Principal principal) {
        //to do проверка что удаляешь именно свою ссылку, по идее метод не нужен будет, если удаление настроить на кнопку
        referenceRepository.delete(referenceRepository.findByReferenceShort(systemReference1.getReferenceShort()));
    }

}
