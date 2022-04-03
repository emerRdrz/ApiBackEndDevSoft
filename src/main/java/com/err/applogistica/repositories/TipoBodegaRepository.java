package com.err.applogistica.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.err.applogistica.models.TipoBodega;

import java.util.List;

public interface TipoBodegaRepository extends JpaRepository<TipoBodega, Long> {

    List<TipoBodega> findAllByEnableOrderByDescripcionAsc(Boolean enable);
}
