package com.epam.esm.entity;

import lombok.*;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GiftCertificate extends Entity{
    private String name;
    private String description;
    private BigDecimal price;
    private int duration;
    private DateTime createDate;
    private DateTime lastUpdateDate;
    private List<Tag> tags;

    public GiftCertificate(long id, String name, String description, BigDecimal price, int duration,
                           DateTime createDate, DateTime lastUpdateDate, List<Tag> tags){
        super(id);
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.tags = List.copyOf(tags);
    }
}
