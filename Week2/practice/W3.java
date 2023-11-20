public class W3 {
    public static void main(String[] args) {
        int x = 3;
        int y = 4;

        if (x < y) {
            System.out.println("X is less than Y");
        } else {
            System.out.println("Y is less than X");
        }

        // Create an instance of the Time class and invoke its main method
        Time timeProgram = new Time();
        timeProgram.main(new String[]{});

        // Create an instance of the Boolean class and invoke its main method
        Boolean booleanProgram = new Boolean();
        booleanProgram.main(new String[]{});

        // Create an instance of the IsMax class and invoke its main method
        Mathy mathyProgram = new Mathy();
        mathyProgram.main(new String[]{});

        // Create an instance of the Switchy class and invoke its main method
        Switchy switchyProgram = new Switchy();
        switchyProgram.main(new String[]{});

        // Create an instance of the Loops class and invoke its main method
        Loops loopsProgram = new Loops();
        loopsProgram.main(new String[]{});

        // Create an instance of the Arrays class and invoke its main method
        Arrays arraysProgram = new Arrays();
        arraysProgram.main(new String[]{});

        // Create an instance of the ForEach class and invoke its main method
        ForEach forEachProgram = new ForEach();
        forEachProgram.main(new String[]{});
    }
}

class Time {
    public static void main(String[] args) {
        int time = 20;
        String result = (time < 18) ? "Good day." : "Good evening.";
        System.out.println(result);
    }
}

class Boolean {
    public static void main(String[] args) {
        int x = 10;
        int y = 9;
        System.out.println(x > y); // returns true, because 10 is higher than 9 
    }
}

class Mathy {
    public static void main(String [] args) {
        System.out.println(Math.max(5, 10));
        System.out.println(Math.min(5, 10));
        System.out.println(Math.sqrt(64));
        System.out.println(Math.abs(-4.7));
        System.out.println(Math.random() * 101);
    }
}

class Switchy {
    public static void main(String [] arg) {
        int day = 4;
        switch (day) {
            case 1:
                System.out.println("Monday");
                break;
            case 2: 
                System.out.println("Tuesday");
                break;
            case 3: 
                System.out.println("Wednesday");
                break;
            case 4: 
                System.out.println("Thursday");
                break;
            case 5: 
                System.out.println("Friday");
                break;
            case 6: 
                System.out.println("Saturday");
                break;
            case 7: 
                System.out.println("Sunday");
                break;
            default: 
                System.out.println("Looking forward to the Weekend");
        }
    }
}

class Loops {
    public static void main(String [] args) {
        int i = 0;
        while (i < 6) {
            System.out.print(i);
            i++;
        }

        System.out.println();

        int j = 1;
        do {
            System.out.print(j);
            j++;   
        }
        while (j < 6);

        System.out.println();
    }
}

class ForEach {
    public static void main(String [] args) {
        String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};
        for (String i : cars) {
            System.out.print(i);
        }
        System.out.println();
    }
}

class Arrays {
    public static void main(String [] args) {
        String[] cars = {"Volvo ", "BMW ", "Ford ", "Mazda "};
        cars[0] = "Opel ";
        System.out.print(cars[0]);
        System.out.print(cars.length);

        System.out.println();
        
        for (int i = 0; i < cars.length; i++) {
            System.out.print(cars[i]);
        }
    }
}