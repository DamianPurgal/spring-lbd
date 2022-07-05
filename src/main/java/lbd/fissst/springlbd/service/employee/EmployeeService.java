package lbd.fissst.springlbd.service.employee;

import java.util.List;

public interface EmployeeService {

    List findAll();

    String getEmployeeNickname(String firstName, String lastName);
}
