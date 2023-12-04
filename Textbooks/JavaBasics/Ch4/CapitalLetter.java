class CapitalLetter {
    public static void main(String[] args) {
        isCapital("Hello");
    }
    public static void isCapital(String phrase) {
        if (phrase.length() > 0 && Character.isUpperCase(phrase.charAt(0))) {
            System.out.println("capital");
        } else {
            System.out.println("not capital");
        }
    }
}