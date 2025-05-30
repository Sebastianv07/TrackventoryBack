package com.ppi.trackventory.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ppi.trackventory.models.Color;
import com.ppi.trackventory.services.impl.ColorService;

@RestController
@RequestMapping("/colors")
@CrossOrigin("*")
public class ColorController {

    @Autowired
    private ColorService colorService;

    // Obtener todos los colores
    @GetMapping
    public ResponseEntity<List<Color>> getAllColors() {
        List<Color> colors = colorService.getAllColors();
        return new ResponseEntity<>(colors, HttpStatus.OK);
    }

    // Obtener un color por su nombre
    @GetMapping("/{id}")
    public ResponseEntity<Color> getColorByName(@PathVariable Integer id) {
        Optional<Color> color = colorService.getColorById(id);
        return color.map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Crear un nuevo color
    @PostMapping
    public ResponseEntity<Color> createColor(@RequestBody Color color) {
        color.setId(null);
        Color newColor = colorService.saveColor(color);
        return new ResponseEntity<>(newColor, HttpStatus.CREATED);
    }

    // Actualizar un color existente
    @PutMapping("/{id}")
    public ResponseEntity<Color> updateColor(@PathVariable Integer id, @RequestBody Color updatedColor) {
        Optional<Color> colorData = colorService.getColorById(id);

        if (colorData.isPresent()) {
            Color color = colorData.get();
            color.setName(updatedColor.getName());
            color.setHexCode(updatedColor.getHexCode());
            colorService.saveColor(color);
            return new ResponseEntity<>(color, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un color por su nombre
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteColor(@PathVariable Integer id) {
        try {
            colorService.deleteColorById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
