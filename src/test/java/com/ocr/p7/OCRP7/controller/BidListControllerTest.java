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

import com.ocr.p7.OCRP7.domain.BidList;
import com.ocr.p7.OCRP7.repositories.BidListRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
class BidListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BidListRepository bidListRepository;

    /**
     * the to show the list of bidList
     */
    @Test
    public void getAllBidListControllerTest() throws Exception {

        // GIVEN
        // WHEN
        // THEN
        this.mockMvc
                .perform(
                        get("/bidList/list").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /**
     * Test to validate a bidList
     */
    @Test
    public void postValidateBidListTest() throws Exception {

        // GIVEN
        BidList bidList = new BidList();
        bidList.setAccount("accounttest");
        bidList.setType("typetest");
        bidList.setBidQuantity(100d);

        Optional<BidList> userMock = Optional.of(bidList);

        Mockito.when(bidListRepository.findById(any(Integer.class))).thenReturn(userMock);

        // WHEN
        // THEN
        this.mockMvc
                .perform(post("/bidList/validate").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).param("account", bidList.getAccount())
                        .param("type", bidList.getType()).param("bidquantity", bidList.getBidQuantity().toString()))
                .andDo(print()).andExpect(MockMvcResultMatchers.redirectedUrl("/bidList/list"))
                .andExpect(status().is3xxRedirection());
    }

    /**
     * Test to validate a bidList with an error (type null)
     */
    @Test
    public void postValidateBidListWithErrorTest() throws Exception {

        // GIVEN
        BidList bidList = new BidList();
        bidList.setAccount("accounttest");
        bidList.setBidQuantity(100d);
        Optional<BidList> userMock = Optional.of(bidList);

        Mockito.when(bidListRepository.findById(any(Integer.class))).thenReturn(userMock);

        // WHEN
        // THEN
        this.mockMvc
                .perform(post("/bidList/validate").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).param("account", bidList.getAccount())
                        .param("type", bidList.getType()).param("bidquantity", bidList.getBidQuantity().toString()))
                .andDo(print()).andExpect(status().isOk()).andExpect(view().name("bidList/add"));

    }

    /**
     * Test to validate a bidList update
     */

    @Test
    public void postUpdateBidListTest() throws Exception {

        // GIVEN
        BidList bidList = new BidList();
        bidList.setAccount("accounttest");
        bidList.setType("typetest");
        bidList.setBidQuantity(100d);
        Optional<BidList> userMock = Optional.of(bidList);

        Mockito.when(bidListRepository.findById(any(Integer.class))).thenReturn(userMock);

        // WHEN
        // THEN

        this.mockMvc
                .perform(post("/bidList/update/1").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).param("account", bidList.getAccount())
                        .param("type", bidList.getType()).param("bidquantity", bidList.getBidQuantity().toString()))
                .andDo(print()).andExpect(MockMvcResultMatchers.redirectedUrl("/bidList/list"))
                .andExpect(status().is3xxRedirection());
    }

    /**
     * Test to validate a bidList update with an error (type)
     */
    @Test
    public void postUpdateBidListWithWrongParametersTest() throws Exception {

        // GIVEN
        BidList bidList = new BidList();
        bidList.setAccount("accounttest");
        bidList.setBidQuantity(100d);
        Optional<BidList> userMock = Optional.of(bidList);

        Mockito.when(bidListRepository.findById(any(Integer.class))).thenReturn(userMock);

        // WHEN
        // THEN
        this.mockMvc
                .perform(post("/bidList/update/1").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).param("account", bidList.getAccount())
                        .param("type", bidList.getType()).param("bidquantity", bidList.getBidQuantity().toString()))
                .andExpect(MockMvcResultMatchers.redirectedUrl(null)).andExpect(status().isOk())
                .andExpect(view().name("bidList/update"));
    }

    /**
     * Test to get the form to add a bidList
     */
    @Test
    public void getBidListAddTest() throws Exception {

        // GIVEN
        // WHEN
        // THEN
        this.mockMvc
                .perform(get("/bidList/add").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    /**
     * Test to get the form to update a bidList
     */
    @Test
    public void getUpdateUserTest() throws Exception {

        // GIVEN
        BidList bidList = new BidList();
        bidList.setAccount("accounttest");
        bidList.setType("typetest");
        bidList.setBidQuantity(100d);
        Optional<BidList> userMock = Optional.of(bidList);

        Mockito.when(bidListRepository.findById(any(Integer.class))).thenReturn(userMock);

        // WHEN
        // THEN
        this.mockMvc.perform(
                get("/bidList/update/1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    /**
     * Test to delete a bidList
     */
    @Test
    public void getDeleteBidListTest() throws Exception {

        // GIVEN
     // GIVEN
        BidList bidList = new BidList();
        bidList.setAccount("accounttest");
        bidList.setType("typetest");
        bidList.setBidQuantity(100d);
        Optional<BidList> userMock = Optional.of(bidList);

        Mockito.when(bidListRepository.findById(any(Integer.class))).thenReturn(userMock);

        // WHEN
        // THEN
        this.mockMvc
                .perform(get("/bidList/delete/1").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/bidList/list")).andExpect(status().is3xxRedirection());
    }
    

    /**
     * Test to delete a bidList, with an id bidList not existing
     */
    @Test
    public void getDeleteBidListNoExistingTest() throws Exception {

        // GIVEN
        BidList bidList = null;
        Optional<BidList> bidListMock = Optional.ofNullable(bidList);

        Mockito.when(bidListRepository.findById(any(Integer.class))).thenReturn(bidListMock);

        // WHEN
        // THEN
        try {
            this.mockMvc.perform(
                    get("/bidList/delete/1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
        } catch (Exception e) {
            assertThat(e).hasMessageContaining("Invalid bidList Id:1");
        }
    }

}
