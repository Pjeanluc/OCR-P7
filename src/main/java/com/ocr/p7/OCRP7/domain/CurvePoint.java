package com.ocr.p7.OCRP7.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "curvepoint")
public class CurvePoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    Integer curveId;
    private Timestamp asOfDate;
    @NotNull(message = "term is mandatory")
    private Double term;
    private Double value;
    private Timestamp creationDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCurveId() {
        return curveId;
    }

    public void setCurveId(Integer curveId) {
        this.curveId = curveId;
    }

    public Timestamp getAsOfDate() {
        Timestamp localTimestamp = asOfDate;
        return localTimestamp;
    }

    public void setAsOfDate(Timestamp asOfDate) {
        if (asOfDate == null) {
            this.asOfDate = null;
        } else {
            this.asOfDate = new Timestamp(asOfDate.getTime());
        }
    }

    public Double getTerm() {
        return term;
    }

    public void setTerm(Double term) {
        this.term = term;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
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

}
