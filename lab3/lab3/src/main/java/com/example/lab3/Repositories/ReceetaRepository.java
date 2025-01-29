package com.example.lab3.Repositories;

import com.example.lab3.Entities.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceetaRepository extends JpaRepository<Receta,Integer> {

}
