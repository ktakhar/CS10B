import java.util.Scanner;

class RAWBD {
    public static void main(String[] args) {
       
        Scanner s = new Scanner(System.in);
        System.out.print("Input positive DECIMAL integer: ");
        
        int dec = s.nextInt();

        System.out.println("DECIMAL: " + dec);
        System.out.println("BINARY: " + Integer.toBinaryString(dec));
        System.out.println("OCTAL: " + Integer.toOctalString(dec));

        System.out.println();

        System.out.print("Input positive BINARY integer: ");
        
        String bin = s.next();
       
        System.out.println("BINARY: " + bin);
        System.out.println("DECIMAL: " + Integer.parseInt(bin, 2));
        System.out.println("OCTAL: " + Integer.toOctalString(Integer.parseInt(bin, 2)));
        
        System.out.println();
        System.out.println("That's all folks!");
        System.out.println();

        s.close();
    }
}