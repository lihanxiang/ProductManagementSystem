package po;

public class User {
    private String username;
    private String password;
    private String phone;
    private String email;
    private String gender;
    private String description;
    private String status;
    private String verifyCode;

    /*public User(String username, String password, String phone, String email){
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }*/

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPhone() {
        return phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getGender() {
        return gender;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
    public String getVerifyCode() {
        return verifyCode;
    }
}
