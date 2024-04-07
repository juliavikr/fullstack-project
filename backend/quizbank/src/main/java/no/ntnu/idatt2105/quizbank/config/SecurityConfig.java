package no.ntnu.idatt2105.quizbank.config;

import no.ntnu.idatt2105.quizbank.security.JwtRequestFilter;
import no.ntnu.idatt2105.quizbank.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * Class that configures the security of the application.
 * It defines the beans for the JWT request filter and the security filter chain.
 * It enables web security and method security.
 * The security filter chain is configured to disable CSRF, authorize requests, and create a stateless session.
 * The security filter chain is also configured to add the JWT request filter before the username password authentication filter.
 * @version 1.0
 * @Author Andrea Amundsen, Julia Vik Remøy
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Bean
    public JwtRequestFilter jwtRequestFilter() {
        return new JwtRequestFilter(userDetailsService, jwtTokenUtil);
    }

    /**
     * Defines the bean for the security filter chain.
     * The security filter chain is configured to disable CSRF, authorize requests, and create a stateless session.
     * The security filter chain is also configured to add the JWT request filter before the username password authentication filter.
     *
     * @param http The {@link HttpSecurity} object.
     * @return The {@link SecurityFilterChain} bean.
     */
 @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors().and()
            .csrf().disable()
            .authenticationProvider(authenticationProvider)
            .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                .requestMatchers("/api/user/login/**", "/api/user/register/**").permitAll()
                .anyRequest().authenticated()
            )
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class); // Legg til JWT filter

        return http.build();
    }

}





