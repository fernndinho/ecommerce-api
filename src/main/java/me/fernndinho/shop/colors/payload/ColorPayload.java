package me.fernndinho.shop.colors.payload;

import me.fernndinho.shop.colors.models.ColorEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ColorPayload {
    private String name;
    private String slug;
    private String hex;

    public ColorPayload(ColorEntity entity) {
        this.name = entity.getName();
        this.slug = entity.getSlug();
        this.hex = entity.getHex();
    }
}
