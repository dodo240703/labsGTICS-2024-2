package com.example.lab3.Repositories;

import com.example.lab3.Entities.RecetaIngrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetaIngredienteRepository extends JpaRepository<RecetaIngrediente, Integer> {
}
