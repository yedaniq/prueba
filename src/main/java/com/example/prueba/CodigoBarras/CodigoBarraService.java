package com.example.prueba.CodigoBarras;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.prueba.Producto.Producto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CodigoBarraService {

    @Autowired
    private  CodigoBarraRepository codigoBarraRepository;

    public Producto buscarProductoPorCodigoBarras(String codigoBarras) {
        return codigoBarraRepository.findByCodigo(codigoBarras)
                .map(CodigoBarra::getProducto) // ✅ Obtiene el producto del Optional
                .orElseThrow(() -> new RuntimeException("Producto no encontrado para el código de barras: " + codigoBarras));
    }
}
