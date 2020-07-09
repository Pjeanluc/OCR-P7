package com.ocr.p7.OCRP7.repository;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ocr.p7.OCRP7.domain.CurvePoint;
import com.ocr.p7.OCRP7.repositories.CurvePointRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class CurvePointRepositoryTest {
    @Autowired
    private CurvePointRepository curvePointRepository;

    @Test
    public void curvePointTest() {
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(1);
        curvePoint.setCurveId(1);
        curvePoint.setTerm(1.1d);
        curvePoint.setValue(1.1d);

        // Save
        curvePoint = curvePointRepository.save(curvePoint);
        Assert.assertNotNull(curvePoint.getId());
        Assert.assertEquals(curvePoint.getTerm(), 1.1d, 1.1d);

        // Update
        curvePoint.setTerm(20d);
        curvePoint = curvePointRepository.save(curvePoint);
        Assert.assertEquals(curvePoint.getTerm(), 20d, 20d);

        // Find
        List<CurvePoint> listResult = curvePointRepository.findAll();
        Assert.assertTrue(listResult.size() > 0);

        // Delete
        Integer id = curvePoint.getId();
        curvePointRepository.delete(curvePoint);
        Optional<CurvePoint> curvePointList = curvePointRepository.findById(id);
        Assert.assertFalse(curvePointList.isPresent());
    }
}
