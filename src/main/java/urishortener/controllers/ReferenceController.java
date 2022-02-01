package urishortener.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import urishortener.entities.ReferenceNew;
import urishortener.entities.SystemReferenceDelete;
import urishortener.entities.SystemReferenceCreating;
import urishortener.repositories.ReferenceRepository;
import urishortener.repositories.RoleRepository;
import urishortener.services.UserService;

import java.net.UnknownHostException;
import java.security.Principal;
import java.util.List;

@RestController("api/v1/reference")
@RequestMapping("/auth")
public class ReferenceController {

    private final String hostname = "http://213.108.211.138:8189/";
    private UserService userService;

    @Autowired
    private ReferenceRepository referenceRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView reference(Model model, Principal principal) {
        List<ReferenceNew> referenceNewList = referenceRepository.findAllByIdUser(userService.findByUsername(principal.getName()));
        model.addAttribute("text", "те самые ссылки");
        model.addAttribute("textHost", hostname);
        model.addAttribute("referenceNewList", referenceNewList);
        return new ModelAndView("reference");
    }

    @GetMapping("/admin")
    public ModelAndView referenceAdm(Model model, Principal principal) {
        List<ReferenceNew> referenceNewList = referenceRepository.findAll();
        model.addAttribute("text", "ссылки всех");
        model.addAttribute("referenceNewList", referenceNewList);
        return new ModelAndView("reference");
    }

    @PostMapping("/create")
    public ModelAndView reference_created(Model model, @RequestBody SystemReferenceCreating systemReferenceCreating, Principal principal) throws UnknownHostException {
        if (!(systemReferenceCreating.getReferenceUser().contains("http://") || systemReferenceCreating.getReferenceUser().contains("https://"))) {
            model.addAttribute("text", "is it correct Link, bad boy?");
        return new ModelAndView("oops");
        } else {
            ReferenceNew referenceNew = new ReferenceNew(systemReferenceCreating.getReferenceUser(), userService.findByUsername(principal.getName()), systemReferenceCreating.getLifetime());
            referenceNew.setReferenceShort(referenceNew.GenerationShortReference());
            referenceRepository.save(referenceNew);
            model.addAttribute("text", hostname + referenceNew.getReferenceShort());
            System.out.println(userService.findIdByUsername(principal.getName()));
            return new ModelAndView("reference_created");
        }
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody SystemReferenceDelete systemReferenceDelete, Principal principal) {
        //to do проверка что удаляешь именно свою ссылку, по идее метод не нужен будет, если удаление настроить на кнопку
        referenceRepository.delete(referenceRepository.findByReferenceShort(systemReferenceDelete.getReferenceShort()));
    }

}
