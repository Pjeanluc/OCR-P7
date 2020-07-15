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

import com.ocr.p7.OCRP7.domain.Trade;
import com.ocr.p7.OCRP7.repositories.TradeRepository;


/**
 * This controller class expose data related methods to front for the trade
 * object
 * 
 * @author S053261
 *
 */
@Controller
public class TradeController {
 private static final Logger logger = LogManager.getLogger("TradeController");
    
    @Autowired
    private TradeRepository tradeRepository;

    /**
     * End to show the list of Trade
     * 
     * @param model
     * @return the trade list
     */
    @RequestMapping("/trade/list")
    public String home(Model model) {
        model.addAttribute("trades", tradeRepository.findAll());
        logger.info("trade/list : OK");
        return "trade/list";
    }
    
    /**
     * End to display trade adding form
     * 
     * @param trade the trade to be added
     * @return
     */
    @GetMapping("/trade/add")
    public String addtradeForm(Trade trade) {
        logger.info("GET /trade/add : OK");
        return "trade/add";
    }
    
    /**
     * End to validate the info of trade
     * 
     * @param trade, trade to be added
     * @param result   technical result
     * @param model    public interface model, model can be accessed and attributes
     *                 can be added
     * @return
     */
    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            trade.setRevisionDate(timestamp);
            trade.setCreationDate(timestamp);
            trade.setTradeDate(timestamp);
            tradeRepository.save(trade);
            model.addAttribute("trade", tradeRepository.findAll());
            logger.info("POST /trade/validate : OK");
            return "redirect:/trade/list";
        }
        logger.info("/trade/validate : KO");
        return "trade/add";
    }

    /**
     * End to display updating form
     * 
     * @param id    the trade id
     * @param model public interface model, model can be accessed and attributes can
     *              be added
     * @return trade/update if OK
     */
    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Trade trade = tradeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
        model.addAttribute("trade", trade);
        logger.info("GET /trade/update : OK");
        return "trade/update";
    }
    
    /**
     * End to validate the trade updating form
     * 
     * @param id
     * @param trade the trade id
     * @param result  technical result
     * @param model   public interface model, model can be accessed and attributes
     *                can be added
     * @return trade/list if ok or trade/update if ko
     */
    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            logger.info("POST /trade/update : KO");
            return "trade/update";
        }
        trade.setTradeId(id);
        tradeRepository.save(trade);
        model.addAttribute("trade", tradeRepository.findAll());
        logger.info("POST /trade/update : OK");
        return "redirect:/trade/list";
    }

    /**
     * End to delete a trade
     * 
     * @param id    the trade id to delete
     * @param model public interface model, model can be accessed and attributes can
     *              be added
     * @return trade/list if ok
     */
    @GetMapping("/trade/delete/{id}")
    public String deletetrade(@PathVariable("id") Integer id, Model model) {
        Trade trade = tradeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
        tradeRepository.delete(trade);
        model.addAttribute("trades", tradeRepository.findAll());
        logger.info("/trade/delete : OK");
        return "redirect:/trade/list";
    }
   }
