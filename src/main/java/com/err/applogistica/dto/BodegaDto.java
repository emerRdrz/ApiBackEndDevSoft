package com.err.applogistica.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BodegaDto implements Serializable {

    private Long idBodega;
    private String descripcion;
    private String direccion;
    private Boolean enable;
    private Long tipoBodega;
    private Long pais;
}
