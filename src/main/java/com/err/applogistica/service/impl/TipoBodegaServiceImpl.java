package com.err.applogistica.service.impl;

import com.err.applogistica.dto.ResponseDto;
import com.err.applogistica.models.TipoBodega;
import com.err.applogistica.repositories.TipoBodegaRepository;
import com.err.applogistica.service.TipoBodegaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TipoBodegaServiceImpl implements TipoBodegaService {

    private static final Logger logger = LoggerFactory.getLogger(PaisServiceImpl.class);

    @Autowired
    private TipoBodegaRepository tipoBodegaRepository;

    @Override
    public ResponseDto<List<TipoBodega>> getTiposBodega() {
        ResponseDto<List<TipoBodega>> responseDto = new ResponseDto<>();

        try {
            responseDto.setCode("OK");
            responseDto.setErrores(null);
            responseDto.setResponse(tipoBodegaRepository.findAllByEnableOrderByDescripcionAsc(true));

        }catch (Exception e){
            logger.error("Error " + e);
        }
        return responseDto;
    }
}
