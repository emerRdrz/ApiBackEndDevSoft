package com.err.applogistica.repocitories;


import com.err.applogistica.models.TipoProducto;
import com.err.applogistica.repositories.TipoProductoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TipoProductoRepositoryTest {

    @Autowired
    private TipoProductoRepository tipoProductoRepository;

    @Test
    public void saveTipoProducto(){
        TipoProducto tipoProducto = new TipoProducto(null, "Tipo Producto Test 1", true);
        TipoProducto tipoProducto2 = new TipoProducto(null, "Tipo Producto Test 1", true);

        tipoProductoRepository.save(tipoProducto);
        tipoProductoRepository.save(tipoProducto2);

        tipoProductoRepository.flush();

        Assertions.assertEquals(8, tipoProductoRepository.findAll().size());

    }
}
