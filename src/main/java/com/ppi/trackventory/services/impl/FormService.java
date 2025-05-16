package com.ppi.trackventory.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppi.trackventory.models.Form;
import com.ppi.trackventory.repositories.FormRepository;

@Service
public class FormService {

    @Autowired
    private FormRepository formRepository;

    // Crear o actualizar un formulario
    public Form saveForm(Form form) {
        return formRepository.save(form);
    }

    // Obtener todos los formularios
    public List<Form> getAllForms() {
        return formRepository.findAll();
    }

    // Aqui se implementa la creacion del mapper para submenus
    public List<Form> getFormTree() {
        List<Form> allForms = formRepository.findAll();

        Map<Integer, Form> map = new HashMap<>();
        for (Form f : allForms) {
            if (f.getChildren() == null) {
                f.setChildren(new ArrayList<>());
            }
            map.put(f.getId(), f);
        }

        List<Form> roots = new ArrayList<>();

        for (Form f : allForms) {
            if (f.getParent() == null) {
                roots.add(f);
            } else {
                Form parent = map.get(f.getParent().getId());
                if (parent != null) {
                    parent.getChildren().add(f);
                }
            }
        }

        return roots;
    }

    // Obtener un formulario por URL
    public Optional<Form> getFormById(Integer id) {
        return formRepository.findById(id);
    }

    // Eliminar un formulario por URL
    public void deleteFormById(Integer id) {
        formRepository.deleteById(id);
    }
}
