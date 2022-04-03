package com.err.applogistica.controller;

import com.err.applogistica.dto.BodegaDto;
import com.err.applogistica.dto.ResponseDto;
import com.err.applogistica.models.Bodega;
import com.err.applogistica.service.BodegaService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/apiRest/catalogo/bodega")
public class BodegaController {

    private static final Logger logger = LoggerFactory.getLogger(BodegaController.class);

    @Autowired
    private BodegaService bodegaService;

    /**
     * Registra bodega
     * @param bodegaDto
     * @return
     */
    @ApiOperation("Registrar una nueva bodega para ser guardado en la base de datos")
    @PostMapping
    public ResponseEntity<ResponseDto<Bodega>> savedBodega(@RequestBody BodegaDto bodegaDto){
        ResponseDto<Bodega> bodegaResponseDto = null;

        try{
            bodegaResponseDto = bodegaService.savedBodega(bodegaDto);

        }catch (Exception e){
            logger.error("Error", e);
            return new ResponseEntity<>(bodegaResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(bodegaResponseDto, HttpStatus.OK);
    }

    /**
     * Modifica bodega
     * @param bodegaDto
     * @return
     */
    @ApiOperation("Modifica una bodega para ser guardado en la base de datos")
    @PutMapping
    public ResponseEntity<ResponseDto<Bodega>> editBodega(@RequestBody BodegaDto bodegaDto){
        ResponseDto<Bodega> bodegaResponseDto = null;

        try{
            bodegaResponseDto = bodegaService.updatedBodega(bodegaDto);

        }catch (Exception e){
            logger.error("Error", e);
            return new ResponseEntity<>(bodegaResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(bodegaResponseDto, HttpStatus.OK);
    }

    /**
     * Elimina registro logicamente
     * @param id
     * @return
     */
    @ApiOperation("Elimina un registro logicamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Bodega>> deleteBodega(@PathVariable(name = "id") Long id){
        ResponseDto<Bodega> bodegaResponseDto = null;

        try{
            BodegaDto bodegaDto = new BodegaDto();
            bodegaDto.setIdBodega(id);

            bodegaResponseDto = bodegaService.disabledBodega(bodegaDto);

            if(bodegaResponseDto.getCode().equals("NOT_FOUND")){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            logger.error("Error", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(bodegaResponseDto, HttpStatus.OK);
    }

    /**
     * Devuelve las bodegas que estan asociada a un pais y al tipo de bodega.
     * @param idPais
     * @param idTipoBodega
     * @return ResponseDto<List<Bodega>>
     */
    @GetMapping("byparameters/{idPais}/{idTipoBodega}")
    public ResponseDto<List<Bodega>> getBodegas(@PathVariable Long idPais, @PathVariable Long idTipoBodega){
        ResponseDto<List<Bodega>> responseDto = new ResponseDto<>();
        try {
            responseDto = bodegaService.findByPaisAndTipoBodega(idPais,idTipoBodega);
        }catch (Exception e){
            logger.error("Error " + e);
        }
        return responseDto;
    }
}
