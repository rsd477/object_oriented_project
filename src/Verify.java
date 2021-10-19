// Richard Scott
// 10/26/2020
// Verify - A very important class that
// leverages regex and other methods to verify user input
// and prevent crashes


import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

// Takes in parameters,
// and prompts user 'til response matches
// USING REGEX
public class Verify {
    public static String regex(String patternIn){
        boolean valid = false;
        String userResponse = "";
        Pattern pattern = Pattern.compile(patternIn, Pattern.CASE_INSENSITIVE);
        while(!valid){
            userResponse = Input.scan();
            Matcher matcher = pattern.matcher(userResponse);
            valid = matcher.find();
            if(!valid){Output.print("\u001B[31mINPUT VALID RESPONSE PLEASE TRY AGAIN\u001B[0m\n");}
        }
        return(userResponse);
    }

    //makes sure input is 1 - max
    public static int enteredInt(int max){
        boolean valid = false;
        String userResponse = "";
        int temp = 0;
        while(!valid){
            userResponse = Input.scan();
            try{
                temp = Integer.parseInt(userResponse);
                valid = !((temp > max) | (temp < 1));
            }catch (NumberFormatException ex) {
                valid = false;
            }
            if(!valid){Output.print("\u001B[31mINPUT VALID RESPONSE PLEASE TRY AGAIN\u001B[0m\n");}
        }
        return temp;
    }

    //is it a yes or no?
    public static boolean yes_no(){
        boolean valid = false;
        String userResponse = "";
        Pattern pattern = Pattern.compile("\\byes$|\\bno$", Pattern.CASE_INSENSITIVE);
        while(!valid){
            userResponse = Input.scan();
            Matcher matcher = pattern.matcher(userResponse);
            valid = matcher.find();
            if(!valid){Output.print("\u001B[31mINPUT VALID RESPONSE PLEASE TRY AGAIN\u001B[0m\n");}
        }
        Pattern pattern2 = Pattern.compile("\\byes$", Pattern.CASE_INSENSITIVE);
        Matcher matcher2 = pattern2.matcher(userResponse);
        if(matcher2.find()){
            return true;
        }else{
            return false;
        }
    }

    // is it true or false or t or f?
    public static boolean true_false(){
        boolean valid = false;
        String userResponse = "";
        Pattern pattern = Pattern.compile("\\btrue$|\\bfalse$|\\bf$|\\bt$", Pattern.CASE_INSENSITIVE);
        while(!valid){
            userResponse = Input.scan();
            Matcher matcher = pattern.matcher(userResponse);
            valid = matcher.find();
            if(!valid){Output.print("\u001B[31mINPUT VALID RESPONSE PLEASE TRY AGAIN\u001B[0m\n");}
        }
        Pattern pattern2 = Pattern.compile("\\btrue$", Pattern.CASE_INSENSITIVE);
        Matcher matcher2 = pattern2.matcher(userResponse);
        if(matcher2.find()){
            return true;
        }else{
            return false;
        }
    }

    public static String validDate(){
        boolean valid = false;
        String userResponse = "";
        DateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        sdf.setLenient(false);
        String regexPattern = "\\b\\d{2}-\\d{2}-\\d{4}$";
        while(!valid){
            userResponse = regex(regexPattern);
            try {
                sdf.parse(userResponse);
                valid = true;
            } catch (ParseException e) {
                Output.print("\u001B[31mINPUT VALID DATE, PLEASE TRY AGAIN\u001B[0m\n");
            }
        }
        return userResponse;
    }
}
