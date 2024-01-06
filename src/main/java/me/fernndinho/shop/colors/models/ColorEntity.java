package me.fernndinho.shop.colors.models;

import lombok.*;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "color")
public class ColorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String slug;
    private String hex;
}
