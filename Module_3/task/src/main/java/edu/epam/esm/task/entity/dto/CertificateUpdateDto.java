package edu.epam.esm.task.entity.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CertificateUpdateDto {
    private Integer duration;
    private BigDecimal price;
    private Date updateDate;
}
