package no.ntnu.idatt2105.quizbank.config;

import lombok.RequiredArgsConstructor;
import no.ntnu.idatt2105.quizbank.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Class that configures the application.
 * It defines the beans for the authentication provider, authentication manager,
 * password encoder, and user details service.
 * It also enables transaction management.
 * The user details service is configured to use the custom user repository.
 * The password encoder is configured to use BCrypt hashing.
 * The authentication provider is configured to use the user details service and password encoder.
 * @version 1.0
 * @Author Andrea Amundsen, Julia Vik Remøy
 */
@Configuration
@RequiredArgsConstructor
@EnableTransactionManagement
public class ApplicationConfig {

    private final UserRepository userRepository;

    /**
     * Defines the bean for the authentication provider.
     * The authentication provider is configured to use the user details service and password encoder.
     *
     * @return The {@link AuthenticationProvider} bean.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * Defines the bean for the authentication manager.
     * The authentication manager is configured to use the authentication configuration.
     *
     * @param config The {@link AuthenticationConfiguration} object.
     * @return The {@link AuthenticationManager} bean.
     * @throws Exception If an error occurs.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Defines the bean for the password encoder.
     * The password encoder is configured to use BCrypt hashing.
     *
     * @return The {@link PasswordEncoder} bean.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Defines the bean for the user details service.
     * The user details service is configured to use the custom user repository.
     *
     * @return The {@link UserDetailsService} bean.
     */
  @Bean
    public UserDetailsService userDetailsService() {
    return username -> userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
}
