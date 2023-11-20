package fooPackage;

public class FooClass {
    static public    int aPublic;
    static protected int bProtected;
    static           int cDefault;
    static private   int dPrivate;
}



class ClassWithinSamePackage {
    public static void foo() {
        int x;
        x = FooClass.aPublic;          // Ok
        x = FooClass.bProtected;       // Ok
        x = FooClass.cDefault;         // Ok
        //x = FooClass.dPrivate;         // Not Ok
    }
}



class SubclassWithinSamePackage extends FooClass {
    public static void foo() {
        int x;
        x = FooClass.aPublic;          // Ok
        x = FooClass.bProtected;       // Ok
        x = FooClass.cDefault;         // Ok
        //x = FooClass.dPrivate;         // Not Ok
    }
}
