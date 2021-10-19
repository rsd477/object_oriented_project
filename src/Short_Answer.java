// Richard Scott
// 10/26/2020
// Short answer question definition

public class Short_Answer extends Essay{
    int charLim;
    private static final long serialVersionUID = 90243184220729L;

    public Short_Answer(){
        super.amt_of_responses = 1;
        this.charLim = 250;
        super.prompt = "";
    }

    @Override
    public void add(){
        Output.print("Enter the prompt for your short answer question:\n");
        super.prompt = Input.scan();

        Output.print("What character limit do you want? (max: 250):\n");
        this.charLim = Verify.enteredInt(250);
    }
    @Override
    public void modify(){
        Output.print("Do you wish to modify the prompt?\n");
        if(Verify.yes_no()){
            Output.print("\n" + super.prompt + "\n");
            Output.print("Enter the new prompt for your short answer question:\n");
            super.prompt = Input.scan();
        }
        Output.print("Do you wish to change the character limit?:\n");
        if(Verify.yes_no()){
            Output.print("What character limit do you want? (max: 250):\n");
            this.charLim = Verify.enteredInt(250);
        }
    }

    @Override
    public String take(){
        String answer = "";
        Output.print(super.prompt + " (case insensitive) [Char Limit: " + charLim + "]\n");
        answer = Input.scan();
        if(answer.length() > charLim){
            answer = answer.substring(0,charLim);
            Output.print("Only first "+ charLim + " characters saved.");
        }
        return answer.toLowerCase();
    }

    public int getCharLim(){
        return this.charLim;
    }
}
