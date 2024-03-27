package no.ntnu.idatt2105.quizbank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests(authorizeRequests ->
                authorizeRequests
                        // Hovedsiden skal være tilgjengelig for alle
                    .requestMatchers("/").permitAll()
                        // alle andre endepunkter krever autentisering fra brukeren
                    .anyRequest().authenticated()
            )
                // bruker HTTP Basic-autentisering for alle endepunkter (sender brukernavn og passord i klartekst)
                .httpBasic(withDefaults());
        // konfiguurerer ytterligere sikkerhetsinnstillinger her

        return http.build();
    }
    @Bean
    /*
     * Method for encoding passwords
     */
     public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    /*
       Uses this method for requests that do not require to go through the security filter chain
     */
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/ignore1", "/ignore2");
    }

}
