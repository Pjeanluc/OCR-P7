package com.ocr.p7.OCRP7.controllers;

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

import com.ocr.p7.OCRP7.domain.RuleName;
import com.ocr.p7.OCRP7.repositories.RuleNameRepository;

/**
 * This controller class expose data related methods to front for the ruleName
 * object
 * 
 * @author S053261
 *
 */
@Controller
public class RuleNameController {
    private static final Logger logger = LogManager.getLogger("BidListController");

    @Autowired
    private RuleNameRepository ruleNameRepository;

    /**
     * Endpoint to show the list of ruleName
     * 
     * @param model
     * @return the ruleName list
     */
    @RequestMapping("/ruleName/list")
    public String home(Model model) {
        model.addAttribute("ruleNames", ruleNameRepository.findAll());
        logger.info("ruleName/list : OK");
        return "ruleName/list";
    }

    /**
     * Endpoint to display rulename adding form
     * 
     * @param bid the ruleName to be added
     * @return
     */
    @GetMapping("/ruleName/add")
    public String addBidForm(RuleName bid) {
        logger.info("GET /ruleName/add : OK");
        return "ruleName/add";
    }

    /**
     * Endpoint to validate the info of ruleName
     * 
     * @param ruleName, ruleName to be added
     * @param result   technical result
     * @param model    public interface model, model can be accessed and attributes
     *                 can be added
     * @return
     */
    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            
            ruleNameRepository.save(ruleName);
            model.addAttribute("ruleName", ruleNameRepository.findAll());
            logger.info("POST /ruleName/validate : OK");
            return "redirect:/ruleName/list";
        }
        logger.info("/ruleName/validate : KO");
        return "ruleName/add";
    }

    /**
     * Endpoint to display updating form
     * 
     * @param id    the ruleName id
     * @param model public interface model, model can be accessed and attributes can
     *              be added
     * @return ruleName/update if OK
     */
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        RuleName bid = ruleNameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
        model.addAttribute("ruleName", bid);
        logger.info("GET /ruleName/update : OK");
        return "ruleName/update";
    }

    /**
     * Endpoint to validate the ruleName updating form
     * 
     * @param id
     * @param ruleName the ruleName id
     * @param result  technical result
     * @param model   public interface model, model can be accessed and attributes
     *                can be added
     * @return ruleName/list if ok or ruleName/update if ko
     */
    @PostMapping("/ruleName/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid RuleName ruleName, BindingResult result, Model model) {
        if (result.hasErrors()) {
            logger.info("POST /ruleName/update : KO");
            return "ruleName/update";
        }
        ruleName.setId(id);
        ruleNameRepository.save(ruleName);
        model.addAttribute("ruleName", ruleNameRepository.findAll());
        logger.info("POST /ruleName/update : OK");
        return "redirect:/ruleName/list";
    }

    /**
     * Endpoint to delete a ruleName
     * 
     * @param id    the ruleName id to delete
     * @param model public interface model, model can be accessed and attributes can
     *              be added
     * @return ruleName/list if ok
     */
    @GetMapping("/ruleName/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        RuleName ruleName = ruleNameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
        ruleNameRepository.delete(ruleName);
        model.addAttribute("ruleNames", ruleNameRepository.findAll());
        logger.info("/ruleName/delete : OK");
        return "redirect:/ruleName/list";

    }
}
