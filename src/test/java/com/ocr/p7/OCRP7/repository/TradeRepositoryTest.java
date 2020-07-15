package com.ocr.p7.OCRP7.repository;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ocr.p7.OCRP7.domain.Trade;
import com.ocr.p7.OCRP7.repositories.TradeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class TradeRepositoryTest {

    @Autowired
    private TradeRepository tradeRepository;

    @Test
    public void tradeTest() {
        Trade trade = new Trade();
        trade.setTradeId(1);
        trade.setAccount("account");
        trade.setType("type");
        trade.setBuyQuantity(10d);
        
        // Save
        trade = tradeRepository.save(trade);
        Assert.assertNotNull(trade.getTradeId());
        Assert.assertEquals(trade.getAccount(), "account", "account");
        Assert.assertEquals(trade.getType(), "type", "type");
        Assert.assertEquals(trade.getBuyQuantity(), 10d, 10d);

        // Update
        trade.setBuyQuantity(25d);
        trade = tradeRepository.save(trade);
        Assert.assertEquals(trade.getBuyQuantity(), 20d, 20d);

        // Find
        List<Trade> listResult = tradeRepository.findAll();
        Assert.assertTrue(listResult.size() > 0);

        // Delete
        Integer id = trade.getTradeId();
        tradeRepository.delete(trade);
        Optional<Trade> TradeList = tradeRepository.findById(id);
        Assert.assertFalse(TradeList.isPresent());
    }

}
