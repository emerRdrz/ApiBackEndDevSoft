package com.err.applogistica.repositories;


import com.err.applogistica.models.TipoProducto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoProductoRepository extends JpaRepository<TipoProducto, Long> {

    List<TipoProducto> findAllByEnableOrderByDescripcionAsc(Boolean enable);
}
