package pl.pw.edu.demo.controler;


import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.pw.edu.demo.DemoApplication;
import pl.pw.edu.demo.algorithm.Algorithm;
import pl.pw.edu.demo.dto.CourseResponse;
import pl.pw.edu.demo.dto.SaveCourseRequest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ControlerTest {

    @Mock
    Algorithm algorithm;


    @Test
    public void saveCourseTest(){
        SaveCourseRequest request = new SaveCourseRequest("Warszawa","Lublin",2000D,10D);
        when(algorithm.addCours(request)).thenReturn(200);
        Assert.assertEquals(algorithm.addCours(request),200);
        verify(algorithm,times(1)).addCours(request);
    }

    @Test
    public void findConnectionTest(){
        //Befor
        List<String> cities = new ArrayList<>();
        cities.add("Warszawa");
        cities.add("Lublin");
        //When
        when(algorithm.findBestConnection(cities.get(0),cities.get(1))).thenReturn(new CourseResponse(cities, 499));
        //Then
        Assert.assertEquals(algorithm.findBestConnection(cities.get(0),cities.get(1)).getValue(),499,0);
    }

}
