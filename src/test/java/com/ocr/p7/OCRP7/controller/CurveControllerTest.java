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

import com.ocr.p7.OCRP7.domain.CurvePoint;
import com.ocr.p7.OCRP7.repositories.CurvePointRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
class CurveControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurvePointRepository curvePointRepository;

    /**
     * the to show the list of curve
     */
    @Test
    public void getAllCurvePointListControllerTest() throws Exception {

        // GIVEN
        // WHEN
        // THEN
        this.mockMvc.perform(
                get("/curvePoint/list").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /**
     * Test to validate a curvePoint
     */
    @Test
    public void postValidateCurvePointTest() throws Exception {

        // GIVEN
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(1);
        curvePoint.setTerm(1.1d);
        curvePoint.setValue(2.2d);

        Optional<CurvePoint> curveMock = Optional.of(curvePoint);

        Mockito.when(curvePointRepository.findById(any(Integer.class))).thenReturn(curveMock);

        // WHEN
        // THEN
        this.mockMvc.perform(post("/curvePoint/validate").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).param("curveId", curvePoint.getCurveId().toString())
                .param("term", curvePoint.getTerm().toString()).param("value", curvePoint.getValue().toString()))
                .andDo(print()).andExpect(MockMvcResultMatchers.redirectedUrl("/curvePoint/list"))
                .andExpect(status().is3xxRedirection());
    }

    /**
     * Test to validate a curvePoint with an error (value)
     */
    @Test
    public void postValidateCurvePointWithErrorTest() throws Exception {

        // GIVEN
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(1);
        curvePoint.setTerm(1.1d);
       
        Optional<CurvePoint> curveMock = Optional.of(curvePoint);

        Mockito.when(curvePointRepository.findById(any(Integer.class))).thenReturn(curveMock);

        // WHEN
        // THEN
        this.mockMvc
                .perform(post("/curvePoint/validate").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).param("curveId", curvePoint.getCurveId().toString())
                        .param("term", curvePoint.getTerm().toString()).param("value", "error"))
                .andDo(print()).andExpect(status().isOk()).andExpect(view().name("curvePoint/add"));

    }

    /**
     * Test to validate a curvePoint update
     */

    @Test
    public void postUpdateCurvePointTest() throws Exception {

        // GIVEN
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(1);
        curvePoint.setTerm(1.1d);
        curvePoint.setValue(2.2d);

        Optional<CurvePoint> curveMock = Optional.of(curvePoint);

        Mockito.when(curvePointRepository.findById(any(Integer.class))).thenReturn(curveMock);

        // WHEN
        // THEN

        this.mockMvc
                .perform(post("/curvePoint/update/1").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).param("curveId", curvePoint.getCurveId().toString())
                        .param("term", curvePoint.getTerm().toString()).param("value", curvePoint.getValue().toString()))
                .andDo(print()).andExpect(MockMvcResultMatchers.redirectedUrl("/curvePoint/list"))
                .andExpect(status().is3xxRedirection());
    }

    /**
     * Test to validate a curvePoint update with an error (value)
     */
    @Test
    public void postUpdateCurvePointWithWrongParametersTest() throws Exception {

        // GIVEN
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(1);
        curvePoint.setTerm(1.1d);
       
        Optional<CurvePoint> curveMock = Optional.of(curvePoint);

        Mockito.when(curvePointRepository.findById(any(Integer.class))).thenReturn(curveMock);

        // WHEN
        // THEN
        this.mockMvc
                .perform(post("/curvePoint/update/1").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .param("curveId", curvePoint.getCurveId().toString())
                        .param("term", curvePoint.getTerm().toString()).param("value", "error"))
                .andExpect(MockMvcResultMatchers.redirectedUrl(null)).andExpect(status().isOk())
                .andExpect(view().name("curvePoint/update"));
    }

    /**
     * Test to get the form to add a curvePoint
     */
    @Test
    public void getcurvePointAddTest() throws Exception {

        // GIVEN
        // WHEN
        // THEN
        this.mockMvc
                .perform(get("/curvePoint/add").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /**
     * Test to get the form to update a curvePoint
     */
    @Test
    public void getUpdatecurvePointTest() throws Exception {

        // GIVEN
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(1);
        curvePoint.setTerm(1.1d);
       
        Optional<CurvePoint> curveMock = Optional.of(curvePoint);

        Mockito.when(curvePointRepository.findById(any(Integer.class))).thenReturn(curveMock);

        // WHEN
        // THEN
        this.mockMvc.perform(
                get("/curvePoint/update/1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /**
     * Test to delete a curvePoint
     */
    @Test
    public void getDeleteCurvePointTest() throws Exception {

        // GIVEN
        // GIVEN
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(1);
        curvePoint.setTerm(1.1d);
       
        Optional<CurvePoint> curveMock = Optional.of(curvePoint);

        Mockito.when(curvePointRepository.findById(any(Integer.class))).thenReturn(curveMock);


        // WHEN
        // THEN
        this.mockMvc
                .perform(get("/curvePoint/delete/1").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/curvePoint/list")).andExpect(status().is3xxRedirection());
    }

    /**
     * Test to delete a curvePoint, with an id curvePoint not existing
     */
    @Test
    public void getDeleteCurvePointNotExistingTest() throws Exception {

        // GIVEN
        CurvePoint curvePoint = null;
        Optional<CurvePoint> curvePointMock = Optional.ofNullable(curvePoint);

        Mockito.when(curvePointRepository.findById(any(Integer.class))).thenReturn(curvePointMock);

        // WHEN
        // THEN
        try {
            this.mockMvc.perform(get("/curvePoint/delete/1").contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON));
        } catch (Exception e) {
            assertThat(e).hasMessageContaining("Invalid curvePoint Id:1");
        }
    }

}
