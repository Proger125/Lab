package edu.epam.esm.task.entity.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CertificateUpdateDto {
    private Integer duration;
    private BigDecimal price;
}
