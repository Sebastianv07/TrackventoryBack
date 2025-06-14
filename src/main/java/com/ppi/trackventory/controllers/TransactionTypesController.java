package com.ppi.trackventory.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ppi.trackventory.models.TransactionTypes;
import com.ppi.trackventory.services.impl.TransactionTypesService;

@RestController
@RequestMapping("/transactionTypes")
@CrossOrigin("*")
public class TransactionTypesController {

    @Autowired
    private TransactionTypesService transactionTypesService;

    // Obtener todos los tipos de transacción
    @GetMapping
    public ResponseEntity<List<TransactionTypes>> getAllTransactionTypes() {
        List<TransactionTypes> types = transactionTypesService.getAllTransactionTypes();
        return new ResponseEntity<>(types, HttpStatus.OK);
    }

    // Obtener un tipo de transacción por ID
    @GetMapping("/{id}")
    public ResponseEntity<TransactionTypes> getTransactionTypeById(@PathVariable Integer id) {
        try {
            TransactionTypes type = transactionTypesService.getTransactionTypeById(id);
            return new ResponseEntity<>(type, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Crear un nuevo tipo de transacción
    @PostMapping
    public ResponseEntity<TransactionTypes> createTransactionType(@RequestBody TransactionTypes transactionType) {
        try {
            TransactionTypes newType = transactionTypesService.saveTransactionType(transactionType);
            return new ResponseEntity<>(newType, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Actualizar un tipo de transacción existente
    @PutMapping("/{id}")
    public ResponseEntity<TransactionTypes> updateTransactionType(@PathVariable Integer id,
            @RequestBody TransactionTypes updatedType) {
        try {
            TransactionTypes existingType = transactionTypesService.getTransactionTypeById(id);
            existingType.setName(updatedType.getName());
            existingType.setDescription(updatedType.getDescription());
            TransactionTypes savedType = transactionTypesService.saveTransactionType(existingType);
            return new ResponseEntity<>(savedType, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un tipo de transacción por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTransactionType(@PathVariable Integer id) {
        try {
            transactionTypesService.deleteTransactionTypeById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
