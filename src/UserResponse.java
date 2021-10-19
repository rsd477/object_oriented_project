// Richard Scott
// 10/26/2020
// Saves a single surveys responses, expandable

import java.util.*;

public class UserResponse implements java.io.Serializable{
    private static final long serialVersionUID = 90243184204999L;
    List<String> answers;
    public UserResponse(){
        this.answers = new ArrayList<>();
    }

    public void addResponse(String input){
        this.answers.add(input);
    }

    public void setResponse(int index, String input){
        this.answers.set(index, input);
    }

    public String getResponse(int index){
        return this.answers.get(index);
    }

    public boolean equals(UserResponse template){
        return true;
    }
}
