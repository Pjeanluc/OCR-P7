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

import com.ocr.p7.OCRP7.domain.Rating;
import com.ocr.p7.OCRP7.repositories.RatingRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class RatingRepositoryTest {

    @Autowired
    private RatingRepository ratingRepository;

    @Test
    public void ratingTest() {
        Rating rating = new Rating();
        rating.setId(1);
        rating.setFitchRating("AA");
        rating.setMoodysRating("BB");;
        rating.setSandPRating("CC");
        rating.setOrderNumber(1);
        // Save
        rating = ratingRepository.save(rating);
        Assert.assertNotNull(rating.getId());
        Assert.assertEquals(rating.getMoodysRating(), "BB", "BB");

        // Update
        rating.setMoodysRating("EE");
        rating = ratingRepository.save(rating);
        Assert.assertEquals(rating.getMoodysRating(), "CC", "CC");

        // Find
        List<Rating> listResult = ratingRepository.findAll();
        Assert.assertTrue(listResult.size() > 0);

        // Delete
        Integer id = rating.getId();
        ratingRepository.delete(rating);
        Optional<Rating> RatingList = ratingRepository.findById(id);
        Assert.assertFalse(RatingList.isPresent());
    }
}
