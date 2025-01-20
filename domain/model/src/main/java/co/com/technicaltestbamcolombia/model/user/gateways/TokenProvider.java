package co.com.technicaltestbamcolombia.model.user.gateways;

public interface TokenProvider {
    String generateToken(String username);
    boolean validateToken(String token);
    String getUsernameFromToken(String token);
}
