package com.err.applogistica.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pais")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pais implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pais")
    private Long idPais;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "enable", length = 1)
    private Boolean enable;
}
