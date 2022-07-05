package lbd.fissst.springlbd;

import lbd.fissst.springlbd.service.employee.EmployeeServiceImplA;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
