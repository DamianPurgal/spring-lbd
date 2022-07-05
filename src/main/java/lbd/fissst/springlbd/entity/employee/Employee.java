package lbd.fissst.springlbd.entity.employee;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Employee {

    private Long id;

    private String firstName;

    private String lastName;

    private String pesel;

    private String idCardNumber;

    private String phoneNumber;

}