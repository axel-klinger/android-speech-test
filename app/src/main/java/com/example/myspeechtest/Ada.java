package com.example.myspeechtest;

public class Ada implements Bot {

    public Ada() {

    }

    @Override
    public String respondTo(String input) {

        if (input.toLowerCase().equals("wer bist du")) {
            return "ich bin Ada";
        }

        if (input.toLowerCase().equals("was kannst du")) {
            return "ich kann rechnen";
        }

        String answer = "X";

        if (input.toLowerCase().startsWith("wie viel ist")) {
            String[] token = input.split(" ");
            if (token.length == 6) {
                Integer a = Integer.parseInt(token[3].trim());
                String operator = token[4];
                Integer b = Integer.parseInt(token[5].trim());

                switch(operator) {
                    case "+":
                        answer = String.valueOf(a + b);
                        break;
                    case "x":
                        answer = String.valueOf(a * b);
                        break;
                    case "/":
                        answer = String.valueOf(round((double)a / b, 3));
                        break;
                    case "-":
                        answer = String.valueOf(a - b);
                        break;
                }

                return answer;
            }
        }

        return "Keine Ahnung";
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
