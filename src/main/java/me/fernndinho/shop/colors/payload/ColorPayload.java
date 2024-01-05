package me.fernndinho.shop.colors.payload;

import lombok.*;
import me.fernndinho.shop.colors.models.ColorEntity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
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
