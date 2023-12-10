public class Prob3 {

     public abstract class Prez { 
        public void republican() {
            System.out.println("Republican Prez");
        }
        public void democrat() {
            System.out.println("Democrat Prez");
        }
        public String toString() {
            return "I am the President!";
        } 
    }

    public class Lincoln extends Prez { 
        public void republican() {
            super.republican();
            System.out.println("Lincoln-R");
        }
    }

    public class FDR extends Prez {
        public String toString() {
            return "FDR";
        }
        public void democrat() {
            System.out.println("FDR - D");
            super.democrat();
        }
    }
   
    public class Truman extends FDR {
        public void democrat() {
            System.out.println("Truman - D");
        }
    }
  
    public static void main(String[] args) {
        Prob3 prob3 = new Prob3();

        Prez [] p = { prob3.new FDR(), prob3.new Lincoln(), prob3.new Truman() };
        
        for (int i = 0; i < p.length; i++) {
            System.out.println(p[i]);
            p[i].democrat();
            p[i].republican();
            System.out.println();
        }
    } 
}