package com.err.applogistica.repositories;

import com.err.applogistica.models.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaisRepository extends JpaRepository<Pais, Long> {

    List<Pais> findAllByEnableOrderByDescripcionAsc(Boolean enable);
}
