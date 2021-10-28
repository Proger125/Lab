package edu.epam.esm.task.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends Entity{
    private String firstName;
    private String lastName;
    private String email;

    public User(long id, String firstName, String lastName, String email){
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
