class CountE {
    public static void main(String[] args) {
        countE("Elementary");
    }
    public static void countE(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char currentChar = Character.toLowerCase(s.charAt(i));
            if (currentChar == 'e') {
                count++;
            }
        }
        System.out.println(count);
    }
}