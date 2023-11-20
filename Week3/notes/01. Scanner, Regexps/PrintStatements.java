class PrintStatements {
    public static void main( String [] args ) {
        // Here are different syntactically-correct System.out.printx method calls.
        // All of these statements are matched with the regexp:    System\.out\.print.*\(.*\) *;
        // All of these lines      are matched with the regexp: ^ *System\.out\.print.*\(.*\) *;.*$
        String foo = "Some String";
        System.out.printf( "%s\n", foo ) ;
        System.out.println( foo )    ; // Some comment
        System.out.println(foo);// Another comment
        System.out.println   ( foo );
        System.out.println (foo);
        System.out.print (foo);
        System.out.println();
    }
}
