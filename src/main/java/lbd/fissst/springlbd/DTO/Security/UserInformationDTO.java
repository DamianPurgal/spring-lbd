package lbd.fissst.springlbd.DTO.Security;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserInformationDTO {
    private String name;
    private List<String> roles;
}
