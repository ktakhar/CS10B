import java.util.*;

class DemoInterface
{
  public static void main (String args [])
  {
      Movie3 hiddenFigures = new Movie3 ("Hidden Figures", 9,10, 10);
      Movie3 laLaLand = new Movie3 ("La La Land", 9, 10, 9);
      Movie3 manchesterByTheSea = new Movie3 ("Manchester by the Sea", 10, 8, 9);

      if (hiddenFigures.compareTo (laLaLand) >0 )
	          System.out.println("Hidden Figures wins!");
      else if (hiddenFigures.compareTo (laLaLand) == 0)
	          System.out.println("Movies equally good/bad!");
      else System.out.println("La La Land is the one!");
      
      Movie3 [] m = {hiddenFigures, laLaLand, manchesterByTheSea};
      Arrays.sort (m);
      for (Movie3 a : m) System.out.println (a);
  }
}
