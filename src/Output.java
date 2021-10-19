// Richard Scott
// 10/26/2020
// Output - outputs strings and saves responses and surveys to files

import java.io.*;

public class Output{
    public static void print(String output){
        System.out.print(output);
    }

    public static void serialize(Survey in, String outPath){
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(outPath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(in);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void serializeResponse(UserResponse in, String outPath){
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(outPath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(in);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
