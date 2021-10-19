public class Test extends Survey{
    private static final long serialVersionUID = 76664924001555L;
    UserResponse correctAnswers;

    public Test(String testName){
        super(testName);
        this.correctAnswers = new UserResponse();
    }

    public void grade(UserResponse userAnswer){
        double pointsPerQ = 100 / (double) savedQuestions.size();
        double score = 0;
        double upgradablePoints = 0;

        for(int i = 0; i < savedQuestions.size(); i++) {

            if(savedQuestions.get(i) instanceof Short_Answer){
                if(userAnswer.getResponse(i).equals(correctAnswers.getResponse(i))){
                    score += pointsPerQ;
                }
            } else if(savedQuestions.get(i) instanceof Essay) {
                upgradablePoints += pointsPerQ;
            } else {
                if(userAnswer.getResponse(i).equals(correctAnswers.getResponse(i))){
                    score += pointsPerQ;
                }
            }
        }


        if(upgradablePoints == 0){
            Output.print("\n\nYou received a score of " + score + " out of 100.\n\n");
        } else {
            Output.print("\n\nYou received a(n) "
                    + score
                    + " on the test. The test was worth 100 points, but only "
                    + (100-upgradablePoints) +" of\n"
                    + "those points could be auto graded because there was "
                    + Integer.toString((int)(upgradablePoints/pointsPerQ))
                    + " essay question.\n\n");
        }
    }

    @Override
    public void addQuestion(int type){
        Question temp;
        switch(type){
            case 1:
                temp = new True_False();
                break;
            case 2:
                temp = new Multiple_Choice();
                break;
            case 3:
                temp = new Short_Answer();
                break;
            case 4:
                temp = new Essay();
                break;
            case 5:
                temp = new Valid_Date();
                break;
            case 6:
                temp = new Matching();
                break;
            default:
                Output.print("Survey:43 THIS SHOULDN'T HAPPEN"); // if this is thrown, retire
                return;
        }
        temp.add();
        this.correctAnswers.addResponse(modifyAnswer(temp));
        this.savedQuestions.add(temp);
    }

    @Override
    public void modifyQuestion(int question){
        if(this.savedQuestions.size() < question){
            Output.print("\u001B[31mThere aren't "+ question +" questions in the test at this time\u001B[0m\n");
        } else if(question < 1){
            Output.print("\u001B[31mIndex must be greater than 0\u001B[0m\n");
        } else {
            Question temp = this.savedQuestions.get(question - 1);
            this.savedQuestions.get(question - 1).modify();
            if(!(temp instanceof Essay)){
                Output.print("Do you want to modify the accepted Answer? [yes/no]");
                if(Verify.yes_no()){
                    String newAnswer = modifyAnswer(temp);
                    this.correctAnswers.setResponse((question-1),newAnswer);
                }
            }
        }
    }

    @Override
    public Test load(String filePath){
        return Input.deserialize(filePath);
    }

    public void displayTestWithAns(){
        Question temp;
        String answer;
        for(int i = 0; i < this.savedQuestions.size(); i++){
            Output.print((i+1) + ") ");
            temp = this.savedQuestions.get(i);
            temp.display();
            answer = correctAnswers.getResponse(i);
            if(temp instanceof True_False) {
                Output.print("The correct answer is " + answer);
            } else if (temp instanceof Multiple_Choice) {
                Output.print("The correct choice[s] is/are: ");
                int amtOfAns = ((Multiple_Choice) temp).getAmtAns();
                String[] split = answer.split("\\s\\s");
                for(int k = 0; k < amtOfAns; k++) {
                    Output.print(split[k] + ")  " + (((Multiple_Choice) temp).getOption(split[k].charAt(0) - 65)) + "   ");
                }
            } else if (temp instanceof Short_Answer){
                Output.print("The correct answer is " + answer);
            } else if (temp instanceof Valid_Date){
                Output.print("The correct answer is " + answer);
            } else if (temp instanceof Matching) {
                Output.print("The Correct answers are: " + answer);
            }
            Output.print("\n\n");
        }

    }

    public String modifyAnswer(Question temp){
        String userResponse = "";
        String pattern = "";
        if(temp instanceof True_False) {
            Output.print("Enter Correct Choice in form of ([T]rue/[F]alse):\n");
            return(Character.toString(Boolean.toString(Verify.true_false()).toUpperCase().charAt(0)));
        } else if (temp instanceof Multiple_Choice) {
            int amtOfAns = ((Multiple_Choice) temp).getAmtAns();
            int amtOfQs = ((Multiple_Choice) temp).getAmtQs();
            pattern = "\\b[A-" + Character.toString((char) (65+(amtOfQs-1))) + "]$";
            Output.print("Enter "+ amtOfAns +" Correct Choice[s] in form \'A\' separated by new lines [ENTER]:\n");
            for(int i = 0; i < amtOfAns; i++){
                userResponse += Verify.regex(pattern).toUpperCase().strip() + "  ";
            }
            return userResponse;
        } else if (temp instanceof Short_Answer){
            int charLim = ((Short_Answer) temp).getCharLim();
            Output.print("Enter correct Answer (case insensitive) [Char Limit: " + charLim + "]:\n");
            String answer = Input.scan();
            if(answer.length() > charLim){
                answer = answer.substring(0,charLim);
                Output.print("Only first "+ charLim + " characters saved.");
            }
            return(answer.toLowerCase());
        } else if (temp instanceof Valid_Date){
            Output.print("Enter correct Answer in the following format: MM-DD-YYYY\n");
            return Verify.validDate();
        } else if (temp instanceof Matching) {
            int pairs = ((Matching) temp).getPairs();
            char a = 'A';
            pattern = "\\b[A-" + Character.toString((char) (65+(pairs-1))) + "]\\s[1-" + pairs + "]$";
            if(pairs == 10){
                pattern = "\\b[A-J]\\s[1-9]$|\\b[A-J]\\s10$";
            }
            Output.print("Enter  " + pairs + " correct Answer[s] in the following format: \'A 1\' [ENTER]\n");
            for(int i = 0; i < pairs; i++){
                userResponse += Verify.regex(pattern) + "  ";
            }
            return userResponse;
        }
        return "";
    }

}
