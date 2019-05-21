package com.shoppinglist.model.database;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table
@Entity
@EqualsAndHashCode
public class Invitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String inviteCode;

    @Column
    private boolean used;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "senderId")
    @EqualsAndHashCode.Exclude
    private User sender;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "receiverId")
    @EqualsAndHashCode.Exclude
    private User receiver;

    public Invitation(Long id, String inviteCode, boolean used) {
        this.id = id;
        this.inviteCode = inviteCode;
        this.used = used;
    }
}
