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

import com.ocr.p7.OCRP7.domain.BidList;
import com.ocr.p7.OCRP7.repositories.BidListRepository;

@Controller
public class BidListController {
    private static final Logger logger = LogManager.getLogger("BidListController");

    @Autowired
    private BidListRepository bidListRepository;

    @RequestMapping("/bidList/list")
    public String home(Model model) {
        model.addAttribute("bidLists", bidListRepository.findAll());
        logger.info("bidList/list : OK");
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        logger.info("GET /bidList/add : OK");
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            bidListRepository.save(bid);
            model.addAttribute("bidlist", bidListRepository.findAll());
            logger.info("POST /bidList/validate : OK");
            return "redirect:/bidList/list";
        }
        logger.info("/bidList/validate : KO");
        return "bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        BidList bid = bidListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
        model.addAttribute("bidList", bid);
        logger.info("GET /bidList/update : OK");
        return "bidList/update";
    }

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

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        BidList bid = bidListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
        bidListRepository.delete(bid);
        model.addAttribute("users", bidListRepository.findAll());
        logger.info("/bidList/delete : OK");
        return "redirect:/bidList/list";

    }
}
