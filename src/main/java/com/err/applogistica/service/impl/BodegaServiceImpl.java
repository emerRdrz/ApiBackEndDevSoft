package com.err.applogistica.service.impl;

import com.err.applogistica.dto.BodegaDto;
import com.err.applogistica.dto.ResponseDto;
import com.err.applogistica.models.*;
import com.err.applogistica.repositories.BodegaRepository;
import com.err.applogistica.repositories.PaisRepository;
import com.err.applogistica.repositories.TipoBodegaRepository;
import com.err.applogistica.service.BodegaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BodegaServiceImpl implements BodegaService {

    private static final Logger logger = LoggerFactory.getLogger(BodegaServiceImpl.class);
    @Autowired
    private BodegaRepository bodegaRepository;

    @Autowired
    private TipoBodegaRepository tipoBodegaRepository;

    @Autowired
    private PaisRepository paisRepository;

    @Override
    public ResponseDto<Bodega> savedBodega(BodegaDto bodegaDto) {
        return savedorUpdate(bodegaDto);
    }

    @Override
    public ResponseDto<Bodega> updatedBodega(BodegaDto bodegaDto) {
        return savedorUpdate(bodegaDto);
    }

    @Override
    public ResponseDto<Bodega> disabledBodega(BodegaDto bodegaDto) {
        ResponseDto<Bodega> bodegaResponseDto = new ResponseDto<>();

        try {
            Bodega bodega = bodegaRepository.getById(bodegaDto.getIdBodega());
            if(bodega.getIdBodega()!=null){
                bodega.setEnable(false);
                bodegaRepository.saveAndFlush(bodega);

                bodegaResponseDto.setResponse(null);
                bodegaResponseDto.setCode("OK");
                bodegaResponseDto.setErrores(null);

            }else{
                bodegaResponseDto.setResponse(null);
                bodegaResponseDto.setCode("NOT_FOUND");
                bodegaResponseDto.setErrores(null);
            }

        } catch (Exception e){
            logger.error("Error", e);
            bodegaResponseDto = new ResponseDto<>();
            bodegaResponseDto.setResponse(null);
            bodegaResponseDto.setCode("ERROR");
            bodegaResponseDto.setErrores(null);
        }

        return bodegaResponseDto;
    }


    /**
     * Logica para guardar o modificar un registro
     * @param bodegaDto
     * @return ResponseDto<Bodega>
     */
    private ResponseDto<Bodega> savedorUpdate(BodegaDto bodegaDto){
        ResponseDto<Bodega> bodegaResponseDto = new ResponseDto<>();

        try{

            Bodega bodega = new Bodega();

            if(bodegaDto.getIdBodega() != null){
                bodega.setIdBodega(bodegaDto.getIdBodega());
            }

            bodega.setDescripcion(bodegaDto.getDescripcion());
            bodega.setDireccion(bodegaDto.getDireccion());
            bodega.setEnable(bodegaDto.getEnable());

            TipoBodega tipoBodega = tipoBodegaRepository.getById(bodegaDto.getTipoBodega());
            bodega.setTipoBodega(tipoBodega);

            Pais pais = paisRepository.getById(bodegaDto.getPais());
            bodega.setPais(pais);

            bodegaRepository.saveAndFlush(bodega);

            bodegaResponseDto.setResponse(bodega);
            bodegaResponseDto.setCode("OK");
            bodegaResponseDto.setErrores(null);

        }catch (Exception e){
            logger.error("Error", e);
            bodegaResponseDto = new ResponseDto<>();
            bodegaResponseDto.setCode("ERROR");
            Map<String, String> map = new HashMap<>();
            map.put("error", "Contacte con el administrador de sistemas");
            bodegaResponseDto.setErrores(map);
        }

        return bodegaResponseDto;
    }

    @Override
    public ResponseDto<List<Bodega>> findByPaisAndTipoBodega(Long idPais, Long idTipoBodega) {
        ResponseDto<List<Bodega>> responseDto = new ResponseDto<>();
        try {
            Pais pais = new Pais();
            pais.setIdPais(idPais);

            TipoBodega tipoBodega = new TipoBodega();
            tipoBodega.setIdTipoBodega(idTipoBodega);

            responseDto.setCode("OK");
            responseDto.setErrores(null);
            responseDto.setResponse(bodegaRepository.findAllByEnableAndPaisAndTipoBodegaOrderByDescripcionAsc(true, pais, tipoBodega));
            return responseDto;
        }catch (Exception e){
            logger.error("Error " + e);
            return responseDto;
        }
    }


}
