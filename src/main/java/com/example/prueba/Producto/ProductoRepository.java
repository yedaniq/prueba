package com.example.prueba.Producto;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductoRepository extends JpaRepository<Producto, UUID>{
    List<Producto> findByCodigo(String codigo);
    List<Producto> findByDescripcionContaining(String descripcion);
    List<Producto> findByDescripcionLike(String descripcion);
}
