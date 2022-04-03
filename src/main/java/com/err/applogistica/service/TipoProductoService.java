package com.err.applogistica.service;

import com.err.applogistica.dto.ResponseDto;
import com.err.applogistica.models.TipoProducto;

import java.util.List;

public interface TipoProductoService {

    ResponseDto<List<TipoProducto>> getTiposProducto();
}
