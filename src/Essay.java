// Richard Scott
// 10/26/2020
// Essay - handles long form Essay formatted question

public class Essay extends Question{
    private static final long serialVersionUID = 46890347984613L;
    protected int amt_of_responses;

    public Essay(){
        this.amt_of_responses = 0;
        super.prompt = "";
    }

    public void add(){
        Output.print("Enter the prompt for your essay question:\n");
        super.prompt = Input.scan();

        Output.print("How many responses per question do you want? (max: 2):\n");
        this.amt_of_responses = Verify.enteredInt(2);
    }

    public void modify(){
        Output.print("Do you wish to modify the prompt?\n");
        if(Verify.yes_no()){
            Output.print("\n" + super.prompt + "\n");
            Output.print("Enter the new prompt for your essay question:\n");
            super.prompt = Input.scan();
        }
    }

    public void display(){
        Output.print(super.prompt + "\n");
    }

    public String take(){
        String answer = "";
        Output.print(super.prompt + "\n");
        for(int i = 0; i < this.amt_of_responses; i++){
            Output.print(Character.toString((char) (65+i)) + ") ");
            answer += Character.toString((char) (65+i)) + ") ";
            answer += Input.scan();
            answer += "\n\n";
        }
        return answer;
    }
}