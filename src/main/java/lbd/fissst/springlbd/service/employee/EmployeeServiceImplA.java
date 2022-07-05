package lbd.fissst.springlbd.service.employee;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@Qualifier("EmployeeServiceImplA")
public class EmployeeServiceImplA implements EmployeeService{

    @Override
    public List findAll() {
        return List.of("a1", "a2", "a3");
    }

    @PostConstruct
    private void postConstruct() {
        System.out.println(findAll().toString());
    }
}