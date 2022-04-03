package com.err.applogistica.controller;

import com.err.applogistica.dto.ResponseDto;
import com.err.applogistica.models.Pais;
import com.err.applogistica.service.PaisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RestController
@RequestMapping("/apiRest/catalogo/pais")
public class PaisController {

    private static final Logger logger = LoggerFactory.getLogger(PaisController.class);

    @Autowired
    private PaisService paisService;

    @GetMapping()
    /**
     *
     * Muestra todos los registro con estado activo de los paises.
     *
     */
    public ResponseDto<List<Pais>> getPaises(){
        ResponseDto<List<Pais>> paisResponseDto = new ResponseDto<>();
        try {
            paisResponseDto = paisService.getPaises();

        }catch (Exception e){
            logger.error("Error " + e);
        }
        return paisResponseDto;
    }
}
