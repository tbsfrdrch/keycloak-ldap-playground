package de.tbsfrdrch.keycloak.ldap.playground.enterprise;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.stream.Collectors;

@RestController
public class EnterpriseController {

    @GetMapping
    public String status() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "User: " + authentication.getPrincipal() +
                " (" + authentication.getAuthorities().stream().map(Object::toString).collect(Collectors.joining(", ")) + ")";
    }

    @GetMapping("/replicate/{item}")
    public String replicate(@PathVariable String item) {
        return item;
    }

    @GetMapping("/warp")
    public String warpStatus() {
        return "FUNCTIONAL";
    }

    @GetMapping("/security")
    @Secured("ROLE_SECURITY_OFFICER")
    public String securityStatus() {
        return "RED";
    }

    @GetMapping("/klingonsecurityonly")
    @PreAuthorize("hasRole('KLINGON') and hasRole('SECURITY_OFFICER')")
    public String klingonSecurityOnly() {
        return "Qapla’";
    }

    @GetMapping("/engage")
    @RolesAllowed({"CAPTAIN", "COMMANDING_OFFICER"})
    public String engage() {
        return "Whoooosh...";
    }
}
