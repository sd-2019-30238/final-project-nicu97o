package com.shoppinglist.model.database;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table
@Entity
@EqualsAndHashCode
public class WantedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column
    private boolean bought;

    @Column
    private boolean constrained;

    @OneToOne(mappedBy = "wantedProduct")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private ProductConstraint productConstraint;

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "userWhichPostedId")
    @EqualsAndHashCode.Exclude
    private User userWhichPosted;

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "clubId")
    @EqualsAndHashCode.Exclude
    private Club club;

    public WantedProduct(Long id, String name, String description, Category category, boolean bought, boolean constrained) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.bought = bought;
        this.constrained = constrained;
    }
}
