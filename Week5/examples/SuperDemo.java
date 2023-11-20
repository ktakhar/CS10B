// SuperDemo.java
/** @author:  Henry H. Leitner
  * @version: Last modified on January 15, 2020
  * Uses class C to demonstrate the use of "this"
  * and "super" constructors
  */

class SuperDemo
{
   public static void main (String args[])
   {
       C anObjectOfTypeC = new C();
   }
}

/** 
 * prints 
In 1-arg constructor A 44
In 0-arg constructor A
In 1-arg constructor B 3
In 0-arg constructor C
*/