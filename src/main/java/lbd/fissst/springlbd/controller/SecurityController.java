package lbd.fissst.springlbd.controller;

import lbd.fissst.springlbd.DTO.Security.UserInformationDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("security")
public class SecurityController {

    @GetMapping
    public UserInformationDTO getLoggedUserInformation(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        List<String> roles = authentication.getAuthorities()
                .stream()
                .map(Object::toString)
                .toList();

        return UserInformationDTO.builder()
                .name(authentication.getName())
                .roles(roles)
                .build();
    }
}
