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

import com.ocr.p7.OCRP7.domain.Rating;
import com.ocr.p7.OCRP7.repositories.RatingRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
class RatingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RatingRepository ratingRepository;

    /**
     * the to show the list of rating
     */
    @Test
    public void getAllRatingListControllerTest() throws Exception {

        // GIVEN
        // WHEN
        // THEN
        this.mockMvc.perform(
                get("/rating/list").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /**
     * Test to validate a rating
     */
    @Test
    public void postValidateRatingTest() throws Exception {

        // GIVEN
        Rating rating = new Rating();
        rating.setMoodysRating("AA");
        rating.setFitchRating("BB");
        rating.setSandPRating("CC");
        rating.setOrderNumber(1);

        Optional<Rating> ratingMock = Optional.of(rating);

        Mockito.when(ratingRepository.findById(any(Integer.class))).thenReturn(ratingMock);

        // WHEN
        // THEN
        this.mockMvc.perform(post("/rating/validate").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).param("moodysRating", rating.getMoodysRating())
                .param("fitchRating", rating.getFitchRating()).param("sandPRating", rating.getSandPRating())
                .param("orderNumber",rating.getOrderNumber().toString()))
                .andDo(print()).andExpect(MockMvcResultMatchers.redirectedUrl("/rating/list"))
                .andExpect(status().is3xxRedirection());
    }

    /**
     * Test to validate a rating with an error (moodysRating)
     */
    @Test
    public void postValidateRatingWithErrorTest() throws Exception {

        // GIVEN
        Rating rating = new Rating();
        rating.setFitchRating("BB");
        rating.setSandPRating("CC");
        rating.setOrderNumber(1);
       
        Optional<Rating> ratingMock = Optional.of(rating);

        Mockito.when(ratingRepository.findById(any(Integer.class))).thenReturn(ratingMock);

        // WHEN
        // THEN
        this.mockMvc
                .perform(post("/rating/validate").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).param("moodysRating", rating.getMoodysRating())
                        .param("fitchRating", rating.getFitchRating()).param("sandPRating", rating.getSandPRating())
                        .param("orderNumber",rating.getOrderNumber().toString()))
                .andDo(print()).andExpect(status().isOk()).andExpect(view().name("rating/add"));

    }

    /**
     * Test to validate a rating update
     */

    @Test
    public void postUpdateRatingTest() throws Exception {

        // GIVEN
        Rating rating = new Rating();
        rating.setMoodysRating("AA");
        rating.setFitchRating("BB");
        rating.setSandPRating("CC");
        rating.setOrderNumber(1);

        Optional<Rating> ratingMock = Optional.of(rating);

        Mockito.when(ratingRepository.findById(any(Integer.class))).thenReturn(ratingMock);

        // WHEN
        // THEN

        this.mockMvc
                .perform(post("/rating/update/1").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).param("moodysRating", rating.getMoodysRating())
                        .param("fitchRating", rating.getFitchRating()).param("sandPRating", rating.getSandPRating())
                        .param("orderNumber",rating.getOrderNumber().toString()))
                .andDo(print()).andExpect(MockMvcResultMatchers.redirectedUrl("/rating/list"))
                .andExpect(status().is3xxRedirection());
    }

    /**
     * Test to validate a rating update with an error (fitchRAting)
     */
    @Test
    public void postUpdateRatingWithWrongParametersTest() throws Exception {

        // GIVEN
        Rating rating = new Rating();
        rating.setMoodysRating("AA");
        rating.setSandPRating("CC");
        rating.setOrderNumber(1);
       
        Optional<Rating> ratingMock = Optional.of(rating);

        Mockito.when(ratingRepository.findById(any(Integer.class))).thenReturn(ratingMock);

        // WHEN
        // THEN
        this.mockMvc
                .perform(post("/rating/update/1").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .param("moodysRating", rating.getMoodysRating())
                        .param("fitchRating", rating.getFitchRating()).param("sandPRating", rating.getSandPRating())
                        .param("orderNumber",rating.getOrderNumber().toString()))
                .andExpect(MockMvcResultMatchers.redirectedUrl(null)).andExpect(status().isOk())
                .andExpect(view().name("rating/update"));
    }

    /**
     * Test to get the form to add a rating
     */
    @Test
    public void getratingAddTest() throws Exception {

        // GIVEN
        // WHEN
        // THEN
        this.mockMvc
                .perform(get("/rating/add").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /**
     * Test to get the form to update a rating
     */
    @Test
    public void getUpdateratingTest() throws Exception {

        // GIVEN
        Rating rating = new Rating();
        rating.setMoodysRating("AA");
        rating.setFitchRating("BB");
        rating.setSandPRating("CC");
        rating.setOrderNumber(1);
       
        Optional<Rating> ratingMock = Optional.of(rating);

        Mockito.when(ratingRepository.findById(any(Integer.class))).thenReturn(ratingMock);

        // WHEN
        // THEN
        this.mockMvc.perform(
                get("/rating/update/1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /**
     * Test to delete a rating
     */
    @Test
    public void getDeleteRatingTest() throws Exception {

        // GIVEN
        // GIVEN
        Rating rating = new Rating();
        rating.setMoodysRating("AA");
        rating.setFitchRating("BB");
        rating.setSandPRating("CC");
        rating.setOrderNumber(1);
       
        Optional<Rating> ratingMock = Optional.of(rating);

        Mockito.when(ratingRepository.findById(any(Integer.class))).thenReturn(ratingMock);


        // WHEN
        // THEN
        this.mockMvc
                .perform(get("/rating/delete/1").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/rating/list")).andExpect(status().is3xxRedirection());
    }

    /**
     * Test to delete a rating, with an id rating not existing
     */
    @Test
    public void getDeleteRatingNotExistingTest() throws Exception {

        // GIVEN
        Rating rating = null;
        Optional<Rating> ratingMock = Optional.ofNullable(rating);

        Mockito.when(ratingRepository.findById(any(Integer.class))).thenReturn(ratingMock);

        // WHEN
        // THEN
        try {
            this.mockMvc.perform(get("/rating/delete/1").contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON));
        } catch (Exception e) {
            assertThat(e).hasMessageContaining("Invalid rating Id:1");
        }
    }


}
