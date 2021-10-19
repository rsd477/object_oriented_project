public class Menu {
    public Menu(){
    }
    public int menu1(){
        int selected = 0;
        Output.print("1) Survey\n"
                + "2) Test\n");
        selected = Verify.enteredInt(2);
        return selected;
    }

    public int surveyMenu2(){
        int selected = 0;
        Output.print("1) Create a new Survey\n"
                + "2) Display an existing Survey\n"
                + "3) Load an existing Survey\n"
                + "4) Save the current Survey\n"
                + "5) Take the current Survey\n"
                + "6) Modify the current Survey\n"
                + "7) Tabulate a Survey\n"
                + "8) Return to the previous menu\n");
        selected = Verify.enteredInt(8);
        return selected;
    }

    public int testMenu2(){
        int selected = 0;
        Output.print("1) Create a new Test\n"
                + "2) Display an existing Test without correct answers\n"
                + "3) Display an existing Test with correct answers\n"
                + "4) Load an existing Test\n"
                + "5) Save the current Test\n"
                + "6) Take the current Test\n"
                + "7) Modify the current Test\n"
                + "8) Tabulate a Test\n"
                + "9) Grade a Test\n"
                + "10)Return to the previous menu\n");
        selected = Verify.enteredInt(10);
        return selected;
    }

    public int menu3(){
        int selected = 0;
        Output.print( "1) Add a new T/F question\n"
                + "2) Add a new multiple-choice question\n"
                + "3) Add a new short answer question\n"
                + "4) Add a new essay question\n"
                + "5) Add a new date question\n"
                + "6) Add a new matching question\n"
                + "7) Return to previous menu\n");

        selected = Verify.enteredInt(7);
        return selected;
    }
}
