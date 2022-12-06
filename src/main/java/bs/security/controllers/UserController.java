/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bs.security.controllers;

import bs.security.dao.UserDao;
import bs.security.entity.User;
import java.security.Principal;
import java.util.Objects;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
 @Autowired
 private PasswordEncoder passwordEncoder;
 @Autowired
 private UserDao dao;
 @GetMapping("/login")
 public String loginPage() {
 //zwrócenie nazwy widoku logowania - login.html
 return "login";
 }
    @GetMapping("/register")
    public String registerPage(Model m) {
    //dodanie do modelu nowego użytkownika
    m.addAttribute("user", new User());
    //zwrócenie nazwy widoku rejestracji - register.html
    return "register";
    }
    @PostMapping("/register")
    public String registerPagePOST(@Valid User user, BindingResult bindingResult) {
        if (dao.findByLogin(user.getLogin()) != null) {
            bindingResult.rejectValue("login", "error.users", "Istnieje już użytkownik o takim loginie");
        }
        if (bindingResult.hasErrors()) {
            return "register";
        }
        if (dao.findByLogin(user.getName()) != null) {
            bindingResult.rejectValue("login", "Login nie może się powtarzać");
            return "register";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.save(user);
        
        return "redirect:/login";
    }
    
    
    @GetMapping("/profile")
    public String profilePage(Model m, Principal principal) {
    //dodanie do modelu aktualnie zalogowanego użytkownika:
    m.addAttribute("user", dao.findByLogin(principal.getName()));
    //zwrócenie nazwy widoku profilu użytkownika - profile.html
    return "profile";
    }
    
    
    @GetMapping("/useredit")
    public String useredit(Model m, Principal principal) {
    //dodanie do modelu aktualnie zalogowanego użytkownika:
    m.addAttribute("user", dao.findByLogin(principal.getName()));
    //zwrócenie nazwy widoku profilu użytkownika - profile.html
    return "useredit";
    }
    
    
    @PostMapping("/useredit")
   public String submitForm(@Valid User user,BindingResult bindingResult, Principal principal) {
   if (dao.findByLogin(user.getLogin()) != null && !Objects.equals(user.getLogin(), principal.getName())) {
               bindingResult.rejectValue("login", "error.users", "Istnieje już użytkownik o takim loginie");
           }
           if (bindingResult.hasErrors()) {
               return "useredit";
           }
           user.setPassword(passwordEncoder.encode(user.getPassword()));
           dao.save(user);
          
           return "redirect:/logout";
           }
   
   
   @RequestMapping(value = "users", method = RequestMethod.GET)
           public String messages(Model model) {
               model.addAttribute("users", dao.findAll());
               return "users";
           }
        
    @GetMapping("/delete")
    public String deletePage(Principal principal) {
        dao.delete(dao.findByLogin(principal.getName()));
        return "redirect:/logout";
    }


    
} 