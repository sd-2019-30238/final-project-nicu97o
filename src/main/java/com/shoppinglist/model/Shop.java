package com.shoppinglist.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table
@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @OneToOne
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "addressId")
    private Address address;

    @OneToOne(mappedBy = "shop", fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    private ProductConstraint productConstraint;
}
