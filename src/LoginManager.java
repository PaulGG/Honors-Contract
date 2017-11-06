public class LoginManager {

    private String user;
    private String password;
    static LoginManager l;

    private LoginManager() {}

    public String getActiveUser() {
        if(user == null) {
            return "none";
        }
        return user;
    }

    public String getActivePassword() {
        if(password == null) {
            return "none";
        }
        return password;
    }

    public void setPassword(String password) { this.password = password; }

    public void setUser(String user) {
        this.user = user;
    }

    public static LoginManager getInstance() {
        if(l == null) {
            return l = new LoginManager();
        }
        return l;
    }
}
