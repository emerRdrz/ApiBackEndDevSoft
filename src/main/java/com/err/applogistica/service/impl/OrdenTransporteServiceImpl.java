package com.err.applogistica.service.impl;

import com.err.applogistica.dto.OrdenDto;
import com.err.applogistica.dto.ResponseDto;
import com.err.applogistica.models.*;
import com.err.applogistica.repositories.*;
import com.err.applogistica.service.OrdenTransporteService;
import com.err.applogistica.utils.Utils;
import jdk.jshell.spi.ExecutionControlProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OrdenTransporteServiceImpl implements OrdenTransporteService {

    private static final Logger logger = LoggerFactory.getLogger(OrdenTransporteServiceImpl.class);

    private static final Double DESCUENTO_TERRESTE = 0.05D;
    private static final Double DESCUENTO_MARITIMA = 0.03D;
    

    @Autowired
    private TipoProductoRepository  tipoProductoRepository;

    @Autowired
    private BodegaRepository bodegaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private OrdenTransporteRepository ordenTransporteRepository;


    @Override
    public ResponseDto<OrdenTransporte> savedOrdenTransporte(OrdenDto orden) {
        return savedorUpdate(orden);
    }    

    @Override
    public ResponseDto<OrdenTransporte> deletedOrdenTransporte(OrdenDto orden) {
        ResponseDto<OrdenTransporte> ordenTransporteResponseDto = new ResponseDto<>();
        try {
            OrdenTransporte ordenTransporte = ordenTransporteRepository.getById(orden.getIdOrdenTransporte());

            if(ordenTransporte != null){
            	ordenTransporteRepository.delete(ordenTransporte);
            	ordenTransporteResponseDto.setResponse(null);
            	ordenTransporteResponseDto.setCode("OK");
            	ordenTransporteResponseDto.setErrores(null);
            }else{
            	ordenTransporteResponseDto.setResponse(null);
            	ordenTransporteResponseDto.setCode("NOT_FOUND");
            	ordenTransporteResponseDto.setErrores(null);
            }
        }catch (Exception e){
        	ordenTransporteResponseDto.setResponse(null);
        	ordenTransporteResponseDto.setCode("Error");
        	ordenTransporteResponseDto.setErrores(null);
        }

        return ordenTransporteResponseDto;
    }
    
    @Override
    public ResponseDto<OrdenTransporte> updatedOrdenTransporte(OrdenDto orden) {
        return savedorUpdate(orden);
    }

    /**
     * Logica para guardar o modificar un registro
     * @param orden
     * @return ResponseDto<Logistica>
     */
   private ResponseDto<OrdenTransporte> savedorUpdate(OrdenDto orden){
       ResponseDto<OrdenTransporte> ordenResponseDto = new ResponseDto<>();

       try{

           OrdenTransporte ordenTransporte = new OrdenTransporte();

           if(orden.getIdOrdenTransporte() != null){
        	   ordenTransporte.setIdOrdenTransporte(orden.getIdOrdenTransporte());
           }

           ordenTransporte.setPrecioEnvio(orden.getPrecioEnvio());
           ordenTransporte.setPrecioDescuento(orden.getPrecioDescuento());
           ordenTransporte.setPorcentajeDescuento(orden.getPorcentajeDescuento());
           ordenTransporte.setCantidadProducto(orden.getCantidadProducto());
           ordenTransporte.setPlaca(orden.getPlaca());
           ordenTransporte.setNumeroGuia(orden.getNumeroGuia());

           Usuario usuario = usuarioRepository.getById(orden.getUsuario());
           ordenTransporte.setUsuario(usuario);

           Bodega bodega = bodegaRepository.getById(orden.getBodega());
           ordenTransporte.setBodega(bodega);

           TipoProducto tipoProducto = tipoProductoRepository.getById(orden.getTipoProducto());
           ordenTransporte.setTipoProducto(tipoProducto);

           //Se aplicara descuento cuando se tenga mas de 10 productos.
           if(ordenTransporte.getCantidadProducto() > 10L){
               if(ordenTransporte.getBodega().getTipoBodega().getIdTipoBodega().equals(1L)){
            	   ordenTransporte.setPrecioDescuento(orden.getPrecioEnvio().multiply(new BigDecimal(DESCUENTO_TERRESTE.toString())));
            	   ordenTransporte.setPorcentajeDescuento(5);
               }else{
            	   ordenTransporte.setPrecioDescuento(ordenTransporte.getPrecioEnvio().multiply(new BigDecimal(DESCUENTO_MARITIMA.toString())));
            	   ordenTransporte.setPorcentajeDescuento(3);
               }
           }

           ordenTransporte.setFechaRegistro(new Date());

           //Validaciones

           try{
        	   ordenTransporte.setFechaEntrega(Utils.dateToString(orden.getFechaEntrega()));
           }catch (Exception e){
        	   ordenResponseDto.setCode("ERROR_SEMANTICA");
               Map<String, String> map = new HashMap<>();
               map.put("error", "El formato de fecha incorrecto: dd/mm/yyyy");
               ordenResponseDto.setErrores(map);

               return ordenResponseDto;
           }

           if(ordenTransporte.getBodega().getTipoBodega().getIdTipoBodega().equals(1L)) {
               if (!Utils.validarPatter(Utils.VALIDAR_PLACA, ordenTransporte.getPlaca())) {
            	   ordenResponseDto.setCode("ERROR_SEMANTICA");
                   Map<String, String> map = new HashMap<>();
                   map.put("error", "El formato del numero de placa es incorrecto");
                   ordenResponseDto.setErrores(map);
                   return ordenResponseDto;
               }
           }else {
               if (!Utils.validarPatter(Utils.VALIDAR_NUMERO_FLOTA, ordenTransporte.getPlaca())) {
            	   ordenResponseDto.setCode("ERROR_SEMANTICA");
                   Map<String, String> map = new HashMap<>();
                   map.put("error", "El formato del numero de flota es incorrecto");
                   ordenResponseDto.setErrores(map);

                   return ordenResponseDto;
               }
           }

           if (!Utils.validarPatter(Utils.VALIDAR_NUMERO_GUIA, ordenTransporte.getNumeroGuia())) {
        	   ordenResponseDto.setCode("ERROR_SEMANTICA");
               return ordenResponseDto;
           }

           //fin de la validacion

           ordenTransporteRepository.saveAndFlush(ordenTransporte);

           ordenResponseDto.setResponse(ordenTransporte);
           ordenResponseDto.setCode("OK");
           ordenResponseDto.setErrores(null);

       }catch (Exception e){
           logger.error("Error", e);
           ordenResponseDto = new ResponseDto<>();
           ordenResponseDto.setCode("ERROR");
           Map<String, String> map = new HashMap<>();
           map.put("error", "No se pudo crear la order");
           ordenResponseDto.setErrores(map);
       }

       return ordenResponseDto;
   }

    @Override
    public Page<OrdenTransporte> findAllPage(Pageable pageable) {
        Page<OrdenTransporte> pageOrden = null;
        try {
            pageOrden = ordenTransporteRepository.findAll(pageable);
        }catch (Exception e){
            logger.error("Error", e);
        }
        return pageOrden;
    }
}
