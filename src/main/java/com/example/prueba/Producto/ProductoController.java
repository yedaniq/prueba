package com.example.prueba.Producto;

import java.util.*;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("api/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/codigo/{codigo}")    
    public List<Producto> buscarPorCodigo(@PathVariable String codigo){
        return productoService.buscarProductoPorCodigo(codigo);
    }


    @GetMapping("/like/{descripcion}")
    public List<Producto> coincidenciaDescripcion(@PathVariable String descripcion){
        return productoService.coincidenciaProductoPorDescripcion(descripcion);
    }

    @GetMapping("/descripcion/{descripcion}")
    public List<Producto> buscarPorDescripcion(@PathVariable String descripcion){
        return productoService.buscarPorProductoPorDescripcion(descripcion);
    }

        @PostMapping
    public Producto crear(@RequestBody Producto producto) {
        return productoService.guardarProducto(producto);
    }

    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable UUID id, @RequestBody Producto producto) {
        return productoService.actualizarProducto(id, producto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable UUID id) {
        productoService.eliminarProducto(id);
    }

}
