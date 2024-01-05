package me.fernndinho.shop.colors.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "color")
public class ColorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String slug;

    private String hex;
}
