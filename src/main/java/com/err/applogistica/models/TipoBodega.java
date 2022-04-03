package com.err.applogistica.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tipo_bodega")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TipoBodega  implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_bodega")
    private Long idTipoBodega;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "enable", length = 1)
    private Boolean enable;
}
