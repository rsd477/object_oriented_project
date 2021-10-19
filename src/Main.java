// Richard Scott
// 10/26/2020
// Main - Driver function to run menu and interface with survey

import java.io.File;

public class Main {

    static void surveyController(Menu menu){
        boolean quit = false;
        Survey load = null;
        File directory = null;
        String outPath = "";
        int choice1;
        int choice2;
        while(!quit){
            choice1 = menu.surveyMenu2();
            switch(choice1){
                case 1:
                    boolean addingQ = true;
                    Output.print("What do you want your new survey to be called?\n");
                    load = new Survey(Input.scan());
                    while(addingQ){
                        Output.print("\n\n");
                        choice2 = menu.menu3();
                        if(choice2 == 7) {
                            addingQ = false;
                        } else {
                            load.addQuestion(choice2);
                        }
                    }
                    break;
                case 2:
                    if(load == null){
                        Output.print("You must have a survey loaded in order to display it.\n");
                    } else {
                        Output.print("\n\n");
                        load.displaySurvey();
                    }
                    break;
                case 3:
                    File f = new File(System.getProperty("user.dir") + File.separator + "Surveys");
                    String[] pathNames = f.list();
                    if (pathNames != null) {
                        if(pathNames.length == 0){
                            Output.print("No Files found in Survey Directory");
                        } else {
                            Output.print("Please select a file to load:\n");
                            for (int i = 0; i < pathNames.length; i++){
                                Output.print((i+1) + ") " + pathNames[i] + "\n");
                            }
                            Survey temp = new Survey("");
                            String getPath = "Surveys" + File.separator + pathNames[Verify.enteredInt(pathNames.length) - 1];
                            load = temp.load(getPath);
                        }
                    } else {
                        Output.print("\u001B[31mNo Files found in Survey Directory, make a Survey\u001B[0m\n");
                    }
                    break;
                case 4:
                    if(load != null){
                        directory = new File("Surveys");
                        if (! directory.exists()){
                            directory.mkdirs();
                        }
                        outPath = "Surveys" + File.separator + load.getName();
                        load.save(load, outPath);
                        Output.print("Your survey was saved as: \n" + outPath + "\n");
                    } else {
                        Output.print("Must Have a survey loaded to save it.\n");
                    }
                    break;
                case 5:
                    if(load == null){
                        Output.print("You must have a survey loaded in order to take it.\n");
                    } else {
                        UserResponse current;
                        directory = new File(System.getProperty("user.dir") + File.separator +"Survey_Responses" + File.separator + load.getName());
                        if (! directory.exists()){
                            directory.mkdirs();
                        }
                        f = new File(System.getProperty("user.dir") + File.separator + "Survey_Responses"+ File.separator + load.getName());
                        pathNames = f.list();
                        int o;
                        if(pathNames == null){o=1;}
                        else{o=pathNames.length+1;}
                        outPath = "Survey_Responses" + File.separator + load.getName() + File.separator + load.getName() + " - Response " + o;
                        current = load.takeSurvey();
                        Output.serializeResponse(current,outPath);
                    }
                    break;
                case 6:
                    if(load == null){
                        Output.print("You must have a survey loaded in order to modify it.\n");
                    } else {
                        Output.print("What question do you wish to modify (Ex: 1)?\n");
                        load.modifyQuestion(Verify.enteredInt(load.size()));
                    }
                    break;
                case 7:
                    if(load == null){
                        Output.print("You must have a survey loaded in order to tabulate it.\n");
                    } else {
                        File r = new File(System.getProperty("user.dir") + File.separator + "Survey_Responses" + File.separator + load.getName());
                        pathNames = r.list();
                        if (pathNames != null) {
                            if(pathNames.length == 0){
                                Output.print("No Files found in Survey_Responses Directory");
                            } else {

                                load.tabulateResults(pathNames, (System.getProperty("user.dir") + File.separator + "Survey_Responses"+ File.separator + load.getName()));
                            }
                        } else {
                            Output.print("\u001B[31mNo Files found in Survey_Responses Directory, take a Survey\u001B[0m\n");
                        }
                    }
                    break;
                case 8:
                    quit = true;
                    break;
                default:
                    Output.print("Main:68 THIS SHOULDN'T HAPPEN"); // if this is thrown, retire
            }
            Output.print("\n");
        }
    }

    static void testController(Menu menu){
        boolean quit = false;
        Test load = null;
        File directory = null;
        String outPath = "";
        int choice1;
        int choice2;
        while(!quit){
            choice1 = menu.testMenu2();
            switch(choice1){
                case 1:
                    boolean addingQ = true;
                    Output.print("What do you want your new test to be called?\n");
                    load = new Test(Input.scan());
                    while(addingQ){
                        Output.print("\n\n");
                        choice2 = menu.menu3();
                        if(choice2 == 7) {
                            addingQ = false;
                        } else {
                            load.addQuestion(choice2);
                        }
                    }
                    break;
                case 2:
                    if(load == null){
                        Output.print("You must have a test loaded in order to display it.\n");
                    } else {
                        Output.print("\n\n");
                        load.displaySurvey();
                    }
                    break;
                case 3:
                    if(load == null){
                        Output.print("You must have a test loaded in order to display it.\n");
                    } else {
                        Output.print("\n\n");
                        load.displayTestWithAns();
                    }
                    break;
                case 4:
                    File f = new File(System.getProperty("user.dir") + File.separator + "Tests");
                    String[] pathNames = f.list();
                    if (pathNames != null) {
                        if(pathNames.length == 0){
                            Output.print("No Files found in Test Directory");
                        } else {
                            Output.print("Please select a file to load:\n");
                            for (int i = 0; i < pathNames.length; i++){
                                Output.print((i+1) + ") " + pathNames[i] + "\n");
                            }
                            Test temp = new Test("");
                            String getPath = "Tests" + File.separator + pathNames[Verify.enteredInt(pathNames.length) - 1];
                            load = temp.load(getPath);
                        }
                    } else {
                        Output.print("\u001B[31mNo Files found in Test Directory, make/save a Test\u001B[0m\n");
                    }
                    break;
                case 5:
                    if(load != null){
                        directory = new File("Tests");
                        if (! directory.exists()){
                            directory.mkdirs();
                        }
                        outPath = "Tests" + File.separator + load.getName();
                        load.save(load, outPath);
                        Output.print("Your test was saved as: \n" + outPath + "\n");
                    } else {
                        Output.print("\u001B[31mLoad/Make a Test before saving\u001B[0m\n");
                    }
                    break;
                case 6:
                    if(load == null){
                        Output.print("You must have a test loaded in order to take it.\n");
                    } else {
                        UserResponse current;
                        directory = new File(System.getProperty("user.dir") + File.separator + "Test_Responses" + File.separator + load.getName());
                        if (!directory.exists()){
                            directory.mkdirs();
                        }
                        f = new File(System.getProperty("user.dir") + File.separator + "Test_Responses"+ File.separator + load.getName());
                        pathNames = f.list();
                        int o;
                        if(pathNames == null){o=1;}
                        else{o=pathNames.length+1;}
                        outPath = "Test_Responses" + File.separator + load.getName() + File.separator + load.getName() + " - Response " + o;
                        current = load.takeSurvey();
                        Output.serializeResponse(current,outPath);
                    }
                    break;
                case 7:
                    if(load == null){
                        Output.print("You must have a test loaded in order to modify it.\n");
                    } else {
                        Output.print("What question do you wish to modify (Ex: 1)?\n");
                        load.modifyQuestion(Verify.enteredInt(load.size()));
                    }
                    break;
                case 8:
                    if(load == null){
                        Output.print("You must have a test loaded in order to tabulate it.\n");
                    } else {
                        File r = new File(System.getProperty("user.dir") + File.separator + "Test_Responses" + File.separator + load.getName());
                        pathNames = r.list();
                        if (pathNames != null) {
                            if(pathNames.length == 0){
                                Output.print("No Files found in Test_Responses Directory");
                            } else {
                                load.tabulateResults(pathNames, (System.getProperty("user.dir") + File.separator + "Test_Responses" + File.separator + load.getName()));
                            }
                        } else {
                            Output.print("\u001B[31mNo Files found in Test_Responses Directory, take a Test\u001B[0m\n");
                        }
                    }
                    break;
                case 9:
                    if(load == null){
                        Output.print("You must have a test loaded in order to grade it.\n");
                    } else {
                        File r = new File(System.getProperty("user.dir") + File.separator + "Test_Responses" + File.separator + load.getName());
                        pathNames = r.list();
                        if(pathNames != null){
                            Output.print("Select an existing response set:\n");
                            for (int i = 0; i < pathNames.length; i++){
                                Output.print("   " + (i+1) + ") " + pathNames[i] + "\n");
                            }
                            UserResponse toGrade = Input.deserialize("Test_Responses" + File.separator + load.getName() + File.separator + pathNames[Verify.enteredInt(pathNames.length) - 1]);
                            load.grade(toGrade);
                        } else {
                            Output.print("\u001B[31mNo Files found in Test_Responses Directory, take a Test\u001B[0m\n");
                        }
                    }
                    break;
                case 10:
                    quit = true;
                    break;
                default:
                    Output.print("Main:68 THIS SHOULDN'T HAPPEN"); // if this is thrown, retire
            }
            Output.print("\n");
        }
    }

    public static void main(String[] args){
        Menu menu = new Menu();
        boolean run = true;
        int choice;
        while(run){
            choice = menu.menu1();
            switch(choice){
                case 1:
                    surveyController(menu);
                    break;
                case 2:
                    testController(menu);
                    break;
                default:
                    System.exit(-1);
                    break;
            }
        }
    }
}
