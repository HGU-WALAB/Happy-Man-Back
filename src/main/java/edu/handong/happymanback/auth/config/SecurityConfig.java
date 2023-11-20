package edu.handong.happymanback.auth.config;

import edu.handong.happymanback.auth.service.AuthService;
import edu.handong.happymanback.utils.enums.Role;
import edu.handong.happymanback.auth.jwt.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthService authService;

    @Value("${custom.jwt.secret}")
    private String secretKey;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(new JwtTokenFilter(authService, secretKey), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(request -> request
                        .requestMatchers(
                                "/api/happyman/auth/**","/error"
                        )
                        .permitAll()
                )
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/happyman/admin/**")
                        .hasAuthority(Role.ADMIN.name())
                )
                .authorizeHttpRequests(request -> request
                        .requestMatchers(
                                "/api/happyman/**"
                        ).authenticated()
                );
        return http.build();
    }
}