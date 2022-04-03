package com.err.applogistica.service;

import com.err.applogistica.dto.BodegaDto;
import com.err.applogistica.dto.ResponseDto;
import com.err.applogistica.models.Bodega;

import java.util.List;

public interface BodegaService {

    ResponseDto<Bodega> savedBodega(BodegaDto bodegaDto);

    ResponseDto<Bodega> updatedBodega(BodegaDto bodegaDto);

    ResponseDto<Bodega> disabledBodega(BodegaDto bodegaDto);

    ResponseDto<List<Bodega>> findByPaisAndTipoBodega(Long idPais, Long idTipoBodega);



}
