package com.shoppinglist.model.database;

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
public class ProductConstraint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String message;

    @Column
    @Enumerated(EnumType.STRING)
    private ConstraintsType constraintsType;

    @OneToOne(fetch = FetchType.LAZY)
    @Cascade({CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "wantedProductId")
    private WantedProduct wantedProduct;

    @OneToOne
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "shopId")
    private Shop shop;
}
