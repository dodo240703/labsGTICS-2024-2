package com.example.lab3.Repositories;

import com.example.lab3.Entities.Nutricion;
import com.example.lab3.Entities.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NutricionRepository extends JpaRepository<Nutricion, Integer> {

    Optional<Nutricion> findByReceta(Receta idReceta);

}
