package dao;

public class UserSession {
    private static UserSession session;
    private static String user = "";

    private UserSession(){}

    private UserSession(String newUser){
        user = newUser;
    }

    public static UserSession getInstance(String newUser){
        if(session == null){
            session = new UserSession(newUser);
        }
        return session;
    }

    public static String getSessionUser(){
        return user;
    }

    public static void clearSession(){
        user = "";
        session = null;
    }
}