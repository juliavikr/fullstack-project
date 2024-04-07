package no.ntnu.idatt2105.quizbank.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Class that configures the web security of the application.
 * It defines the beans for the password encoder and the web configuration.
 * The web configuration is configured to allow cross-origin requests from the frontend application.
 * @version 1.0
 * @Author Andrea Amundsen, Julia Vik Remøy
 */
@Configuration
@EnableWebSecurity
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    /**
     * Defines the bean for the password encoder.
     * The password encoder is configured to use BCrypt hashing.
     *
     * @return The {@link PasswordEncoder} bean.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173") // Legg til URLen til din frontend-applikasjon her
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}

