package com.err.applogistica.controller;

import com.err.applogistica.dto.ResponseDto;
import com.err.applogistica.models.TipoBodega;
import com.err.applogistica.service.TipoBodegaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RestController
@RequestMapping("/apiRest/catalogo/tipoBodega")
public class TipoBodegaController {

    private static final Logger logger = LoggerFactory.getLogger(TipoBodegaController.class);

    @Autowired
    private TipoBodegaService tipoBodegaService;

    @GetMapping()
    /**
     * Devuelve el listado de Tipo de Bodegas.
     * @return ResponseDto<List<TipoBodega>>
     */
    public ResponseDto<List<TipoBodega>> getTipoBodega(){
        ResponseDto<List<TipoBodega>> responseDto = new ResponseDto<>();
        try {
            responseDto = tipoBodegaService.getTiposBodega();

        }catch (Exception e){
            logger.error("Error " + e);
        }
        return responseDto;
    }
}
