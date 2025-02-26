package com.example.prueba.Categoria;

import java.security.PublicKey;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    @Autowired
    private final CategoriaRepository categoriaRepository;

    public List<Categoria> buscarCategoriaPorCodigo (String codigo){
        return categoriaRepository.findByCodigo(codigo);
    }

    public List<Categoria> buscarCategoriaPorDescripcion(String descipcion){
        return categoriaRepository.findByDescripcionContaining(descipcion);
    }

    public  Optional<Categoria> obtenerPorId(UUID id) {
        return categoriaRepository.findById(id);
    }

    public Categoria guardarCategoria (Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public Optional<Categoria> buscarPorId(UUID id){
        return categoriaRepository.findById(id);
    }
}
