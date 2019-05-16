package com.shoppinglist.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String city;

    @Column
    private String street;

    @Column
    private Integer number;

    @Column
    private String postalCode;

    @OneToOne(mappedBy = "address")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Shop shop;

    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "addressCoordinatesId")
    private AddressCoordinates addressCoordinates;
}
