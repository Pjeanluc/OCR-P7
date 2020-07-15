package com.ocr.p7.OCRP7.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ocr.p7.OCRP7.domain.Trade;
import com.ocr.p7.OCRP7.repositories.TradeRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
class TradeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TradeRepository tradeRepository;

    /**
     * the to show the list of trade
     */
    @Test
    public void getAllTradeListControllerTest() throws Exception {

        // GIVEN
        // WHEN
        // THEN
        this.mockMvc.perform(
                get("/trade/list").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /**
     * Test to validate a trade
     */
    @Test
    public void postValidateTradeTest() throws Exception {

        // GIVEN
        Trade trade = new Trade();
        trade.setAccount("account");
        trade.setType("type");
        trade.setBuyQuantity(15d);
        
        Optional<Trade> tradeMock = Optional.of(trade);

        Mockito.when(tradeRepository.findById(any(Integer.class))).thenReturn(tradeMock);

        // WHEN
        // THEN
        this.mockMvc.perform(post("/trade/validate").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).param("account", trade.getAccount())
                .param("type", trade.getType()).param("buyQuantity", trade.getBuyQuantity().toString()))                
                .andDo(print()).andExpect(MockMvcResultMatchers.redirectedUrl("/trade/list"))
                .andExpect(status().is3xxRedirection());
    }

    /**
     * Test to validate a trade with an error (Buy Quantity)
     */
    @Test
    public void postValidateTradeWithErrorTest() throws Exception {

        // GIVEN
        Trade trade = new Trade();
        trade.setAccount("account");
        trade.setType("type");
        trade.setBuyQuantity(0d);
       
        Optional<Trade> tradeMock = Optional.of(trade);

        Mockito.when(tradeRepository.findById(any(Integer.class))).thenReturn(tradeMock);

        // WHEN
        // THEN
        this.mockMvc
                .perform(post("/trade/validate").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).param("account", trade.getAccount())
                        .param("type", trade.getType()).param("buyQuantity", trade.getBuyQuantity().toString())) 
                .andDo(print()).andExpect(status().isOk()).andExpect(view().name("trade/add"));

    }

    /**
     * Test to validate a trade update
     */

    @Test
    public void postUpdateTradeTest() throws Exception {

        // GIVEN
        Trade trade = new Trade();
        trade.setAccount("account");
        trade.setType("type");
        trade.setBuyQuantity(15d);

        Optional<Trade> tradeMock = Optional.of(trade);

        Mockito.when(tradeRepository.findById(any(Integer.class))).thenReturn(tradeMock);

        // WHEN
        // THEN

        this.mockMvc
                .perform(post("/trade/update/1").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).param("account", trade.getAccount())
                        .param("type", trade.getType()).param("buyQuantity", trade.getBuyQuantity().toString())) 
                .andDo(print()).andExpect(MockMvcResultMatchers.redirectedUrl("/trade/list"))
                .andExpect(status().is3xxRedirection());
    }

    /**
     * Test to validate a trade update with an error (type)
     */
    @Test
    public void postUpdateTradeWithWrongParametersTest() throws Exception {

        // GIVEN
        Trade trade = new Trade();
        trade.setAccount("account");
        trade.setBuyQuantity(15d);
       
        Optional<Trade> tradeMock = Optional.of(trade);

        Mockito.when(tradeRepository.findById(any(Integer.class))).thenReturn(tradeMock);

        // WHEN
        // THEN
        this.mockMvc
                .perform(post("/trade/update/1").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .param("account", trade.getAccount())
                        .param("type", trade.getType()).param("buyQuantity", trade.getBuyQuantity().toString())) 
                .andExpect(MockMvcResultMatchers.redirectedUrl(null)).andExpect(status().isOk())
                .andExpect(view().name("trade/update"));
    }

    /**
     * Test to get the form to add a trade
     */
    @Test
    public void gettradeAddTest() throws Exception {

        // GIVEN
        // WHEN
        // THEN
        this.mockMvc
                .perform(get("/trade/add").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /**
     * Test to get the form to update a trade
     */
    @Test
    public void getUpdatetradeTest() throws Exception {

        // GIVEN
        Trade trade = new Trade();
        trade.setAccount("account");
        trade.setType("type");
        trade.setBuyQuantity(15d);
       
        Optional<Trade> tradeMock = Optional.of(trade);

        Mockito.when(tradeRepository.findById(any(Integer.class))).thenReturn(tradeMock);

        // WHEN
        // THEN
        this.mockMvc.perform(
                get("/trade/update/1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /**
     * Test to delete a trade
     */
    @Test
    public void getDeleteTradeTest() throws Exception {

        // GIVEN
        // GIVEN
        Trade trade = new Trade();
        trade.setAccount("account");
        trade.setType("type");
        trade.setBuyQuantity(15d);
       
        Optional<Trade> tradeMock = Optional.of(trade);

        Mockito.when(tradeRepository.findById(any(Integer.class))).thenReturn(tradeMock);


        // WHEN
        // THEN
        this.mockMvc
                .perform(get("/trade/delete/1").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/trade/list")).andExpect(status().is3xxRedirection());
    }

    /**
     * Test to delete a trade, with an id trade not existing
     */
    @Test
    public void getDeleteTradeNotExistingTest() throws Exception {

        // GIVEN
        Trade trade = null;
        Optional<Trade> tradeMock = Optional.ofNullable(trade);

        Mockito.when(tradeRepository.findById(any(Integer.class))).thenReturn(tradeMock);

        // WHEN
        // THEN
        try {
            this.mockMvc.perform(get("/trade/delete/1").contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON));
        } catch (Exception e) {
            assertThat(e).hasMessageContaining("Invalid trade Id:1");
        }
    }



}
