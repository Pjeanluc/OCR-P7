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

import com.ocr.p7.OCRP7.domain.RuleName;
import com.ocr.p7.OCRP7.repositories.RuleNameRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
class RuleNameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RuleNameRepository ruleNameRepository;

    /**
     * the to show the list of ruleName
     */
    @Test
    public void getAllRuleNameListControllerTest() throws Exception {

        // GIVEN
        // WHEN
        // THEN
        this.mockMvc.perform(
                get("/ruleName/list").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /**
     * Test to validate a ruleName
     */
    @Test
    public void postValidateRuleNameTest() throws Exception {

        // GIVEN
        RuleName ruleName = new RuleName();
        ruleName.setName("Name");
        ruleName.setDescription("Description");
        ruleName.setJson("json");
        ruleName.setTemplate("Template");
        ruleName.setSqlStr("SQLStr");
        ruleName.setSqlPart("SQLPart");

        Optional<RuleName> ruleNameMock = Optional.of(ruleName);

        Mockito.when(ruleNameRepository.findById(any(Integer.class))).thenReturn(ruleNameMock);

        // WHEN
        // THEN
        this.mockMvc
                .perform(post("/ruleName/validate").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).param("Name", ruleName.getName())
                        .param("description", ruleName.getDescription()).param("json", ruleName.getJson())
                        .param("template", ruleName.getTemplate()).param("sqlStr", ruleName.getSqlStr())
                        .param("sqlPart", ruleName.getSqlPart()))
                .andDo(print()).andExpect(MockMvcResultMatchers.redirectedUrl("/ruleName/list"))
                .andExpect(status().is3xxRedirection());
    }

    /**
     * Test to validate a ruleName with an error (Name)
     */
    @Test
    public void postValidateRuleNameWithErrorTest() throws Exception {

        // GIVEN
        RuleName ruleName = new RuleName();
        
        ruleName.setDescription("Description");
        ruleName.setJson("json");
        ruleName.setTemplate("Template");
        ruleName.setSqlStr("SQLStr");
        ruleName.setSqlPart("SQLPart");

        Optional<RuleName> ruleNameMock = Optional.of(ruleName);

        Mockito.when(ruleNameRepository.findById(any(Integer.class))).thenReturn(ruleNameMock);

        // WHEN
        // THEN
        this.mockMvc
                .perform(post("/ruleName/validate").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).param("name", ruleName.getName())
                        .param("description", ruleName.getDescription()).param("json", ruleName.getJson())
                        .param("template", ruleName.getTemplate()).param("sqlStr", ruleName.getSqlStr())
                        .param("sqlPart", ruleName.getSqlPart()))
                .andDo(print()).andExpect(status().isOk()).andExpect(view().name("ruleName/add"));

    }

    /**
     * Test to validate a ruleName update
     */

    @Test
    public void postUpdateRuleNameTest() throws Exception {

        // GIVEN
        RuleName ruleName = new RuleName();
        ruleName.setName("Name");
        ruleName.setDescription("Description");
        ruleName.setJson("json");
        ruleName.setTemplate("Template");
        ruleName.setSqlStr("SQLStr");
        ruleName.setSqlPart("SQLPart");

        Optional<RuleName> ruleNameMock = Optional.of(ruleName);

        Mockito.when(ruleNameRepository.findById(any(Integer.class))).thenReturn(ruleNameMock);

        // WHEN
        // THEN

        this.mockMvc
                .perform(post("/ruleName/update/1").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).param("Name", ruleName.getName())
                        .param("description", ruleName.getDescription()).param("json", ruleName.getJson())
                        .param("template", ruleName.getTemplate()).param("sqlStr", ruleName.getSqlStr())
                        .param("sqlPart", ruleName.getSqlPart()))
                .andDo(print()).andExpect(MockMvcResultMatchers.redirectedUrl("/ruleName/list"))
                .andExpect(status().is3xxRedirection());
    }

    /**
     * Test to validate a ruleName update with an error (Name)
     */
    @Test
    public void postUpdateRuleNameWithWrongParametersTest() throws Exception {

        // GIVEN
        RuleName ruleName = new RuleName();
        ruleName.setName("Name");
        ruleName.setDescription("Description");
        ruleName.setJson("json");
        ruleName.setTemplate("Template");
        ruleName.setSqlStr("SQLStr");
        ruleName.setSqlPart("SQLPart");

        Optional<RuleName> ruleNameMock = Optional.of(ruleName);

        Mockito.when(ruleNameRepository.findById(any(Integer.class))).thenReturn(ruleNameMock);

        // WHEN
        // THEN
        this.mockMvc
                .perform(post("/ruleName/update/1").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .param("description", ruleName.getDescription()).param("json", ruleName.getJson())
                        .param("template", ruleName.getTemplate()).param("sqlStr", ruleName.getSqlStr())
                        .param("sqlPart", ruleName.getSqlPart()))
                .andExpect(MockMvcResultMatchers.redirectedUrl(null)).andExpect(status().isOk())
                .andExpect(view().name("ruleName/update"));
    }

    /**
     * Test to get the form to add a ruleName
     */
    @Test
    public void getruleNameAddTest() throws Exception {

        // GIVEN
        // WHEN
        // THEN
        this.mockMvc
                .perform(
                        get("/ruleName/add").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /**
     * Test to get the form to update a ruleName
     */
    @Test
    public void getUpdateruleNameTest() throws Exception {

        // GIVEN
        RuleName ruleName = new RuleName();
        ruleName.setName("Name");
        ruleName.setDescription("Description");
        ruleName.setJson("json");
        ruleName.setTemplate("Template");
        ruleName.setSqlStr("SQLStr");
        ruleName.setSqlPart("SQLPart");

        Optional<RuleName> ruleNameMock = Optional.of(ruleName);

        Mockito.when(ruleNameRepository.findById(any(Integer.class))).thenReturn(ruleNameMock);

        // WHEN
        // THEN
        this.mockMvc.perform(
                get("/ruleName/update/1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /**
     * Test to delete a ruleName
     */
    @Test
    public void getDeleteRuleNameTest() throws Exception {

        // GIVEN
        // GIVEN
        RuleName ruleName = new RuleName();
        ruleName.setName("Name");
        ruleName.setDescription("Description");
        ruleName.setJson("json");
        ruleName.setTemplate("Template");
        ruleName.setSqlStr("SQLStr");
        ruleName.setSqlPart("SQLPart");

        Optional<RuleName> ruleNameMock = Optional.of(ruleName);

        Mockito.when(ruleNameRepository.findById(any(Integer.class))).thenReturn(ruleNameMock);

        // WHEN
        // THEN
        this.mockMvc
                .perform(get("/ruleName/delete/1").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/ruleName/list"))
                .andExpect(status().is3xxRedirection());
    }

    /**
     * Test to delete a ruleName, with an id ruleName not existing
     */
    @Test
    public void getDeleteRuleNameNotExistingTest() throws Exception {

        // GIVEN
        RuleName ruleName = null;
        Optional<RuleName> ruleNameMock = Optional.ofNullable(ruleName);

        Mockito.when(ruleNameRepository.findById(any(Integer.class))).thenReturn(ruleNameMock);

        // WHEN
        // THEN
        try {
            this.mockMvc.perform(get("/ruleName/delete/1").contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON));
        } catch (Exception e) {
            assertThat(e).hasMessageContaining("Invalid ruleName Id:1");
        }
    }

}
