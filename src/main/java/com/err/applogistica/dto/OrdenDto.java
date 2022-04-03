package com.err.applogistica.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdenDto implements Serializable {

	private Long idOrdenTransporte;
    private Integer cantidadProducto;
    private String fechaRegistro;
    private String fechaEntrega;
    private BigDecimal precioEnvio;
    private BigDecimal precioDescuento;
    private Integer porcentajeDescuento;
    private String placa;
    private String numeroGuia;
    private Long bodega;
    private Long usuario;
    private Long tipoProducto;
}
