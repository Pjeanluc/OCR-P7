package com.ocr.p7.OCRP7.controllers;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ocr.p7.OCRP7.config.ValidPassword;
import com.ocr.p7.OCRP7.domain.User;
import com.ocr.p7.OCRP7.repositories.UserRepository;

/**
 * 
 * This controller class expose data related methods to front for the User object
 * @author S053261
 *
 */
@Controller
public class UserController {
    private static final Logger logger = LogManager.getLogger("UserController");

    @Autowired
    private UserRepository userRepository;

    /**
     * Endpoint to show the list of user
     * @param model
     * @return the user list
     */
    @RequestMapping("/user/list")
    public String home(Model model) {
        model.addAttribute("users", userRepository.findAll());
        logger.info("/user/list : OK");
        return "user/list";
    }

    /**
     * Endpoint to display user adding form 
     * @param bid the user to be added
     * @return
     */
    @GetMapping("/user/add")
    public String addUser(User bid) {
        logger.info("GET /user/add : OK");
        return "user/add";
    }

    /**
     * Endpoint to validate the info of user
     * @param user, user to be added
     * @param result technical result
     * @param model public interface model, model can be accessed and attributes can be added
     * @return
     */
    @PostMapping("/user/validate")
    public String validate(@Valid @ValidPassword User user, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
            model.addAttribute("users", userRepository.findAll());
            logger.info("POST /user/validate : OK");
            return "redirect:/user/list";
        }
        logger.info("/user/validate : KO");
        return "user/add";
    }

    /**
     * Endpoint to display user updating form
     * @param id the user id
     * @param model public interface model, model can be accessed and attributes can be added
     * @return user/update if OK
     */
    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        user.setPassword("");
        model.addAttribute("user", user);
        logger.info("GET /user/update : OK");
        return "user/update";
    }

    /**
     * Endpoint to validate the user updating form
     * @param id
     * @param user the user id
     * @param result technical result
     * @param model public interface model, model can be accessed and attributes can be added
     * @return user/list if ok or user/update if ko
     */
    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid @ValidPassword User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            logger.info("POST /user/update : KO");
            return "user/update";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setId(id);
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        logger.info("POST /user/update : OK");
        return "redirect:/user/list";
    }

   /**
    * Endpoint to delete a user
    * @param id the user id to delete
    * @param model public interface model, model can be accessed and attributes can be added
    * @return user/list if ok
    */
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        model.addAttribute("users", userRepository.findAll());
        logger.info("/user/delete : OK");
        return "redirect:/user/list";
    }
}
