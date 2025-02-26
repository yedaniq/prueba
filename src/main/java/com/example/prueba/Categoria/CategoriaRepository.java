package com.example.prueba.Categoria;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;


@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, UUID>{
    List<Categoria> findByCodigo(String codigo);
    List<Categoria> findByDescripcionContaining(String descripcion);
    Optional<Categoria> findList<Categoria> findById(UUID id);
}
