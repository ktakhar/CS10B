import java.util.*;
import javax.swing.*;
import java.awt.*;

class PracticeFinal
{
  public static void prob1 (ArrayList<Integer> list)
  {
    for (int i = list.size() - 1; i >= 0; i--)
    {
        if (i % 2 == 0) list.add (list.get(i));
        else list.add(0, list.get (i));

    }
    System.out.println (list);
  }

   public static void prob2()
   {
      JPanel center = new JPanel(new GridLayout(2, 2));
      center.add(new JButton ("Button4"));
      center.add(new JButton ("Button6"));
      center.add(new JButton ("Button5"));
      center.add(new JButton ("Button7"));
      JPanel north = new JPanel(new GridLayout(1, 3));
      north.add(new JButton("Button1"));
      north.add(new JButton("Button2"));
      north.add(new JButton("Button3"));
      JPanel south = new JPanel();
      south.add(new JLabel("Type stuff:"));
      south.add(new JTextField(10));

      JFrame frame = new JFrame ("Good thing I studied!");
      Container c = frame.getContentPane();
      frame.setSize(500, 500);
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
      frame.setLocation (100, 100);
      c.add(north, BorderLayout.NORTH);
      c.add(center, BorderLayout.CENTER);
      c.add(south, BorderLayout.SOUTH);
      frame.setVisible(true);
  }

  public static void prob3()
  {
    Prez [] p = { new FDR(), new Lincoln(), new Truman() };
    for (int i = 0; i < p.length; i++)
    {
      System.out.println (p[i]);
      p[i].democrat();
      p[i].republican();
      System.out.println();
    }
  }

  public static void prob4()
  {
    Stack<Integer> first = new Stack<>();
    Stack<Integer> second = new Stack<>();
    first.push(-12);
    first.push(0);
    first.push(1);
    first.push(8);
    first.push(8);
    first.push(8);
    if (isSorted(first)) System.out.println ("SORTED");
    else System.out.println ("NOT sorted!");
    if (Sorted(first)) System.out.println ("SORTED");
    else System.out.println ("NOT sorted!");
    second.push(-9);
    second.push(10);
    second.push(43);
    second.push (24);
    second.push(97);
    if (Sorted(second)) System.out.println ("SORTED");
    else System.out.println ("NOT sorted!");
    if (isSorted(second)) System.out.println ("SORTED");
    else System.out.println ("NOT sorted!");
 }

public static void s2q (Stack<Integer> s, Queue<Integer> q)
{
    while (!s.isEmpty()) q.add(s.pop());
       // Transfers the entire contents
       // of stack s to queue q
}

public static void q2s (Queue<Integer> q, Stack<Integer> s)
{
    while (!q.isEmpty()) s.push(q.remove());
      // Transfers the entire contents
      // of queue q to stack s
}

public static boolean Sorted(Stack<Integer> s)
{
    if (s.size() < 2) return true;

    Queue<Integer> q = new LinkedList<Integer>();
    boolean isSorted = true;
    int prev = s.pop();
    q.add(prev);
    while (!s.isEmpty()) {
        int x = s.pop();
        if (x > prev) isSorted = false;
        q.add(x);
        prev = x;
    }

     q2s(q, s);
     s2q(s, q);
     q2s(q, s);

    return isSorted;
}

public static boolean isSorted(Stack<Integer> s)
{
   if (s.size() < 2) return true;
   int curr_max = s.pop();
   int temp;
   while (!s.isEmpty())
   {
       temp = s.pop();
       if (temp > curr_max) return false;
       curr_max = temp;
   }
   return true;
 }

public static boolean prob4 (ListNode ln )
{
    if (ln != null)
    {
       ListNode current = ln;
       while (current.getLink() != null)
       {
          if (current.getData() % 2 ==
            current.getLink().getData() % 2) return false;
          else current = current.getLink();
        }
    }
    return true;
}
   public static void main (String [] args)
   {
      ArrayList<Integer> a = new ArrayList<>();
      a.add(10);  a.add(20);  a.add(30);
      prob1(a);
      prob2();
      prob3();
      prob4();
      // the following code is problem 6
      ListNode four = new ListNode (4, null);
      ListNode three = new ListNode (3, four);
      ListNode list2 = new ListNode (2, three);
      ListNode list = new ListNode (1, null);
      System.out.print("List initially = ");
      ListNode.print(list);
      System.out.print("List2 initially = ");
      ListNode.print(list2);

      list2.getLink().getLink().setLink(list);  // line 1
      list = list2.getLink();                   // line 2
      list.getLink().getLink().setLink(null);   // line 3 (doesn't do anything really)
      list.getLink().setData(17);               // line 4
      System.out.print("List ends up = ");
      ListNode.print(list);
      System.out.print("List2 ends up = ");
      ListNode.print(list2);
      System.out.println (prob4(list));
      System.out.println (prob4(list2));
int x = 63;
x ^=  -1  <<  (x/6);
System.out.printf ("%5x    \n", x);

   }
}
