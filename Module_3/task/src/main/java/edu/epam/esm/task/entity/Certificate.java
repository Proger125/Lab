package edu.epam.esm.task.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Certificate extends Entity{
    private String name;
    private String description;
    private BigDecimal price;
    private int duration;
    private Date createDate;
    private Date lastUpdateDate;
    private List<Tag> tags;

    public Certificate(long id, String name, String description, BigDecimal price, int duration,
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
