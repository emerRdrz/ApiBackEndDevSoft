package com.err.applogistica.controller;

import com.err.applogistica.dto.ResponseDto;
import com.err.applogistica.models.TipoProducto;
import com.err.applogistica.service.TipoProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RestController
@RequestMapping("/apiRest/catalogo/tipoProducto")
public class TipoProductoController {

    private static final Logger logger = LoggerFactory.getLogger(TipoProductoController.class);

    @Autowired
    private TipoProductoService tipoProductoService;

    @GetMapping()
    /**
     * Devuelve el listado de Tipo de producto.
     * @return ResponseDto<List<TipoProducto>>
     */
    public ResponseDto<List<TipoProducto>> getTipoProducto(){
        ResponseDto<List<TipoProducto>> responseDto = new ResponseDto<>();
        try {
            responseDto = tipoProductoService.getTiposProducto();

        }catch (Exception e){
            logger.error("Error " + e);
        }
        return responseDto;
    }
}
