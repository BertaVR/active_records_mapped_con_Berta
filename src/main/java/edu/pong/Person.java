package edu.pong;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.smallrye.common.constraint.NotNull;


@Entity
@Table(name="Person")
public class Person extends PanacheEntityBase{
    
    @Id
    @NotNull
    @Column(unique = true)
    public String dni;

    @NotNull
    public String name;

    public Person() {
    }

    public Person(String dni, String name) {
        this.dni = dni;
        this.name = name;
    }

}