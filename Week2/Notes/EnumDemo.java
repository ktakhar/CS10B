import java.util.Arrays;

/**
 * Program class to demonstrate enum, switch and "for each" loops.
 */

class EnumDemo {
    static enum DaysOfWeek { SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY }
    public static void main( String [] args ) {
        DaysOfWeek day;
        day = DaysOfWeek.TUESDAY;

        DaysOfWeek days[] = DaysOfWeek.values();
     // for (int i=0; i<days.length; i++) {
     //     DaysOfWeek member = days[i];
        for (DaysOfWeek member : days) {
            System.out.printf("member.name() is %s   member.ordinal() is %d\n", member.name(), member.ordinal() );
        }
        day = EnumDemo.DaysOfWeek.SUNDAY;
        Foo.bar( EnumDemo.DaysOfWeek.SUNDAY );
        Foo.bar( EnumDemo.DaysOfWeek.SATURDAY );
        Foo.bar( EnumDemo.DaysOfWeek.MONDAY );
        Foo.bar( EnumDemo.DaysOfWeek.TUESDAY );
        System.out.printf( "Arrays.toString( days )= %s\n\n", Arrays.toString( days ) );
    }
}

class Foo {
    static void bar( EnumDemo.DaysOfWeek day ) {
        switch( day ) {
            case SUNDAY, SATURDAY -> {
                System.out.println("SUNDAY OR SATURDAY!!");
            }
            case MONDAY -> {
                System.out.println("MONDAY!!");
            }
            default -> {
                System.out.println("NOT SATURDAY, NOT SUNDAY, NOT MONDAY!!");
            }
        }
    }
}
