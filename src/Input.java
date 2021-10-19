// Richard Scott
// 10/26/2020
// Input - scans from System.in and imports serialized files

import java.util.Scanner;
import java.io.*;

public class Input {
    public static String scan(){
        Scanner one = new Scanner(System.in);
        String temp = one.nextLine();
        return temp;
    }

    public static <T> T deserialize(String filePath){
        T temp = null;
        try {
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            temp = (T) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
            return null;
        }
        return temp;
    }

}
