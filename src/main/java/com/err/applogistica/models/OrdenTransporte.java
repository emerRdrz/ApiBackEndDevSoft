package com.err.applogistica.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "orden_transporte")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrdenTransporte  implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orden_transporte")
    private Long idOrdenTransporte;
    @Column(name = "cantidad_producto")
    private Integer cantidadProducto;
    @Column(name = "fecha_registro")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date fechaRegistro;
    @Column(name = "fecha_entrega")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date fechaEntrega;
    @Column(name = "precio_envio")
    private BigDecimal precioEnvio;
    @Column(name = "precio_descuento")
    private BigDecimal precioDescuento;
    @Column(name = "porcentaje_descuento")
    private Integer porcentajeDescuento;
    @Column(name = "placa")
    private String placa;
    @Column(name = "numero_guia")
    private String numeroGuia;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_bodega")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Bodega bodega;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_producto")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private TipoProducto tipoProducto;

}
