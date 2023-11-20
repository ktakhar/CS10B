// File StackDemo.java
// Henry H. Leitner

// class StackDemo
// {
//   public static void main (String [] args)
//   {
//      Stack s = new Stack();

//      s.push ("foo");
//      s.push ("bar");
//      s.push (34);

//      System.out.println ( s.pop() );
//      System.out.println ( s.pop() );
//      System.out.println ( s.pop() );
//   }
// }

class StackDemo
{
  public static void main (String [] args)
  {
     java.util.Stack<Object> s = new java.util.Stack();

     s.push ("foo");
     s.push ("bar");
     s.push (34);

     System.out.println ( s.pop() );
     System.out.println ( s.pop() );
     System.out.println ( s.pop() );
  }
}

