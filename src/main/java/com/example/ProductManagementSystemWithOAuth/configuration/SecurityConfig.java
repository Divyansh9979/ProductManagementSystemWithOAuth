package com.example.ProductManagementSystemWithOAuth.configuration;

import com.example.ProductManagementSystemWithOAuth.service.CustomerUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private GoogleOAuth2SuccessHandler googleOAuth2SuccessHandler;

    @Autowired
    private CustomerUserDetailService customerUserDetailService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .authorizeRequests((authz) -> authz
                        .requestMatchers("/", "/shop/**", "/register").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest()
                        .authenticated()
                )
                .formLogin((formLogin) -> formLogin
                        .loginPage("/login")
                        .permitAll()
                        .failureUrl("/login?error : true")
                        .defaultSuccessUrl("/")
                        .usernameParameter("email")
                        .passwordParameter("password")
                )
                .oauth2Login((oauth2Login) -> oauth2Login
                        .loginPage("/login")
                        .successHandler(googleOAuth2SuccessHandler)
                )
                .logout((logout) -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )
                .exceptionHandling()
                .and()
                .csrf()
                .disable()
                .headers().frameOptions().disable()
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerUserDetailService).passwordEncoder(passwordEncoder());
    }

    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(
                "/resources/**","/static/**","/images/**","/productImages/**");
    }

}
