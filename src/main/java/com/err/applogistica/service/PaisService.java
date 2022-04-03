package com.err.applogistica.service;

import com.err.applogistica.dto.ResponseDto;
import com.err.applogistica.models.Pais;

import java.util.List;

public interface PaisService {

    ResponseDto<List<Pais>> getPaises();
}
