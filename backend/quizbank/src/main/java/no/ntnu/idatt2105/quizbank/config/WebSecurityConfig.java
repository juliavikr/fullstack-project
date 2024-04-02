package no.ntnu.idatt2105.quizbank.config;

import no.ntnu.idatt2105.quizbank.security.JWTAuthorizationFilter;
import no.ntnu.idatt2105.quizbank.service.CustomUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * this class configures the web security for the application and sets up the security filter chain
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService customUserDetailsService;

    public WebSecurityConfig(CustomUserDetailsService customUserDetailsService , PasswordEncoder passwordEncoder, CustomUserDetailsService customUserDetailsService1) {
        this.passwordEncoder = passwordEncoder;
        this.customUserDetailsService = customUserDetailsService1;
        logger.info("CustomUserDetailsService and PasswordEncoder are injected successfully.");
    }

    /**
     * Configures the security filter chain that decides which requests should be authenticated
     * @param http the http security to configure
     * @return the security filter chain
     * @throws Exception if an error occurs during configuration
     */
 @Bean
 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
     http
         .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/login","/api/user/login", "/api/register", "/h2-console/**").permitAll()
                .anyRequest().permitAll() // TODO bytte til authenticated() når den er ferdig
            )
             .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Creates a new instance of JWTAuthorizationFilter to be used in the security filter chain
     * @return a new instance of JWTAuthorizationFilter
     */
  @Bean
  public JWTAuthorizationFilter jwtAuthorizationFilter() {
        return new JWTAuthorizationFilter();
  }
    /**
     * Creates a new instance of the AuthenticationManager to be used in the security filter chain
     * @param authenticationConfiguration the authentication configuration to get the authentication manager from
     * @return a new instance of AuthenticationManager
     * @throws Exception if an error occurs during creation
     */
  @Bean
  public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
      return authenticationConfiguration.getAuthenticationManager();
    }
}