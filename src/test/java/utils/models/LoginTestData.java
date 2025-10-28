package utils.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "scenario", "username", "password" })
public class LoginTestData {

    public String scenario;
    public String username;
    public String password;

    // كونستركتور افتراضي مطلوب من Jackson
    public LoginTestData() {}

    // كونستركتور كامل
    public LoginTestData(String scenario, String username, String password) {
        this.scenario = scenario;
        this.username = username;
        this.password = password;
    }

    // Getter و Setter (اختياري لكن يفضل للمرونة)
    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
