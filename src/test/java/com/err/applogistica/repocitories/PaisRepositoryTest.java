package com.err.applogistica.repocitories;


import com.err.applogistica.models.Pais;
import com.err.applogistica.repositories.PaisRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PaisRepositoryTest {

    @Autowired
    private PaisRepository paisRepository;

    @Test
    public void savePais(){
        Pais pais = new Pais(null, "China", true);
        Pais pais2 = new Pais(null, "Corea de Sur", true);

        paisRepository.save(pais);
        paisRepository.save(pais2);

        paisRepository.flush();

        Assertions.assertEquals(8, paisRepository.findAll().size());

    }
}
