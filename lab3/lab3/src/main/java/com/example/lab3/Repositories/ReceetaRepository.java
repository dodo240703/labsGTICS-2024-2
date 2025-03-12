package com.example.lab3.Repositories;

import com.example.lab3.Entities.Categoria;
import com.example.lab3.Entities.Receta;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceetaRepository extends JpaRepository<Receta,Integer> {

    List<Receta> findByNombreContainingIgnoreCase(String nombre, Sort sort);
    List<Receta> findByIdcategoria_Id(Integer idCategoria, Sort sort);
    List<Receta> findByNombreContainingIgnoreCaseAndIdcategoria_Id(String name, Integer idCategoria, Sort sort);

    @Query(value = """
    SELECT r.* FROM receta r
    LEFT JOIN receta_ingrediente ri ON r.idreceta = ri.idreceta
    GROUP BY r.idreceta
    ORDER BY COUNT(ri.idingrediente) DESC
    """, nativeQuery = true)
    List<Receta> findAllOrderByCantidadIngredientesDesc();

    @Query(value = """
    SELECT r.* FROM receta r
    LEFT JOIN receta_ingrediente ri ON r.idreceta = ri.idreceta
    GROUP BY r.idreceta
    ORDER BY COUNT(ri.idingrediente) ASC
    """, nativeQuery = true)
    List<Receta> findAllOrderByCantidadIngredientesAsc();


}
