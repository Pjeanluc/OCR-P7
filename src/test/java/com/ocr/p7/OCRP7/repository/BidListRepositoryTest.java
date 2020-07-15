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
        bid.setAsk(11d);
        bid.setAskQuantity(12d);
        bid.setBenchmark("benchmark");
        bid.setBook("book");
        bid.setCommentary("commentary");
        bid.setDealName("dealName");
        bid.setDealType("dealType");
        bid.setRevisionName("revisionName");
        bid.setSecurity("security");
        bid.setSide("side");
        bid.setSourceListId("sourceListId");
        bid.setStatus("status");

        // Save
        bid = bidListRepository.save(bid);
        Assert.assertNotNull(bid.getBidListId());
        Assert.assertEquals(bid.getAccount(),"Account Test","Account Test");
        Assert.assertEquals(bid.getBidQuantity(), 10d, 10d);
        Assert.assertEquals(bid.getAsk(),11d,11d);
        Assert.assertEquals(bid.getAskQuantity(),12d,12d);
        Assert.assertEquals(bid.getBenchmark(),"benchmark","benchmark");
        Assert.assertEquals(bid.getBook(),"book","book");
        Assert.assertEquals(bid.getCommentary(),"commentary","commentary");
        Assert.assertEquals(bid.getDealName(),"dealName","dealName");
        Assert.assertEquals(bid.getDealType(),"dealType","dealType");
        Assert.assertEquals(bid.getRevisionName(),"revisionName","revisionName");
        Assert.assertEquals(bid.getSecurity(),"security","security");
        Assert.assertEquals(bid.getSide(),"side","side");
        Assert.assertEquals(bid.getSourceListId(),"sourceListId","sourceListId");
        Assert.assertEquals(bid.getStatus(),"status","status");

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
