public class Loop {

    static public void main(String[] args) {
       forLoop();
       doWhile();
       nestedLoop();
       multitable();
    }

    static void forLoop() {
        int n = 5;
        for (int i = 0; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
             System.out.println();
        }
       
    }

    static void doWhile() {
        int count = 1;
        do {
            System.out.println("Count: " + count);
            count++;
        } while (count <= 5);
    }

    static void nestedLoop() {
        for (int i = 0; i <= 3; i++) {
            for (int j = 1; j <= 5; j++) {
                System.out.print(i + j);
            }
            System.out.println();
        } 
    }

    static void multitable() {
        for (int i = 1; i <=5; i++) {
            for (int j = 1; j <= 10; j++) {
                System.out.print((i * j) + " ");
            } 
            System.out.println();
        } 
    }
}