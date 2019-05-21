package com.shoppinglist.model.database;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table
@Entity
@EqualsAndHashCode
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
    @EqualsAndHashCode.Exclude
    private WantedProduct wantedProduct;

    @OneToOne
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "shopId")
    @EqualsAndHashCode.Exclude
    private Shop shop;

    public ProductConstraint(Long id, String name, String message, ConstraintsType constraintsType) {
        this.id = id;
        this.name = name;
        this.message = message;
        this.constraintsType = constraintsType;
    }
}
