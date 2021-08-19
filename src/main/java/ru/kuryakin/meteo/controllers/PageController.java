package ru.kuryakin.meteo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kuryakin.meteo.dao.user.AppRoleDAO;
import ru.kuryakin.meteo.dao.user.AppUserDAO;
import ru.kuryakin.meteo.models.user.AppUser;
import ru.kuryakin.meteo.sbsecurity.WebUtils;

import java.security.Principal;
import java.util.List;

@Controller
public class PageController {

    @Autowired
    AppRoleDAO appRoleDAO;

    @Autowired
    AppUserDAO appUserDAO;

    @GetMapping("/")
    public String getIndex(Model model){
        long l = 1;
        List<String> strings = appRoleDAO.getRoleNames(l);
        AppUser appUser = appUserDAO.findUserAccount("admin");
        model.addAttribute("strings", strings);
        model.addAttribute("appUser", appUser);
        return "index";
    }

    @GetMapping("/welcome")
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
    }

    @GetMapping(value = "/admin")
    public String adminPage(Model model, Principal principal) {

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "adminPage";
    }

    @GetMapping(value = "/login")
    public String loginPage(Model model) {
        return "loginPage";
    }

    @GetMapping(value = "/logoutSuccessful")
    public String logoutSuccessfulPage(Model model) {
//        model.addAttribute("title", "Logout");
        return "loginPage";
    }

    @GetMapping(value = "/userInfo")
    public String userInfo(Model model, Principal principal) {

        // After user login successfully.
        String userName = principal.getName();

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "userInfoPage";
    }
}
