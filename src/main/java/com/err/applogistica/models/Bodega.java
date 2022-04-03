package com.err.applogistica.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bodega")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Bodega  implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bodega")
    private Long idBodega;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "enable")
    private Boolean enable;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_bodega")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private TipoBodega tipoBodega;
    @ManyToOne(cascade = CascadeType.ALL, fetch =  FetchType.EAGER)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "id_pais")
    private Pais pais;

}
