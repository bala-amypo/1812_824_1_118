@Component
public class JwtTokenProvider {

    @Value("${jwt.secret:default-secret}")
    private String secret;

    public String generateToken(AppUser user) {
        return "TOKEN_" + user.getUsername();
    }

    public boolean validateToken(String token) {
        return token != null && token.startsWith("TOKEN_");
    }
}
