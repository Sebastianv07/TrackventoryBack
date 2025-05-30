package com.ppi.trackventory.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ppi.trackventory.models.Form;

public interface FormRepository extends JpaRepository<Form, Integer> {

    List<Form> findByParentIsNull();

    
    @Query("SELECT MAX(f.id) FROM Form f")
    Integer findMaxId();

}
