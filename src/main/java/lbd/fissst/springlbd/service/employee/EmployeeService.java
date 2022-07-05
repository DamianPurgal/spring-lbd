package lbd.fissst.springlbd.service.employee;

import lbd.fissst.springlbd.entity.employee.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List findAll();

    Optional<Employee> findByName(String name);

    Employee save(Employee employee);

    String getEmployeeNickname(String firstName, String lastName);
}
