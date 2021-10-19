// Richard Scott
// 10/26/2020
// True/False questions are defined

import java.util.ArrayList;

public class True_False extends Multiple_Choice{
    private static final long serialVersionUID = 10633618356403L;

    public True_False(){

        super.prompt = "";
        super.options = new ArrayList<>();
        super.options.add("F");
        super.options.add("T");
        super.amtOfQs = 2;
        super.amtOfAns = 1;
    }
    @Override
    public void add(){
        Output.print("Enter the prompt for your True/False question:\n");
        super.prompt = Input.scan();
    }

    @Override
    public void modify(){
        char a = 'A';
        String response = "";
        int temp = 0;
        Output.print("Do you wish to modify the prompt?\n");
        if(Verify.yes_no()){
            Output.print("\n" + super.prompt + "\n");
            Output.print("Enter the new prompt for your true/false question:\n");
            super.prompt = Input.scan();
        }
    }

    @Override
    public void display(){
        Output.print(super.prompt + "\n");
        Output.print("T/F\n");
    }

    @Override
    public String take(){
        String response = "";
        Output.print(super.prompt + "\n");
        Output.print("T/F\n");
        if(Verify.true_false()){
            response = "T";
        } else {
            response = "F";
        }
        return response;
    }
}

