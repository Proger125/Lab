package com.epam.esm.entity;

import lombok.*;


@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tag extends Entity{
    private String name;

    public Tag(long id, String name){
        super(id);
        this.name = name;
    }
}
