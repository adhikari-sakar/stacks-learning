package com.example.stacks.learning.securitry.form;

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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.concurrent.TimeUnit;

import static com.example.stacks.learning.securitry.UserRoles.ADMIN;
import static com.example.stacks.learning.securitry.UserRoles.STUDENT;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class FormSecurityConfig {

    private final PasswordEncoder passwordEncoder;

//    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(
                        auth -> {
                            try {
                                auth
                                        .anyRequest()
                                        .authenticated()
                                        .and()
                                        .formLogin()
                                        .loginPage("/login")
                                            .permitAll()
                                            .defaultSuccessUrl("/course", true)
                                        .and()
                                        .rememberMe()
                                            .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30))
                                            .key("remember")
                                        .and()
                                        .logout()
                                        .logoutUrl("/logout")
                                            .clearAuthentication(true)
                                            .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                                            .invalidateHttpSession(true)
                                            .deleteCookies("JSESSIONID", "remember-me")
                                            .logoutSuccessUrl("/login");
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
                .authorities(STUDENT.getGrantedAuthorities())
                .build();
        var admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("pass"))
                .authorities(ADMIN.getGrantedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(student, admin);
    }

}