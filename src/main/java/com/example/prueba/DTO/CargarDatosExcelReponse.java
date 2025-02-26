package com.example.prueba.DTO;

import java.util.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CargarDatosExcelReponse {
    private List<String> errores;
    private int productosCargados;

    public CargarDatosExcelReponse(List<String> errores, int productosCargados){
        this.errores = errores;
        this.productosCargados = productosCargados;
    }

}
