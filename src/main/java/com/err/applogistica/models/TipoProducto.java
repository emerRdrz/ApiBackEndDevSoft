package com.err.applogistica.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tipo_producto")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TipoProducto implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_producto")
    private Long idTipoProducto;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "enable")
    private Boolean enable;
}
