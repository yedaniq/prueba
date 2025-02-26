package com.example.prueba.CodigoBarras;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CodigoBarraRepository extends JpaRepository<CodigoBarra, UUID> {
    Optional<CodigoBarra> findByCodigo(String codigo);
}
