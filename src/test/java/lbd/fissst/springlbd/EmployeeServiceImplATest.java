package lbd.fissst.springlbd;

import lbd.fissst.springlbd.entity.employee.Employee;
import lbd.fissst.springlbd.service.employee.EmployeeServiceImplA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class EmployeeServiceImplATest {

    @Value("${project.prefix}")
    String prefix;

    @Value("${project.suffix}")
    String suffix;

    @Autowired
    EmployeeServiceImplA employeeService;

    //Throwing exceptions and exception testing have been ignored
    @ParameterizedTest
    @CsvSource({
            "Damian,Purgal,DamPur",
            "Jan,Kowalski,JanKow",
            "Jan,Nowak,JanNow"
    })
    public void testIsNicknameGeneratedCorrectly(String firstName, String lastName, String expectedNickname){

        String correctNickname = prefix + expectedNickname + suffix;

        String testedNickname = employeeService.getEmployeeNickname(firstName, lastName);

        assertEquals(correctNickname,
                testedNickname,
                "nickname is not generated correctly");
    }

    @Test
    public void testIsEmployeeAddedSuccessfully(){
        //given
        Employee employee = Employee.builder()
                .id(1L)
                .firstName("Damian")
                .lastName("Purgal")
                .pesel("12345678901")
                .idCardNumber("DDD123")
                .phoneNumber("123123123")
                .build();

        //when
        employeeService.save(employee);

        //then
        assertEquals(employeeService.getEmployeeMap()
                .get(employee.getId()), employee);
    }

    @Test
    public void testIsEmployeeCorrectlyFoundByName(){
        //given
        Employee employeeDamian = Employee.builder()
                .id(1L)
                .firstName("Damian")
                .lastName("Purgal")
                .pesel("12345678901")
                .idCardNumber("DDD123")
                .phoneNumber("123123123")
                .build();

        Employee employeeJan = Employee.builder()
                .id(2L)
                .firstName("Jan")
                .lastName("Kowalski")
                .pesel("12345678901")
                .idCardNumber("JJJ123")
                .phoneNumber("123123123")
                .build();

        Map<Long, Employee> employeeMap = new HashMap<>();
        employeeMap.put(employeeDamian.getId(), employeeDamian);
        employeeMap.put(employeeJan.getId(), employeeJan);
        employeeService.setEmployeeMap(employeeMap);

        //when
        Employee foundEmployee = employeeService.findByName("Damian").orElse(null);

        //then
        assertEquals(employeeDamian, foundEmployee);
    }
}
