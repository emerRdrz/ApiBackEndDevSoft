package com.err.applogistica.service.impl;

import com.err.applogistica.dto.ResponseDto;
import com.err.applogistica.models.Pais;
import com.err.applogistica.repositories.PaisRepository;
import com.err.applogistica.service.PaisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PaisServiceImpl implements PaisService {

    private static final Logger logger = LoggerFactory.getLogger(PaisServiceImpl.class);

    @Autowired
    private PaisRepository paisRepository;

    @Override
    public ResponseDto<List<Pais>> getPaises() {
        ResponseDto<List<Pais>> paisResponseDto = new ResponseDto<>();

        try {
            paisResponseDto.setCode("OK");
            paisResponseDto.setErrores(null);
            paisResponseDto.setResponse(paisRepository.findAllByEnableOrderByDescripcionAsc(true));
            return paisResponseDto;
        }catch (Exception e){
            logger.error("Error " + e);
            return paisResponseDto;
        }
    }

    @Override
    public List<Pais> findAllPaises() {
        List<Pais> paisesList = new ArrayList<>();
        try {
            paisesList = paisRepository.findAll();

        }catch (Exception e){
            logger.error("Error " + e);
        }
        return paisesList;
    }

    @Override
    public Pais findOneById(Long idPais) {
        Pais pais = new Pais();

        try{
            pais = paisRepository.getById(idPais);
        }catch (Exception e){
            logger.error("Error " + e);
        }

        return pais;
    }
}
