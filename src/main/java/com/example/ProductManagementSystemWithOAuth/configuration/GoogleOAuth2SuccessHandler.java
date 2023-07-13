package com.example.ProductManagementSystemWithOAuth.configuration;

import com.example.ProductManagementSystemWithOAuth.model.Role;
import com.example.ProductManagementSystemWithOAuth.model.User;
import com.example.ProductManagementSystemWithOAuth.repository.RoleRepository;
import com.example.ProductManagementSystemWithOAuth.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /*Implemented Constructor Injection:
    This approach promotes better testability and reduces coupling.
     */
    @Autowired
    public GoogleOAuth2SuccessHandler(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        String email = token.getPrincipal().getAttributes().get("email").toString();
        if (userRepository.findUserByEmail(email).isEmpty()) {
            try {
                User user = new User();
                user.setFirstName(token.getPrincipal().getAttributes().get("given_name").toString());
                user.setLastName(token.getPrincipal().getAttributes().get("family_name").toString());
                user.setEmail(email);
                Optional<Role> roleOptional = roleRepository.findById(2);
                if(roleOptional.isPresent()) {
                    List<Role> roles = new ArrayList<>();
                    roles.add(roleOptional.get());
                    user.setRoles(roles);
                    userRepository.save(user);
                } else {
                    // Handled the absence of the role appropriately based on requirements
                    log.error("Role not found with ID 2");
                }
            } catch (Exception e) {
                // Handled the exception appropriately based on requirements
                log.error("Error occurred during user creation..."+e.getMessage());
            }
        }
        redirectStrategy.sendRedirect(request, response, "/");
    }
}
