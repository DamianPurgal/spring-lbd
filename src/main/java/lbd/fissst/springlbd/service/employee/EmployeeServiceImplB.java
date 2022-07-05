package lbd.fissst.springlbd.service.employee;

import lbd.fissst.springlbd.entity.employee.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Profile("prod")
public class EmployeeServiceImplB implements EmployeeService {

    Map<Long, Employee> employeeMap = new HashMap<>();

    @Value("${project.prefix}")
    String prefix;

    @Value("${project.suffix}")
    String suffix;

    @Override
    public List findAll() {
        return List.of("b1", "b2", "b3");
    }

    @PostConstruct
    private void postConstruct() {
        System.out.println("EmployeeServiceImplB postConstruct execute...");
        System.out.println(findAll().toString());
    }

    @Override
    public String getEmployeeNickname(String firstName, String lastName) {
        return prefix + firstName.substring(0,3) + lastName.substring(0,3) + suffix;
    }

    @Override
    public Optional<Employee> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public Employee save(Employee employee) {
        return null;
    }
}
