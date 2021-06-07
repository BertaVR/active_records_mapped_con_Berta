package edu.pong;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.smallrye.common.constraint.NotNull;

@Entity
@Table(name = "Orders")
public class Orders extends PanacheEntityBase{
    
    @Id
    @NotNull
    @Column(unique = true)
    public String id;

    @OneToOne
    @JoinColumn(name = "person")
    public Person person;

    @OneToOne
    @JoinColumn(name = "product")
    public Product product;
}
