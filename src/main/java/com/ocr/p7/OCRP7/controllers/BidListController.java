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

import com.ocr.p7.OCRP7.domain.BidList;
import com.ocr.p7.OCRP7.repositories.BidListRepository;

/**
 * This controller class expose data related methods to front for the BidList
 * object
 * 
 * @author S053261
 *
 */
@Controller
public class BidListController {
    private static final Logger logger = LogManager.getLogger("BidListController");

    @Autowired
    private BidListRepository bidListRepository;

    /**
     * Endpoint to show the list of bid
     * 
     * @param model
     * @return the bidList list
     */
    @RequestMapping("/bidList/list")
    public String home(Model model) {
        model.addAttribute("bidLists", bidListRepository.findAll());
        logger.info("bidList/list : OK");
        return "bidList/list";
    }

    /**
     * Endpoint to display bidlist adding form
     * 
     * @param bid the bidlist to be added
     * @return
     */
    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        logger.info("GET /bidList/add : OK");
        return "bidList/add";
    }

    /**
     * Endpoint to validate the info of bidlist
     * 
     * @param bidlist, bidlist to be added
     * @param result   technical result
     * @param model    public interface model, model can be accessed and attributes
     *                 can be added
     * @return
     */
    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            bid.setBidListDate(timestamp);
            bid.setCreationDate(timestamp);
            bidListRepository.save(bid);
            model.addAttribute("bidlist", bidListRepository.findAll());
            logger.info("POST /bidList/validate : OK");
            return "redirect:/bidList/list";
        }
        logger.info("/bidList/validate : KO");
        return "bidList/add";
    }

    /**
     * Endpoint to display updating form
     * 
     * @param id    the bidlist id
     * @param model public interface model, model can be accessed and attributes can
     *              be added
     * @return bidlist/update if OK
     */
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        BidList bid = bidListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
        model.addAttribute("bidList", bid);
        logger.info("GET /bidList/update : OK");
        return "bidList/update";
    }

    /**
     * Endpoint to validate the bidlist updating form
     * 
     * @param id
     * @param bidlist the bidlist id
     * @param result  technical result
     * @param model   public interface model, model can be accessed and attributes
     *                can be added
     * @return bidlist/list if ok or bidlist/update if ko
     */
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList, BindingResult result, Model model) {
        if (result.hasErrors()) {
            logger.info("POST /bidList/update : KO");
            return "bidList/update";
        }
        bidList.setBidListId(id);
        bidListRepository.save(bidList);
        model.addAttribute("bidList", bidListRepository.findAll());
        logger.info("POST /bidList/update : OK");
        return "redirect:/bidList/list";
    }

    /**
     * Endpoint to delete a bidlist
     * 
     * @param id    the bidlist id to delete
     * @param model public interface model, model can be accessed and attributes can
     *              be added
     * @return bidlist/list if ok
     */
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        BidList bid = bidListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
        bidListRepository.delete(bid);
        model.addAttribute("bidLists", bidListRepository.findAll());
        logger.info("/bidList/delete : OK");
        return "redirect:/bidList/list";

    }
}
