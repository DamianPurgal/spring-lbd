package lbd.fissst.springlbd;

import lbd.fissst.springlbd.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SpringLbdApplication {

    @Autowired
    EmployeeService employeeService;

    @PostConstruct
    public void postConstructMethod(){
        System.out.println("SpringLbdApplication postConstruct execute...");
        employeeService.findAll();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringLbdApplication.class, args);
    }

}
