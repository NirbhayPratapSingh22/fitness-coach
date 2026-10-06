package fitness_coach.controller;

public class AuthResponse {

    private Long id;
    private String username;
    private String email;
    private Long userProfileId;
    private String message;
    private String token;

    public AuthResponse(
            Long id,
            String username,
            String email,
            Long userProfileId,
            String message,
            String token
    ) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.userProfileId = userProfileId;
        this.message = message;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Long getUserProfileId() {
        return userProfileId;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }
}