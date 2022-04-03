package com.err.applogistica.services;


import com.err.applogistica.models.Pais;
import com.err.applogistica.repositories.PaisRepository;
import com.err.applogistica.service.PaisService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class PaisServiceTest {

    @InjectMocks
    PaisService paisService;

    @BeforeEach
    public void init() {
        paisService = mock(PaisService.class);
    }

    @Test
    public void getAllCountries() {
        List<Pais> paises = new ArrayList<>();
        Pais pais1 = new Pais(1L, "El Salvador", true);
        Pais pais2 = new Pais(2L, "Guatemala", true);
        Pais pais3 = new Pais(3L, "Honduras", true);
        Pais pais4 = new Pais(4L, "México", true);

        paises.add(pais1);
        paises.add(pais2);
        paises.add(pais3);
        paises.add(pais4);

        when(paisService.findAllPaises()).thenReturn(paises);

        List<Pais> paisList = paisService.findAllPaises();

        Assertions.assertEquals(4, paisList.size());

        verify(paisService, times(1)).findAllPaises();

    }

    @Test
    public void getOneCountry() {
        Pais pais1 = new Pais(1L, "El Salvador", true);

        when(paisService.findOneById(1L)).thenReturn(pais1);

        Assertions.assertEquals(pais1, paisService.findOneById(1L));

        verify(paisService, times(1)).findOneById(1L);
    }

}
