package com.ocr.p7.OCRP7.controllers;

import java.sql.Timestamp;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ocr.p7.OCRP7.domain.Rating;
import com.ocr.p7.OCRP7.repositories.RatingRepository;

/**
 * This controller class expose data related methods to front for the rating
 * object
 * 
 * @author S053261
 *
 */
@Controller
public class RatingController {
    private static final Logger logger = LogManager.getLogger("RatingController");

    @Autowired
    private RatingRepository ratingRepository;

    /**
     * Endpoint to show the list of rating
     * 
     * @param model
     * @return the rating list
     */
    @RequestMapping("/rating/list")
    public String home(Model model) {
        model.addAttribute("ratings", ratingRepository.findAll());
        logger.info("rating/list : OK");
        return "rating/list";
    }

    /**
     * Endpoint to display rating adding form
     * 
     * @param rating the rating to be added
     * @return
     */
    @GetMapping("/rating/add")
    public String addratingForm(Rating rating) {
        logger.info("GET /rating/add : OK");
        return "rating/add";
    }

    /**
     * Endpoint to validate the info of rating
     * 
     * @param rating, rating to be added
     * @param result  technical result
     * @param model   public interface model, model can be accessed and attributes
     *                can be added
     * @return
     */
    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        if (!result.hasErrors()) {           
            ratingRepository.save(rating);
            model.addAttribute("rating", ratingRepository.findAll());
            logger.info("POST /rating/validate : OK");
            return "redirect:/rating/list";
        }
        logger.info("/rating/validate : KO");
        return "rating/add";
    }

    /**
     * Endpoint to display updating form
     * 
     * @param id    the rating id
     * @param model public interface model, model can be accessed and attributes can
     *              be added
     * @return rating/update if OK
     */
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
        model.addAttribute("rating", rating);
        logger.info("GET /rating/update : OK");
        return "rating/update";
    }

    /**
     * Endpoint to validate the rating updating form
     * 
     * @param id
     * @param rating the rating id
     * @param result     technical result
     * @param model      public interface model, model can be accessed and
     *                   attributes can be added
     * @return rating/list if ok or rating/update if ko
     */
    @PostMapping("/rating/update/{id}")
    public String updaterating(@PathVariable("id") Integer id, @Valid Rating rating, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            logger.info("POST /rating/update : KO");
            return "rating/update";
        }
        rating.setId(id);
        ratingRepository.save(rating);
        model.addAttribute("rating", ratingRepository.findAll());
        logger.info("POST /rating/update : OK");
        return "redirect:/rating/list";
    }

    /**
     * Endpoint to delete a rating
     * 
     * @param id    the rating id to delete
     * @param model public interface model, model can be accessed and attributes can
     *              be added
     * @return rating/list if ok
     */
    @GetMapping("/rating/delete/{id}")
    public String deleterating(@PathVariable("id") Integer id, Model model) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
        ratingRepository.delete(rating);
        model.addAttribute("ratings", ratingRepository.findAll());
        logger.info("/rating/delete : OK");
        return "redirect:/rating/list";
    }

}
