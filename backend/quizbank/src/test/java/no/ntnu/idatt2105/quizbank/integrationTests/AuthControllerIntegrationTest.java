package no.ntnu.idatt2105.quizbank.integrationTests;

/*@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setup() {
        userRepository.deleteAll();
        User user = new User("user", passwordEncoder.encode("password"));
        userRepository.save(user);
    }

    @Test
    public void whenValidLogin_thenAuthenticateAndReturnJwt() throws Exception {
        // Her vil du opprette en LoginRequest med gyldige legitimasjonsopplysninger
        LoginDTO loginDTO;
        loginDTO = new LoginDTO();
        loginDTO.setUsername("user");
        loginDTO.setPassword("password");

        // Utfører en POST-forespørsel mot /api/login og forventer et JWT-token
        mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(loginDTO)))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.jwt").exists());
    }

    @Test
    public void whenInvalidLogin_thenUnauthorized() throws Exception {
        // Oppretter en LoginRequest med ugyldige legitimasjonsopplysninger
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("user");
        loginDTO.setPassword("wrongpassword");

        // Utfører en POST-forespørsel mot /api/login og forventer en 401 Unauthorized status
        mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(loginDTO)))
                .andExpect(status().isUnauthorized());
    }
}
*/

