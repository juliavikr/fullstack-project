package no.ntnu.idatt2105.quizbank.securityTests;

import no.ntnu.idatt2105.quizbank.security.JwtTokenUtil;
import no.ntnu.idatt2105.quizbank.security.JwtRequestFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.assertj.core.api.Assertions.assertThat;

public class JwtRequestFilterTest {

  @Mock
  private UserDetailsService userDetailsService;

  @Mock
  private JwtTokenUtil jwtTokenUtil;

  @Mock
  private HttpServletRequest request;

  @Mock
  private HttpServletResponse response;

  @Mock
  private FilterChain chain;

  @InjectMocks
  private JwtRequestFilter jwtRequestFilter;

  @BeforeEach
  public void setup() {
    openMocks(this);
  }

  @Test
  public void givenValidToken_whenDoFilter_thenAuthenticate() throws Exception {
    // Arrange
    String token = "Bearer valid_token";
    String username = "user";

    when(request.getHeader("Authorization")).thenReturn(token);
    when(jwtTokenUtil.getUsernameFromToken(anyString())).thenReturn(username);
    when(jwtTokenUtil.validateToken(anyString(), any())).thenReturn(true);

    User userDetails = new User(username, "password", Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);

    // Act
    jwtRequestFilter.doFilterInternal(request, response, chain);

    // Assert
    verify(chain, times(1)).doFilter(request, response);
    assertThat(SecurityContextHolder.getContext().getAuthentication()).isNotNull();
    assertThat(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()).isTrue();
  }

  @Test
  public void givenInvalidToken_whenDoFilter_thenDoNotAuthenticate() throws Exception {
    // Arrange
    when(request.getHeader("Authorization")).thenReturn("Bearer invalid_token");
    when(jwtTokenUtil.getUsernameFromToken(anyString())).thenReturn(null);

    // Act
    jwtRequestFilter.doFilterInternal(request, response, chain);

    // Assert
    verify(chain, times(1)).doFilter(request, response);
    assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
  }

  @Test
  public void givenExpiredToken_whenDoFilter_thenDoNotAuthenticate() throws Exception {
    // Arrange
    when(request.getHeader("Authorization")).thenReturn("Bearer expired_token");
    when(jwtTokenUtil.getUsernameFromToken(anyString())).thenThrow(new ExpiredJwtException(null, null, "Token expired"));

    // Act
    jwtRequestFilter.doFilterInternal(request, response, chain);

    // Assert
    verify(chain, times(1)).doFilter(request, response);
    assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
  }

  // Add more tests for other scenarios like null token, wrong format token, etc.
}