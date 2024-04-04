package no.ntnu.idatt2105.quizbank.controller;

/**
 * Class that handles the HTTP requests for authentication
 * Manages the interaction between AuthenticationManager and JwtUtil
 */
/*@RestController
@RequestMapping("/api")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Method for generating a JWT token for a user
     * Called when a POST request is sent to /api/login
     * @param loginRequest LoginRequest object containing the login details
     * @return ResponseEntity containing a JWT token if the login was successful or UNAUTHORIZED status if unsuccessful
     */

   /* @PostMapping("/api/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest loginRequest) {
        logger.debug("Attempting to authenticate user: {}", loginRequest.getUsername());
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            final String jwt = jwtUtil.generateToken(authentication);
            logger.info("Authentication successful for user: {}", loginRequest.getUsername());
            return ResponseEntity.ok(new AuthenticationResponse(jwt));
        } catch (AuthenticationException e) {
            logger.error("Authentication failed for user: {}. Error: {}", loginRequest.getUsername(), e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    // Inner class for sending response
    private static class AuthenticationResponse {
        private final String jwt;

        public AuthenticationResponse(String jwt) {
            this.jwt = jwt;
        }

        public String getJwt() {
            return jwt;
        }
    }
}*/
