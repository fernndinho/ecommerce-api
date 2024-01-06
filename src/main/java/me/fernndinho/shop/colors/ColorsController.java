package me.fernndinho.shop.colors;

import me.fernndinho.shop.colors.payload.ColorPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/colors/")
public class ColorsController {
    @Autowired
    private ColorService service;

    @GetMapping("/getall")
    public ResponseEntity<List<ColorPayload>> getAllColors() {
        List<ColorPayload> colors = service.getAll();

        return ResponseEntity.ok(colors);
    }

    @GetMapping("/get/{slug}")
    public ResponseEntity<ColorPayload> getColor(@PathVariable("slug") String slug) {
        ColorPayload color = service.getColor(slug);

        return ResponseEntity.ok(color);
    }

    @PostMapping("/create")
    public ResponseEntity<ColorPayload> createColor(@Validated @RequestBody ColorPayload color) {
        ColorPayload createdColor = service.create(color);

        return ResponseEntity.status(201).body(createdColor);
    }

    //TODO: add an endpoit to update colors

    @DeleteMapping("/remove/{slug}")
    public ResponseEntity<Void> removeColor(@PathVariable("slug") String slug) {
        service.delete(slug);

        return ResponseEntity.noContent().build();
    }
}
