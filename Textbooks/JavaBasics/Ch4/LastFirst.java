class LastFirst {
    public static void main(String[] args) {
        lastFirst("Marla Singer");
    }
    public static void lastFirst(String fullName) {
        String [] names = fullName.split(" ");
        char firstInitial = names[0].charAt(0);
        String formattedName = names[1] + ", " + firstInitial + ".";
        System.out.println(formattedName);
    }
}