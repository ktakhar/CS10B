class TestingTwoObjectsForEquality {
    public static void main(String [] args) {
        String str1, str2;
        str1 = "abcd".toUpperCase(); // str1's data is "ABCD"
        str2 = "ABCD";               // str2's data is "ABCD"

        System.out.printf( "str1=\"%s\"\n", str1 );
        System.out.printf( "str2=\"%s\"\n", str2 );

        // When you create a variable that's an object, the variable points to the
        // area of memory (aka the "blob") that contains the object's data.
        // So the variable is actually the address of the object's blob.

        // Use == to test if two objects point to the same blob.
        // Use .equals to test if two objects contain the same data
        System.out.printf("str1==str2 is %b\n", str1==str2 );
        System.out.printf("str1.equals( str2 ) is %b\n", str1.equals( str2 ) );
    }
}
