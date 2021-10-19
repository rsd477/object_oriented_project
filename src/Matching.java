// Richard Scott
// 10/26/2020
// Matching - takes care of matching type questions

import java.util.ArrayList;
import java.util.List;

public class Matching extends Question{
    private static final long serialVersionUID = 90243184204729L;
    List<String> choicesL;
    List<String> choicesR;
    int pairs;

    public Matching(){
        this.choicesL = new ArrayList<>();
        this.choicesR = new ArrayList<>();
        this.pairs = 0;
        super.prompt = "";
    }

    public void add(){
        char a = 'A';
        Output.print("Enter the prompt for your matching question:\n");
        super.prompt = Input.scan();

        Output.print("How many pairs of choices do you want? (max: 10) \n");
        this.pairs = Verify.enteredInt(10);

        for(int i = 0; i < this.pairs; i++){
            Output.print("Enter choice " + String.valueOf((char)(a+i)) + ":\n");
            this.choicesL.add(Input.scan());
            Output.print("Enter choice " + (i+1) + ":\n");
            this.choicesR.add(Input.scan());
        }
    }

    public void modify(){
        char a = 'A';
        String response = "";
        String pattern = "\\b[A-" + Character.toString((char) (65+(this.pairs-1))) + "]$|\\b[1-" + this.pairs + "]$";
        if(this.pairs == 10){
            pattern = "\\b[A-J]$|\\b[1-9]$|\\b10$";
        }
        int temp = 0;
        Output.print("Do you wish to modify the prompt?\n");
        if(Verify.yes_no()){
            Output.print("\n" + super.prompt + "\n");
            Output.print("Enter the new prompt for your matching question:\n");
            super.prompt = Input.scan();
        }
        Output.print("Do you wish to modify the choices?\n");
        if(Verify.yes_no()){
            Output.print("Which choice do you want to modify? (Ex: A or 1)\n");
            for(int i = 0; i < this.pairs; i++){
                Output.print(String.format("%s) %-10s\t%d) %-10s\n",
                        (String.valueOf((char)(a+i))),
                        this.choicesL.get(i), (i+1),
                        this.choicesR.get(i)));
            }
            response = Verify.regex(pattern);
            response = response.toUpperCase();
            if((response.charAt(0) -'A') < 0){ // if its a number
                temp = Integer.parseInt(response); // make sure it's an option
                choicesR.set(temp-1, Input.scan());
            } else {
                temp = response.charAt(0) -'A';
                choicesL.set(temp, Input.scan());
            }

        }
    }

    public void display(){
        char a = 'A';
        Output.print(super.prompt + "\n");
        for(int i = 0; i < this.pairs; i++){
            Output.print(String.format("%s) %-20s %d) %-20s\n",
                    (String.valueOf((char)(a+i))),
                    this.choicesL.get(i), (i+1),
                    this.choicesR.get(i)));
        }
    }


    public String take(){
        char a = 'A';
        String pattern = "\\b[A-" + Character.toString((char) (65+(this.pairs-1))) + "]\\s[1-" + this.pairs + "]$";
        if(this.pairs == 10){
            pattern = "\\b[A-J]\\s[1-9]$|\\b[A-J]\\s10$";
        }
        String userResponse = "";
        Output.print(super.prompt + "\n");
        Output.print("Answer in form: \'A 1\' [ENTER]\n");
        for(int i = 0; i < this.pairs; i++){
            Output.print(String.format("%s) %-20s %d) %-20s\n",
                    (String.valueOf((char)(a+i))),
                    this.choicesL.get(i), (i+1),
                    this.choicesR.get(i)));
        }
        for(int i = 0; i < this.pairs; i++){
            userResponse += Verify.regex(pattern) + "  ";
        }
        return userResponse;
    }

    public int getPairs(){
        return this.pairs;
    }

}
