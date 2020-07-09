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

import com.ocr.p7.OCRP7.domain.CurvePoint;
import com.ocr.p7.OCRP7.repositories.CurvePointRepository;

/**
 * This controller class expose data related methods to front for the CurvePoint
 * object
 * 
 * @author S053261
 *
 */
@Controller
public class CurveController {
    private static final Logger logger = LogManager.getLogger("CurveController");
    
    @Autowired
    private CurvePointRepository curvePointRepository;

    /**
     * Endpoint to show the list of CurvePoint
     * 
     * @param model
     * @return the curvePoint list
     */
    @RequestMapping("/curvePoint/list")
    public String home(Model model) {
        model.addAttribute("curvePoints", curvePointRepository.findAll());
        logger.info("curvePoint/list : OK");
        return "curvePoint/list";
    }
    
    /**
     * Endpoint to display curvePoint adding form
     * 
     * @param curvePoint the curvePoint to be added
     * @return
     */
    @GetMapping("/curvePoint/add")
    public String addcurvePointForm(CurvePoint curvePoint) {
        logger.info("GET /curvePoint/add : OK");
        return "curvePoint/add";
    }
    
    /**
     * Endpoint to validate the info of curve
     * 
     * @param curvePoint, curvePoint to be added
     * @param result   technical result
     * @param model    public interface model, model can be accessed and attributes
     *                 can be added
     * @return
     */
    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            curvePoint.setAsOfDate(timestamp);
            curvePoint.setCreationDate(timestamp);
            curvePointRepository.save(curvePoint);
            model.addAttribute("curvePoint", curvePointRepository.findAll());
            logger.info("POST /curvePoint/validate : OK");
            return "redirect:/curvePoint/list";
        }
        logger.info("/curvePoint/validate : KO");
        return "curvePoint/add";
    }

    /**
     * Endpoint to display updating form
     * 
     * @param id    the curvePoint id
     * @param model public interface model, model can be accessed and attributes can
     *              be added
     * @return curvePoint/update if OK
     */
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        CurvePoint curvePoint = curvePointRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));
        model.addAttribute("curvePoint", curvePoint);
        logger.info("GET /curvePoint/update : OK");
        return "curvePoint/update";
    }
    
    /**
     * Endpoint to validate the curvePoint updating form
     * 
     * @param id
     * @param curvePoint the curvePoint id
     * @param result  technical result
     * @param model   public interface model, model can be accessed and attributes
     *                can be added
     * @return curvePoint/list if ok or curvePoint/update if ko
     */
    @PostMapping("/curvePoint/update/{id}")
    public String updateCurvePoint(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            logger.info("POST /curvePoint/update : KO");
            return "curvePoint/update";
        }
        curvePoint.setCurveId(id);
        curvePointRepository.save(curvePoint);
        model.addAttribute("curvePoint", curvePointRepository.findAll());
        logger.info("POST /curvePoint/update : OK");
        return "redirect:/curvePoint/list";
    }

    /**
     * Endpoint to delete a curvePoint
     * 
     * @param id    the curvePoint id to delete
     * @param model public interface model, model can be accessed and attributes can
     *              be added
     * @return curvePoint/list if ok
     */
    @GetMapping("/curvePoint/delete/{id}")
    public String deletecurvePoint(@PathVariable("id") Integer id, Model model) {
        CurvePoint curvePoint = curvePointRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));
        curvePointRepository.delete(curvePoint);
        model.addAttribute("curvePoints", curvePointRepository.findAll());
        logger.info("/curvePoint/delete : OK");
        return "redirect:/curvePoint/list";
    }
}
