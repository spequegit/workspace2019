package com.exercises.algorithms;

public class ExpressionX {


    public ExpressionX() {

        int x = 5;
        int n = 3;

        System.out.println(doExpression(x  ,n));
        System.out.println(oblicz(x,n));

    }

    // x * (x2 + 1) * (x3 + 2) * (x4 + 3) * ... * (xn + n - 1)

    public int doExpression(int x, int n) {

        int result = 1;

        for (int i = 1; i <= n; i++) {
            result = ((int)Math.pow(x, i) + i - 1) * result;

        }


        return result;


    }
    public static int oblicz(int x, int n) {

        if (x < 0 || n < 0 || (n == 0 && x == 0)) {
            //
        } else if (n == 0) {
            return 1;
        } else if (x == 0) {
            return 0;
        }

        int result = ((int) Math.pow(x, n) + n - 1) * oblicz(x, n - 1);
        return result;

    }



    public static void main(String[] args) {
        new ExpressionX();
    }
}
