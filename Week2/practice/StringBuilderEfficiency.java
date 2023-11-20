class StringBuilderEfficiency {
    public static void main (String [] args) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 500; i++);
        {
            result.append("some more stuff");
        }
        // System.out.println(result);
    }
}