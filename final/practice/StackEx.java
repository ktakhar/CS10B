import java.util.*;

class StackEx {
    public static void main(String [] args) {
        String [] colors = {"Red", "Orange", "Yellow", "Green", "Blue", "Purple"};
        Stack <String> colorStack = new Stack<>();

        for (String color : colors) {
            colorStack.push(color);
        }
        System.out.println("Stack: " + colorStack);
        System.out.println("Size: " + colorStack.size());
        System.out.println("Peek: " + colorStack.peek());
        System.out.println("Pop: " + colorStack.pop());
    
    }
}
// push() = add to top
// pop() = remove from top
// peek() = purple
// pop() = purple 
// isEmpty() = false;
// size() = 6;