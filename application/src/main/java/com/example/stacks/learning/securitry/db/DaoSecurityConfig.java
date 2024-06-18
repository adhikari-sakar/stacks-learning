package com.example.stacks.learning.securitry.db;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.concurrent.TimeUnit;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class DaoSecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserDetailService userDetailService;

    //    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailService);
        return provider;
    }

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
                                        .logoutSuccessUrl("/login")
                                        .and()
                                        .authenticationProvider(daoAuthenticationProvider());
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
}