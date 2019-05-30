package com.exercises.algorithms;

public class ReverseText {


    public ReverseText() {


        System.out.println(reverse("dupa dupa"));
    }

    private String reverse(String text) {
        String reversed = new String(text);

        for (int i = 0; i < text.length(); i++) {

            StringBuffer c = new StringBuffer(reversed);

            c.setCharAt(i, text.charAt(text.length() - i-1));

            reversed = c.toString();

        }

        return reversed;
    }

    public static void main(String[] args) {
        new ReverseText();
    }
}
