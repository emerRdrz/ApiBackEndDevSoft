package com.err.applogistica.controller;

import com.err.applogistica.dto.OrdenDto;
import com.err.applogistica.dto.ResponseDto;
import com.err.applogistica.models.OrdenTransporte;
import com.err.applogistica.service.OrdenTransporteService;
import com.err.applogistica.utils.Utils;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/apiRest/ordentransporte")
public class OrdenTransporteController {

    private static final Logger logger = LoggerFactory.getLogger(OrdenTransporteController.class);
    @Autowired
    private OrdenTransporteService ordenTransporteService;

    /**
     * Guarda un numevo metodo
     * @param orden
     * @return
     */
    @ApiOperation("Registrar una nueva orden de transporte para ser guardado en la base de datos")
    @PostMapping
    public ResponseEntity<ResponseDto<OrdenTransporte>> savedOrden(@RequestBody OrdenDto orden){
        ResponseDto<OrdenTransporte> ordenTransporteResponseDto = null;

        try{
            ordenTransporteResponseDto = ordenTransporteService.savedOrdenTransporte(orden);

            if(ordenTransporteResponseDto.getCode().equals("ERROR_SEMANTICA")){
                return new ResponseEntity<>(ordenTransporteResponseDto, HttpStatus.UNPROCESSABLE_ENTITY);
            }

        }catch (Exception e){
            logger.error("Error", e);
            return new ResponseEntity<>(ordenTransporteResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(ordenTransporteResponseDto, HttpStatus.OK);
    }

    /**
     * Guarda un numevo metodo
     * @param orden
     * @return
     */
    @ApiOperation("Modifica una nueva orden de logistica para ser guardado en la base de datos")
    @PutMapping
    public ResponseEntity<ResponseDto<OrdenTransporte>> editOrden(@RequestBody OrdenDto orden){
        ResponseDto<OrdenTransporte> ordenTransporteResponseDto = null;

        try{
            ordenTransporteResponseDto = ordenTransporteService.updatedOrdenTransporte(orden);

            if(ordenTransporteResponseDto.getCode().equals("ERROR_SEMANTICA")){
                return new ResponseEntity<>(ordenTransporteResponseDto, HttpStatus.UNPROCESSABLE_ENTITY);
            }

        }catch (Exception e){
            logger.error("Error", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(ordenTransporteResponseDto, HttpStatus.OK);
    }

    /**
     * Guarda un numevo metodo
     * @param id
     * @return
     */
    @ApiOperation("Elimina un registro fisicamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<OrdenTransporte>> deleteOrden(@PathVariable(name = "id") Long id){
        ResponseDto<OrdenTransporte> ordenTransporteResponseDto = null;

        try{
            OrdenDto ordenDto = new OrdenDto();
            ordenDto.setIdOrdenTransporte(id);

            ordenTransporteResponseDto = ordenTransporteService.deletedOrdenTransporte(ordenDto);

            if(ordenTransporteResponseDto.getCode().equals("NOT_FOUND")){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            logger.error("Error", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(ordenTransporteResponseDto, HttpStatus.OK);
    }

    @ApiOperation("Devuelve todos los registros")
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllOrdens(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "idOrdenTransporte,desc") String[] sort
    ){
        List<OrdenTransporte> outCome = new ArrayList<>(0);

        try {
            List<Sort.Order> ordens = new ArrayList<>();
            if (sort[0].contains(",")) {
                for(String s : sort){
                    String[] sortNew = s.split(",");
                    ordens.add(new Sort.Order(Utils.getSortDirection(sortNew[1]), sortNew[0]));
                }
            }else{
                ordens.add(new Sort.Order(Utils.getSortDirection(sort[1]), sort[0]));
            }

            Pageable pageable = PageRequest.of(page,size, Sort.by(ordens));

            Page<OrdenTransporte> pageOrden;

            pageOrden = ordenTransporteService.findAllPage(pageable);

            outCome = pageOrden.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("ordenResultList", outCome);
            response.put("currentPage", pageOrden.getNumber());
            response.put("totalItems", pageOrden.getTotalElements());
            response.put("totalPages", pageOrden.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            logger.error("Error ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
