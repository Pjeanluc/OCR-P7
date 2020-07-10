package com.ocr.p7.OCRP7.domain;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.sql.Timestamp;

@Entity
@Table(name = "bidlist")
public class BidList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "TINYINT")
    private Integer BidListId;
    @NotBlank(message = "Account is mandatory")
    @Size(max = 30)
    private String account;
    @NotBlank(message = "Type is mandatory")
    @Size(max = 30)
    private String type;
    @Min(1)
    private Double bidQuantity;
    private Double askQuantity;
    private Double bid;
    private Double ask;
    @Size(max = 125)
    private String benchmark;

    private Timestamp bidListDate;
    @Size(max = 125)
    private String commentary;
    @Size(max = 125)
    private String security;
    @Size(max = 10)
    private String status;
    @Size(max = 125)
    private String trader;
    @Size(max = 125)
    private String book;
    @Size(max = 125)
    private String creationName;
    private Timestamp creationDate;
    @Size(max = 125)
    private String revisionName;
    private Timestamp revisionDate;
    @Size(max = 125)
    private String dealName;
    private String dealType;
    @Size(max = 125)
    private String sourceListId;
    @Size(max = 125)
    private String side;

    public Integer getBidListId() {
        return BidListId;
    }

    public void setBidListId(Integer bidListId) {
        BidListId = bidListId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getBidQuantity() {
        return bidQuantity;
    }

    public void setBidQuantity(Double bidQuantity) {
        this.bidQuantity = bidQuantity;
    }

    public Double getAskQuantity() {
        return askQuantity;
    }

    public void setAskQuantity(Double askQuantity) {
        this.askQuantity = askQuantity;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public String getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public Timestamp getBidListDate() {
        Timestamp localTimestamp = bidListDate;
        return localTimestamp;
    }

    public void setBidListDate(Timestamp bidListDate) {
        if (bidListDate == null) {
            this.bidListDate = null;
        } else {
            this.bidListDate = new Timestamp(bidListDate.getTime());
        }
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTrader() {
        return trader;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getCreationName() {
        return creationName;
    }

    public void setCreationName(String creationName) {
        this.creationName = creationName;
    }

    public Timestamp getCreationDate() {
        Timestamp localTimestamp = creationDate;
        return localTimestamp;
    }

    public void setCreationDate(Timestamp creationDate) {
        if (creationDate == null) {
            this.creationDate = null;
        } else {
            this.creationDate = new Timestamp(creationDate.getTime());
        }
    }

    public String getRevisionName() {
        return revisionName;
    }

    public void setRevisionName(String revisionName) {
        this.revisionName = revisionName;
    }

    public Timestamp getRevisionDate() {
        Timestamp localTimestamp = revisionDate;
        return localTimestamp;
    }

    public void setRevisionDate(Timestamp revisionDate) {
        if (revisionDate == null) {
            this.revisionDate = null;
        } else {
            this.revisionDate = new Timestamp(revisionDate.getTime());
        }        
    }

    public String getDealName() {
        return dealName;
    }

    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getSourceListId() {
        return sourceListId;
    }

    public void setSourceListId(String sourceListId) {
        this.sourceListId = sourceListId;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

}
