package com.err.applogistica.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.err.applogistica.models.Bodega;
import com.err.applogistica.models.Pais;
import com.err.applogistica.models.TipoBodega;

import java.util.List;

public interface BodegaRepository extends JpaRepository<Bodega, Long> {

    List<Bodega> findAllByEnableAndPaisAndTipoBodegaOrderByDescripcionAsc(Boolean enable, Pais pais , TipoBodega tipoBodega);
}
