package com.ocr.p7.OCRP7.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ocr.p7.OCRP7.domain.RuleName;
import com.ocr.p7.OCRP7.repositories.RuleNameRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class RuleNameRepositoryTest {
    @Autowired
    private RuleNameRepository ruleNameRepository;

    @Test
    public void ruleNameTest() {
        RuleName ruleName = new RuleName();
        ruleName.setId(1);
        ruleName.setName("name");
        ruleName.setDescription("description");
        ruleName.setTemplate("template");
        ruleName.setJson("json");
        ruleName.setSqlPart("sqlPart");
        ruleName.setSqlStr("sqlStr");
        // Save
        ruleName = ruleNameRepository.save(ruleName);
        Assert.assertNotNull(ruleName.getId());
        Assert.assertEquals(ruleName.getName(), "name", "name");

        // Update
        ruleName.setName("newName");
        ruleName = ruleNameRepository.save(ruleName);
        Assert.assertEquals(ruleName.getName(), "newName", "newName");

        // Find
        List<RuleName> listResult = ruleNameRepository.findAll();
        Assert.assertTrue(listResult.size() > 0);

        // Delete
        Integer id = ruleName.getId();
        ruleNameRepository.delete(ruleName);
        Optional<RuleName> RuleNameList = ruleNameRepository.findById(id);
        Assert.assertFalse(RuleNameList.isPresent());
    }

}
