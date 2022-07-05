package lbd.fissst.springlbd;

import lbd.fissst.springlbd.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLbdApplication {

    @Autowired
    EmployeeService employeeService;

    public static void main(String[] args) {
        SpringApplication.run(SpringLbdApplication.class, args);
    }

}
