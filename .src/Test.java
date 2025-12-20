import auth.AuthService; 
import model.User; 
import ui.Dashboard;

public class Test { 
    public static void main(String[] args) { 
        AuthService auth = new AuthService(); 
        User user = auth.loginOrSignUp(); 
        
        Dashboard dashboard = new Dashboard(user); 
        dashboard.show(); } }