package com.err.applogistica.service.impl;

import com.err.applogistica.dto.ResponseDto;
import com.err.applogistica.models.TipoProducto;
import com.err.applogistica.repositories.TipoProductoRepository;
import com.err.applogistica.service.TipoProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TipoProductoServiceImpl implements TipoProductoService {

    private static final Logger logger = LoggerFactory.getLogger(TipoProductoServiceImpl.class);

    @Autowired
    private TipoProductoRepository tipoProductoRepository;

    @Override
    public ResponseDto<List<TipoProducto>> getTiposProducto() {
        ResponseDto<List<TipoProducto>> responseDto = new ResponseDto<>();
        try {
            responseDto.setCode("OK");
            responseDto.setErrores(null);
            responseDto.setResponse(tipoProductoRepository.findAllByEnableOrderByDescripcionAsc(true));

        }catch (Exception e){
            logger.error("Error " + e);
        }
        return responseDto;
    }
}
