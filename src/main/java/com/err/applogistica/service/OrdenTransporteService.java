package com.err.applogistica.service;

import com.err.applogistica.dto.OrdenDto;
import com.err.applogistica.dto.ResponseDto;
import com.err.applogistica.models.OrdenTransporte;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrdenTransporteService {

    ResponseDto<OrdenTransporte> savedOrdenTransporte(OrdenDto logistica);
    
    ResponseDto<OrdenTransporte> deletedOrdenTransporte(OrdenDto logistica);
    
    ResponseDto<OrdenTransporte> updatedOrdenTransporte(OrdenDto logistica);

    Page<OrdenTransporte> findAllPage(Pageable pageable);
}
