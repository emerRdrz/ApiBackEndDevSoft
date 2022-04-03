package com.err.applogistica.service;

import com.err.applogistica.dto.ResponseDto;
import com.err.applogistica.models.TipoBodega;

import java.util.List;

public interface TipoBodegaService {

    ResponseDto<List<TipoBodega>> getTiposBodega();
}
