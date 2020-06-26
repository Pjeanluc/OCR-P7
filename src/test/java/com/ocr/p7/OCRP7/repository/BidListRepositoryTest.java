package com.ocr.p7.OCRP7.repository;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import com.ocr.p7.OCRP7.domain.BidList;
import com.ocr.p7.OCRP7.repositories.BidListRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class BidListRepositoryTest {

    @Autowired
    private BidListRepository bidListRepository;

    @Test
    public void bidListTest() {
        BidList bid = new BidList();
        bid.setAccount("Account Test");
        bid.setType("Type Test");
        bid.setBidQuantity(10d);

        // Save
        bid = bidListRepository.save(bid);
        Assert.assertNotNull(bid.getBidListId());
        Assert.assertEquals(bid.getBidQuantity(), 10d, 10d);

        // Update
        bid.setBidQuantity(20d);
        bid = bidListRepository.save(bid);
        Assert.assertEquals(bid.getBidQuantity(), 20d, 20d);

        // Find
        List<BidList> listResult = bidListRepository.findAll();
        Assert.assertTrue(listResult.size() > 0);

        // Delete
        Integer id = bid.getBidListId();
        bidListRepository.delete(bid);
        Optional<BidList> bidList = bidListRepository.findById(id);
        Assert.assertFalse(bidList.isPresent());
    }
}
