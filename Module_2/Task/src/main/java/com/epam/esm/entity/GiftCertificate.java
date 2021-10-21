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
    private Date createDate;
    private Date lastUpdateDate;
    private List<Tag> tags;

    public GiftCertificate(long id, String name, String description, BigDecimal price, int duration,
                           Date createDate, Date lastUpdateDate, List<Tag> tags){
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
