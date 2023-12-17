class RevStrByChar {
    public static void main (String[] args) {
       String reversedStr = reverse("Hello");
       System.out.println(reversedStr);
    }
    public static String reverse(String phrase) {
        String result = "";
        for (int i = 0; i < phrase.length(); i++) {
            result = phrase.charAt(i) + result;
        }
        return result;
    }
}