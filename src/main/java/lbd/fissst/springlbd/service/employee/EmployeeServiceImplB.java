package lbd.fissst.springlbd.service.employee;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class EmployeeServiceImplB implements EmployeeService {

    @Override
    public List findAll() {
        return List.of("b1", "b2", "b3");
    }

    @PostConstruct
    private void postConstruct() {
        System.out.println(findAll().toString());
    }
}
