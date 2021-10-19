// Richard Scott
// 10/26/2020
// Survey - Adding questions,
// saving and loading surveys,
// displaying and taking them

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.TreeMap;

public class Survey implements java.io.Serializable{
    private static final long serialVersionUID = 76664924001143L;
    String surveyName;
    List<Question> savedQuestions;

    public Survey(String surveyName){
        this.surveyName = surveyName;
        this.savedQuestions = new ArrayList<>();
    }

    public String getName(){
        return this.surveyName;
    }

    public int size(){
        return this.savedQuestions.size();
    }

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
        this.savedQuestions.add(temp);
    }

    public void modifyQuestion(int question){
        if(this.savedQuestions.size() < question){
            Output.print("\u001B[31mThere aren't "+ question +" questions in the survey at this time\u001B[0m\n");
        } else if(question < 1){
            Output.print("\u001B[31mIndex must be greater than 0\u001B[0m\n");
        } else {
            this.savedQuestions.get(question - 1).modify();
        }
    }

    public void displaySurvey(){
        for(int i = 0; i < this.savedQuestions.size(); i++){
            Output.print((i+1) + ") ");
            this.savedQuestions.get(i).display();
            Output.print("\n");
        }
    }

    public void save(Survey in, String outPath){
        Output.serialize(in, outPath);
    }

    public Survey load(String filePath){
        return Input.deserialize(filePath);
    }

    public UserResponse takeSurvey(){
        UserResponse temp = new UserResponse();
        for(int i = 0; i < this.savedQuestions.size(); i++){
            Output.print((i+1) + ") ");
            temp.addResponse(this.savedQuestions.get(i).take()); // Basically add a response to each question
            Output.print("\n");
        }
        return(temp);
    }

    public void tabulateResults(String[] filePaths, String headerPath){
        List<UserResponse> responses = new ArrayList<>();
        UserResponse temp;
        for(int i=0;i<filePaths.length;i++){
            temp = Input.deserialize(headerPath + File.separator + filePaths[i]);
            responses.add(temp);
        }
        int userResponseIndex = 0;
        Output.print("\n\n\n");
        for(int i=0;i<savedQuestions.size();i++){

            if(savedQuestions.get(i) instanceof True_False){
                Output.print("Question:\n");
                int c1=0; int c2=0;
                savedQuestions.get(i).display();
                Output.print("\nResponses:\n");
                for(int j=0;j<filePaths.length;j++){
                    Output.print(responses.get(j).getResponse(userResponseIndex)+ "\n");
                    if(responses.get(j).getResponse(userResponseIndex).equals("T")){
                        c1++;
                    } else {
                        c2++;
                    }
                }
                Output.print("\nTabulation:\n");
                Output.print("True: " + c1 + "\nFalse: " + c2);
                Output.print("\n\n\n");
                userResponseIndex++;
            }

            else if(savedQuestions.get(i) instanceof Multiple_Choice){
                Output.print("Question:\n");
                savedQuestions.get(i).display();
                int choices = ((Multiple_Choice) savedQuestions.get(i)).getAmtQs();
                int[] ans = new int[choices];

                Output.print("\nReplies:\n");
                for(int j=0;j<filePaths.length;j++){
                    Output.print(responses.get(j).getResponse(userResponseIndex)+ "\n");
                    String[] splitAns = responses.get(j).getResponse(userResponseIndex).split("\\s\\s");
                    for(int o = 0; o< splitAns.length;o++){
                        ans[splitAns[o].charAt(0)-'A']++;
                    }
                }
                Output.print("\nTabulation:\n");
                for(int k=0; k<choices; k++){
                    Output.print(Character.toString((char)('A'+k)) + ": " + ans[k] + "\n");
                }
                Output.print("\n\n\n");
                userResponseIndex++;
            }

            else if(savedQuestions.get(i) instanceof Short_Answer){
                String in;
                int count = 0;
                Hashtable<String, Integer> counter = new Hashtable<String, Integer>();
                Output.print("Question:\n");
                savedQuestions.get(i).display();
                Output.print("\nResponses:\n");
                for(int j=0;j<filePaths.length;j++){
                    in = responses.get(j).getResponse(userResponseIndex);
                    Output.print(in + "\n");
                    if(!counter.containsKey(in)){
                        counter.put(in, 1);
                    } else {
                        count = counter.get(in);
                        counter.put(in, ++count);
                    }
                }
                Output.print("\nTabulation:\n");
                for(String s:counter.keySet()) {
                    Output.print(s + " " + counter.get(s)+ "\n");
                }
                Output.print("\n\n\n");
                userResponseIndex++;
            }

            else if(savedQuestions.get(i) instanceof Essay){
                Output.print("Question:\n");
                savedQuestions.get(i).display();
                Output.print("\nResponses:\n");
                for(int j=0;j<filePaths.length;j++){
                    Output.print(responses.get(j).getResponse(userResponseIndex)+ "\n\n");
                }
                Output.print("\nTabulation:\n");
                for(int j=0;j<filePaths.length;j++){
                    Output.print(responses.get(j).getResponse(userResponseIndex)+ "\n\n");
                }
                Output.print("\n\n\n");
                userResponseIndex++;
            }

            else if(savedQuestions.get(i) instanceof Valid_Date){
                String in;
                int count = 0;
                Hashtable<String, Integer> counter = new Hashtable<String, Integer>();
                Output.print("Question:\n");
                savedQuestions.get(i).display();
                Output.print("\nResponses:\n");
                for(int j=0;j<filePaths.length;j++){
                    in = responses.get(j).getResponse(userResponseIndex);
                    Output.print(in + "\n");
                    if(!counter.containsKey(in)){
                        counter.put(in, 1);
                    } else {
                        count = counter.get(in);
                        counter.put(in, ++count);
                    }
                }
                Output.print("\nTabulation:\n");
                for(String s:counter.keySet()) {
                    Output.print(s + "\n" + counter.get(s)+ "\n\n");
                }
                Output.print("\n\n\n");
                userResponseIndex++;
            }

            else if(savedQuestions.get(i) instanceof Matching){
                String in;
                int count = 0;
                Hashtable<String, Integer> counter = new Hashtable<String, Integer>();
                Output.print("Question:\n");
                savedQuestions.get(i).display();
                Output.print("\nResponses:\n");
                for(int j=0;j<filePaths.length;j++){
                    in = responses.get(j).getResponse(userResponseIndex);
                    String[] splitAns = in.split("\\s\\s");
                    for(int o = 0; o< splitAns.length;o++){
                        Output.print(splitAns[o]+ "\n");
                    }
                    Output.print("\n");
                    if(!counter.containsKey(in)){
                        counter.put(in, 1);
                    } else {
                        count = counter.get(in);
                        counter.put(in, ++count);
                    }
                }
                Output.print("\nTabulation:\n");
                for(String s:counter.keySet()) {
                    Output.print(Integer.toString(counter.get(s)) + "\n");
                    String[] splitAns = s.split("\\s\\s");
                    for(int o = 0; o< splitAns.length;o++){
                        Output.print(splitAns[o]+ "\n");
                    }
                    Output.print("\n");
                }
                Output.print("\n\n\n");
                userResponseIndex++;

            }
        }
    }
}
