package lbd.fissst.springlbd.service.employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@Profile("dev")
public class EmployeeServiceImplA implements EmployeeService{

    Logger logger = LoggerFactory.getLogger(EmployeeServiceImplA.class);

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

        logger.info("getEmployeeNickname -> firstName: {}, lastName: {}", firstName, lastName);

        String nickname = prefix +
                firstName.substring(0,3) +
                lastName.substring(0,3) +
                suffix;

        logger.info("getEmployeeNickname -> generated nickname: {}", nickname);

        return  nickname;
    }
}
