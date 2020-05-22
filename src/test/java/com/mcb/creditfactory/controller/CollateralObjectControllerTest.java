package com.mcb.creditfactory.controller;

import com.google.common.collect.ImmutableList;
import com.mcb.creditfactory.model.Car;
import com.mcb.creditfactory.service.CollateralService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CollateralObjectControllerTest {
    @Mock
    private CollateralService service;

    //создаем объект контроллера
    @InjectMocks
    CollateralObjectController controller;

    @Test
    public void getListAllCars() {
        //prepare
        when(service.findAllCars()).thenReturn(ImmutableList.of());
        //testing
        List<Car> listAllCars = controller.getListAllCars();
        //validate
        verify(service).findAllCars();
    }
}
