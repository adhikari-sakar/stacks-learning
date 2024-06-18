package com.example.stacks.learning.securitry.basic;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static com.example.stacks.learning.securitry.UserRoles.ADMIN;
import static com.example.stacks.learning.securitry.UserRoles.STUDENT;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BasicSecurityConfig {

    private final PasswordEncoder passwordEncoder;

//    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
//                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .and()
                .csrf().disable()
                .authorizeHttpRequests(
                        auth -> {
                            try {
                                auth
//                                        .antMatchers(HttpMethod.GET, "/api/v1/students/**").hasAnyRole(STUDENT.name(), ADMIN.name())
//                                        .antMatchers(HttpMethod.POST, "/api/v1/students/**").hasAuthority(STUDENT_WRITE.getPermission())
//                                        .antMatchers(HttpMethod.PUT, "/api/v1/students/**").hasAuthority(STUDENT_WRITE.getPermission())
//                                        .antMatchers(HttpMethod.DELETE, "/api/v1/students/**").hasAuthority(STUDENT_WRITE.getPermission())
                                        .anyRequest()
                                        .authenticated()
                                        .and()
                                        .httpBasic();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
        return http.build();
    }

//    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/", "index", "/css/*", "/js/*");
    }

//    @Bean
    public UserDetailsService userDetailsService() {
        var student = User.builder()
                .username("user")
                .password(passwordEncoder.encode("pass"))
//                .roles(STUDENT.name())
                .authorities(STUDENT.getGrantedAuthorities())
                .build();
        var admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("pass"))
//                .roles(ADMIN.name())
                .authorities(ADMIN.getGrantedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(student, admin);
    }

}