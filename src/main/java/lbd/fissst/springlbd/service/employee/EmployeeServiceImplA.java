package lbd.fissst.springlbd.service.employee;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@Profile("dev")
public class EmployeeServiceImplA implements EmployeeService{

    @Value("${project.prefix}")
    String prefix;

    @Value("${project.suffix}")
    String suffix;

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
        return prefix +
                firstName.substring(0,3) +
                lastName.substring(0,3) +
                suffix;
    }
}
