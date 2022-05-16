package business;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.UserDAO;

public class UserBUSINESS {
    
    public static Boolean checkPass(String pass){

        Boolean checkedMinuscule = checkMinuscule(pass);
        Boolean checkedCapital = checkCapital(pass);
        Boolean checkedNumber = checkNumber(pass);
        Boolean checkedSpecial = checkSpecial(pass);
        Boolean checkedLength = checkLength(pass);

        if(checkedMinuscule && checkedCapital && checkedNumber && checkedSpecial && checkedLength) return true;
        return false;
    }

    private static Boolean checkMinuscule(String pass){
        Pattern p = Pattern.compile("[a-z]");
        Matcher m = p.matcher(pass);
        return m.find();
    }

    private static Boolean checkCapital(String pass){
        Pattern p = Pattern.compile("[A-Z]");
        Matcher m = p.matcher(pass);
        return m.find();
    }

    private static Boolean checkNumber(String pass){
        Pattern p = Pattern.compile("[0-9]");
        Matcher m = p.matcher(pass);
        return m.find();
    }

    private static Boolean checkSpecial(String pass){
        Pattern p = Pattern.compile("[~!@#$%^&*()_+{}\\[\\]:;,.<>/?-]");
        Matcher m = p.matcher(pass);
        return m.find();
    }

    private static Boolean checkLength(String pass){
        if(pass.length() >= 8) return true;
        return false;
    }

    public static Boolean checkUser(String user) throws Exception{

        Boolean userExist = UserDAO.getInstance().checkUserExist(user);

        if(userExist) return true;
        return false;
    }
    
}