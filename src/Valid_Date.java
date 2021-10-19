// Richard Scott
// 10/26/2020
// Valid Date uses some advanced regex i worked a little to hard on

public class Valid_Date extends Question implements java.io.Serializable{
    private static final long serialVersionUID = 10633618356403L;
    public Valid_Date(){
        super.prompt = "";
    }

    public void add(){
        Output.print("Enter the prompt for your date question:\n");
        super.prompt = Input.scan();
    }

    public void modify(){
        Output.print("Do you wish to modify the prompt?\n");
        if(Verify.yes_no()){
            Output.print("\n" + super.prompt + "\n");
            Output.print("Enter the new prompt for your date question:\n");
            super.prompt = Input.scan();
        }
    }

    public void display(){
        Output.print(super.prompt + "\n");
        Output.print("A date should be entered in the following format: MM-DD-YYYY\n");
    }

    public String take(){
        Output.print(super.prompt + "\n");
        Output.print("A date should be entered in the following format: MM-DD-YYYY\n");
        return Verify.validDate();
    }
}
