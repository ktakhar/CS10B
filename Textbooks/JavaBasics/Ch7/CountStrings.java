public class CountStrings {
    public static void main(String[] args) {
        String[] array = {"hi", "how", "are", "you"};
        String target = "how";
        int occurrences = countStrings(array, target);
        System.out.println(occurrences);
    }
   
    public static int countStrings(String[] array, String target) {
        int count = 0;
        for (String element : array) {
            if (element.equals(target)) {
                count++;
            }
        }
        return count;
    }
}
