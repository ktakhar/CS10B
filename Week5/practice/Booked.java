public class Booked {
   private String day;
   private int time;
   private String name;

   public Booked(String day, int time, String name) {
        this.day = day;
        this.time = time;
        this.name = name;
   }

    public String getDay() {
          return day;
    }

    public int getTime() {
          return time;
    }

    public String getName() {
          return name;
    }

    public boolean isBooked() {
          if (day == day && time == time) {
              return true;
          } else {
              return false;
          }
    }
}

class Exercise extends Booked {
    public Exercise(String day, int time, String name) {
        super(day, time, name);
    }
}