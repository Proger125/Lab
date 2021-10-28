package edu.epam.esm.task.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag extends Entity{
    private String name;

    public Tag(long id, String name){
        super(id);
        this.name = name;
    }
}
