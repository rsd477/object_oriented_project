// Richard Scott
// 10/26/2020
// Multiple_Choice questions

import java.util.ArrayList;
import java.util.List;

public class Multiple_Choice extends Question{
    private static final long serialVersionUID = 96762409837514L;
    List<String> options;
    int amtOfQs;
    int amtOfAns;

    public Multiple_Choice(){
        super.prompt = "";
        this.options = new ArrayList<>();
        this.amtOfQs = 4;
        this.amtOfAns = 1;
    }

    public void add(){
        Output.print("Enter the prompt for your multiple choice question:\n");
        super.prompt = Input.scan();

        Output.print("How many choices do you want? (max: 5) \n");
        this.amtOfQs = Verify.enteredInt(5);

        Output.print("How many user selections do you want? (max: 5) \n");
        this.amtOfAns = Verify.enteredInt(5);

        for(int i = 0; i < this.amtOfQs; i++){
            Output.print("Enter choice #" + (i+1) + "\n");
            this.options.add(Input.scan());
        }
    }

    public void modify(){
        char a = 'A';
        String response = "";
        String pattern = "\\b[A-" + Character.toString((char) (65+(this.amtOfQs-1))) + "]$";
        int temp = 0;
        Output.print("Do you wish to modify the prompt?\n");
        if(Verify.yes_no()){
            Output.print("\n" + super.prompt + "\n");
            Output.print("Enter the new prompt for your multiple choice question:\n");
            super.prompt = Input.scan();
        }
        Output.print("Do you wish to modify the choices?\n");
        if(Verify.yes_no()){
            Output.print("Which choice do you want to modify?\n");
            for(int i = 0; i < this.amtOfQs; i++){
                Output.print(String.format("%s) %-10s\t",
                        (String.valueOf((char)(a+i))), this.options.get(i)));
            }
            Output.print("\n");
            response = Verify.regex(pattern);
            response = response.toUpperCase();
            temp = response.charAt(0) -'A';
            options.set(temp, Input.scan());
        }
    }

    public void display(){
        char a = 'A';
        Output.print(super.prompt + "\n");
        for(int i = 0; i < this.amtOfQs; i++){
            Output.print(String.format("%s) %s \t",
                    (String.valueOf((char)(a+i))), this.options.get(i)));
        }
        Output.print("\n");
    }

    public String take(){
        char a = 'A';
        String pattern = "\\b[A-" + Character.toString((char) (65+(this.amtOfQs-1))) + "]$";
        String userResponse = "";
        Output.print(super.prompt + "\n");
        Output.print("Enter "+ this.amtOfAns + " choice[s] separated by new lines [ENTER]:\n");
        for(int i = 0; i < this.amtOfQs; i++){
            Output.print(String.format("%s) %s \t",
                    (String.valueOf((char)(a+i))), this.options.get(i)));
        }
        Output.print("\n");
        for(int i = 0; i < this.amtOfAns; i++){
            userResponse += Verify.regex(pattern).toUpperCase().strip() + "  ";
        }
        return userResponse;
    }

    public int getAmtAns(){
        return this.amtOfAns;
    }

    public int getAmtQs(){
        return this.amtOfQs;
    }

    public String getOption(int index){
        return this.options.get(index);
    }
}

