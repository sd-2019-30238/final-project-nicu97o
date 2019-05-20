package com.shoppinglist.model.database;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table
@Entity
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(unique = true)
    @Size(max = 100)
    private String inviteCode;

    @OneToMany(mappedBy = "club")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<WantedProduct> wantedProducts = new ArrayList<>();

    @ManyToMany(mappedBy = "clubs", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<User> users = new ArrayList<>();
}
