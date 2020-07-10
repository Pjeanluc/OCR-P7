package com.ocr.p7.OCRP7.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(columnDefinition = "TINYINT")
    private Integer id;
    @NotBlank(message = "moody rating is mandatory")
    @Column(length = 125)
    private String moodysRating;
    @NotBlank(message = "sandPRating is mandatory")
    @Column(length = 125)
    private String sandPRating;
    @NotBlank(message = "fitch rating is mandatory")
    @Column(length = 125)
    private String fitchRating;
    @Column(columnDefinition = "TINYINT")
    private Integer orderNumber;
        
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getMoodysRating() {
        return moodysRating;
    }
    public void setMoodysRating(String moodysRating) {
        this.moodysRating = moodysRating;
    }
    public String getSandPRating() {
        return sandPRating;
    }
    public void setSandPRating(String sandPRating) {
        this.sandPRating = sandPRating;
    }
    public String getFitchRating() {
        return fitchRating;
    }
    public void setFitchRating(String fitchRating) {
        this.fitchRating = fitchRating;
    }
    public Integer getOrderNumber() {
        return orderNumber;
    }
    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
          
}
