package lbd.fissst.springlbd.service.employee;

import lbd.fissst.springlbd.entity.employee.Employee;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@Profile("dev")
@Getter
@Setter
public class EmployeeServiceImplA implements EmployeeService{

    private Map<Long, Employee> employeeMap = new HashMap<>();

    private Logger logger = LoggerFactory.getLogger(EmployeeServiceImplA.class);

    @Value("${project.prefix}")
    private String prefix;

    @Value("${project.suffix}")
    private String suffix;

    @Override
    public List findAll() {
        return List.of("a1", "a2", "a3");
    }

    @PostConstruct
    private void postConstruct() {
        System.out.println(findAll().toString());
    }

    @Override
    public String getEmployeeNickname(String firstName, String lastName) {

        logger.info("getEmployeeNickname -> firstName: {}, lastName: {}", firstName, lastName);

        String nickname = prefix +
                firstName.substring(0,3) +
                lastName.substring(0,3) +
                suffix;

        logger.info("getEmployeeNickname -> generated nickname: {}", nickname);

        return  nickname;
    }

    @Override
    public Optional<Employee> findByName(String name) {
        return employeeMap.values()
                .stream()
                .filter(
                        employee -> employee.getFirstName().equals(name) ||
                                employee.getLastName().equals(name)
                        )
                .findFirst();
    }

    @Override
    public Employee save(Employee employee) {
        return employeeMap.put(employee.getId(), employee);
    }
}
