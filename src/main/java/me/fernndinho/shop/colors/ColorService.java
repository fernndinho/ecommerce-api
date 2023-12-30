package me.fernndinho.shop.colors;

import me.fernndinho.shop.colors.models.ColorEntity;
import me.fernndinho.shop.colors.payload.ColorPayload;
import me.fernndinho.shop.colors.repo.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ColorService {
    @Autowired
    private ColorRepository repo;

    public List<ColorPayload> getAll() {
        return repo.findAll()
                .stream()
                .map(ColorPayload::new)
                .collect(Collectors.toList());
    }

    public ColorPayload getColor(String slug) {
        return repo.findBySlug(slug)
                .map(ColorPayload::new)
                .orElseThrow(() -> new RuntimeException("color" + slug +" not found"));
    }

    public ColorPayload create(ColorPayload payload) {
        ColorEntity color = new ColorEntity();
        color.setName(payload.getName());
        color.setSlug(payload.getSlug());
        color.setHex(payload.getHex());

        ColorEntity createdColor = repo.save(color);

        return new ColorPayload(createdColor);
    }

    public void delete(String slug) { //TODO: check first if some product has the color and remove from it
        repo.deleteBySlug(slug);
    }
}