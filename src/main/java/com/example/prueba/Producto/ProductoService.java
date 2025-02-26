package com.example.prueba.Producto;

import java.io.IOException;
import java.util.*;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.prueba.Categoria.Categoria;
import com.example.prueba.Categoria.CategoriaRepository;
import com.example.prueba.DTO.CargarDatosExcelReponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductoService {

    @Autowired
    private  ProductoRepository productoRepository;

    @Autowired CategoriaRepository categoriaRepository;

    public List<Producto> buscarProductoPorCodigo(String codigo){
        return productoRepository.findByCodigo(codigo);
    }

    public List<Producto> buscarPorProductoPorDescripcion(String descripcion){
        return productoRepository.findByDescripcionLike(descripcion);
    }

    public List<Producto> coincidenciaProductoPorDescripcion(String descipcion){
        return productoRepository.findByDescripcionContaining(descipcion);
    }

    public CargarDatosExcelReponse datosExcelReponse(MultipartFile excelFile){
        List<String> errores = new ArrayList<>();
        int productosCargados = 0;
        try (Workbook workbook = WorkbookFactory.create(excelFile.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Saltar encabezados

                try {
                    String codigo = row.getCell(0).getStringCellValue();
                    String descripcion = row.getCell(1).getStringCellValue();
                    String codigoCategoria = row.getCell(2).getStringCellValue();

                    // Validaciones
                    if (codigo.length() > 10) {
                        errores.add("Fila " + (row.getRowNum() + 1) + ": Código excede 10 caracteres.");
                        continue;
                    }

                    Producto producto = new Producto();
                    producto.setCodigo(codigo);
                    producto.setDescripcion(descripcion);
                    Categoria categoria = categoriaRepository.findByCodigo(codigoCategoria)
                        .stream()
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Código de categoría inválido: " + codigoCategoria));
                    producto.setCategoria(categoria);

                    productoRepository.save(producto);
                    productosCargados++;
                } catch (Exception e) {
                    errores.add("Fila " + (row.getRowNum() + 1) + ": Error al procesar.");
                }
            }
        } catch (IOException e) {
            errores.add("Error al leer el archivo.");        }


        return new CargarDatosExcelReponse(errores, productosCargados);
    }

    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto actualizarProducto(UUID id, Producto producto) {
        Producto existente = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        existente.setCodigo(producto.getCodigo());
        existente.setDescripcion(producto.getDescripcion());
        existente.setActivo(producto.isActivo());
        return productoRepository.save(existente);
    }

    public void eliminarProducto(UUID id) {
        productoRepository.deleteById(id);
    }

}
