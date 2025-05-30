package com.ppi.trackventory.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ppi.trackventory.models.Form;
import com.ppi.trackventory.services.impl.FormService;

@RestController
@RequestMapping("/forms")
@CrossOrigin({ "*", "http://localhost:4200", "https://ppi-front-83902661050.us-east1.run.app" })
public class FormController {

    @Autowired
    private FormService formService;

    // Obtener todos los formularios en forma de árbol
    @GetMapping
    public ResponseEntity<List<Form>> getAllFormsTree() {
        List<Form> formsTree = formService.getFormTree();
        return ResponseEntity.ok(formsTree);
    }

    // Obtener un formulario por su id
    @GetMapping("/{id}")
    public ResponseEntity<Form> getFormById(@PathVariable Integer id) {
        Optional<Form> form = formService.getFormById(id);
        return form.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo formulario
    @PostMapping
    public ResponseEntity<Form> createForm(@RequestBody Form form) {
        if (form == null) {
            return ResponseEntity.badRequest().build();
        }
        Form newForm = formService.saveForm(form);
        return new ResponseEntity<>(newForm, HttpStatus.CREATED);
    }

    // Actualizar un formulario existente
    @PutMapping("/{id}")
    public ResponseEntity<Form> updateForm(@PathVariable Integer id, @RequestBody Form updatedForm) {
        Optional<Form> formData = formService.getFormById(id);

        if (formData.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Form form = formData.get();

        form.setUrl(updatedForm.getUrl());
        form.setName(updatedForm.getName());
        form.setIcon(updatedForm.getIcon());

        if (updatedForm.getChildren() != null && !updatedForm.getChildren().isEmpty()) {
            for (Form child : updatedForm.getChildren()) {
                if (child.getId() == null || child.getId() == 0) {
                    // Paso 1: guardar sin el padre (null)
                    Integer nextId = formService.getMaxId() + 10;
                    child.setId(nextId);
                    child.setParent(null);
                    Form savedChild = formService.saveForm(child);

                    // Paso 2: asignar el padre y guardar de nuevo
                    savedChild.setParent(form);
                    formService.saveForm(savedChild);
                } else {
                    // Actualizar hijos existentes si lo deseas
                    Optional<Form> existingChildOpt = formService.getFormById(child.getId());
                    if (existingChildOpt.isPresent()) {
                        Form existingChild = existingChildOpt.get();
                        existingChild.setName(child.getName());
                        existingChild.setUrl(child.getUrl());
                        existingChild.setIcon(child.getIcon());
                        // Asegurarse que el padre es correcto
                        existingChild.setParent(form);
                        formService.saveForm(existingChild);
                    }
                }
            }
        }

        // Si no hay hijos enviados, se limpian
        if (updatedForm.getChildren() == null || updatedForm.getChildren().isEmpty()) {
            form.setChildren(null);
        }

        Form savedForm = formService.saveForm(form);
        return ResponseEntity.ok(savedForm);
    }

    // Eliminar un formulario por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteForm(@PathVariable Integer id) {
        try {
            if (!formService.existsById(id)) {
                return ResponseEntity.notFound().build();
            }
            formService.deleteFormById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            // Aquí se podría loguear el error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
