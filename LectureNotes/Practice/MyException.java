// Slide 116

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class MyException {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner (new File ("x.dat"));
            String input = in.next();
            process(input);
            in.close();
        } catch (IOException e) {
            System.out.println("Cannot open file!");
        }
    }
    public static void process(Scanner in) {
        while (in.hasNext()) {
            String line = in.nextLine();
            System.out.println(line);
        }
    }
}
