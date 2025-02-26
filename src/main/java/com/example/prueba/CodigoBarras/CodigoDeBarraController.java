package com.example.prueba.CodigoBarras;

import java.util.*;

import org.springframework.web.bind.annotation.RestController;

import com.example.prueba.Producto.Producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/codigos-barras")
public class CodigoDeBarraController {
    @Autowired
    private CodigoBarraService codigoBarraService;

    @GetMapping("/{codigo}")
    public ResponseEntity<Producto> obtenerProductoPorCodigoBarras(@PathVariable String codigoBarras) {
        try {
            Producto producto = codigoBarraService.buscarProductoPorCodigoBarras(codigoBarras);
            return ResponseEntity.ok(producto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
