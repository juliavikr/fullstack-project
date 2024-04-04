package no.ntnu.idatt2105.quizbank.security;

/*@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {
     private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Override
    protected void doFilterInternal(
            @Nonnull HttpServletRequest request,
            @Nonnull HttpServletResponse response,
            @Nonnull FilterChain filterChain)
            throws ServletException, IOException {

        logger.debug("JWTAuthorizationFilter is running");
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            try {
                token = token.substring(7); // Fjern "Bearer " fra token
                DecodedJWT jwt = verifyToken(token);

                String username = jwt.getSubject(); // Anta at brukernavn er satt som subject
                List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } catch (JWTVerificationException exception) {
                // Logger eller håndterer feilen basert på dine behov
            }
        }

        filterChain.doFilter(request, response);
    }

    private DecodedJWT verifyToken(String token) throws JWTVerificationException {
        // Dette skal være en sterk nøkkel
        String secretKey = "YourSecretKey";
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = com.auth0.jwt.JWT.require(algorithm).build();
        return verifier.verify(token);
    }
}
*/
