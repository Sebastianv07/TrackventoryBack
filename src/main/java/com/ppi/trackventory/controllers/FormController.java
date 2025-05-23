package com.ppi.trackventory.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ppi.trackventory.models.Form;
import com.ppi.trackventory.services.impl.FormService;

@RestController
@RequestMapping("/forms")
@CrossOrigin("*")
public class FormController {

    @Autowired
    private FormService formService;

    // Obtener todos los menus y submenus
    @GetMapping
    public ResponseEntity<List<Form>> getAllFormsTree() {
        List<Form> formsTree = formService.getFormTree();
        return new ResponseEntity<>(formsTree, HttpStatus.OK);
    }

    // Obtener un formulario por su URL
    @GetMapping("/{id}")
    public ResponseEntity<Form> getFormByUrl(@PathVariable Integer id) {
        Optional<Form> form = formService.getFormById(id);
        return form.map(f -> new ResponseEntity<>(f, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Crear un nuevo formulario
    @PostMapping
    public ResponseEntity<Form> createForm(@RequestBody Form form) {
        Form newForm = formService.saveForm(form);
        return new ResponseEntity<>(newForm, HttpStatus.CREATED);
    }

    // Actualizar un formulario existente
    @PutMapping("/{id}")
    public ResponseEntity<Form> updateForm(@PathVariable Integer id, @RequestBody Form updatedForm) {
        Optional<Form> formData = formService.getFormById(id);

        if (formData.isPresent()) {
            Form form = formData.get();
            form.setUrl(updatedForm.getUrl());
            form.setName(updatedForm.getName());
            form.setIcon(updatedForm.getIcon());
            formService.saveForm(form);
            return new ResponseEntity<>(form, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un formulario por su URL
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteForm(@PathVariable Integer id) {
        try {
            formService.deleteFormById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
