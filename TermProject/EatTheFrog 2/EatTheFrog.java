public class EatTheFrog {
    public static void main(String[] args) {
        try {
            EatTheFrogUI ui = new EatTheFrogUI();
            ui.createAndShowGUI();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}