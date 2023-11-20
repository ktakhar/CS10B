package barPackage;

import fooPackage.FooClass;

class SubclassOutsideOfPackage extends FooClass {
    public static void foo() {
        int x;
        x = FooClass.aPublic;          // Ok
        x = FooClass.bProtected;       // Ok
        x = FooClass.cDefault;         // Not Ok
        x = FooClass.dPrivate;         // Not Ok
    }
}



class ClassOutsideOfPackage {
    public static void foo() {
        int x;
        x = FooClass.aPublic;          // Ok
        x = FooClass.bProtected;       // Not Ok
        x = FooClass.cDefault;         // Not Ok
        x = FooClass.dPrivate;         // Not Ok
    }
}
