package com.example.myspeechtest;

public class Bot {

    public static String respondTo(String input) {

        String inputType = "AUSSAGE";
        String answer = input;
        if (input.startsWith("wann") || input.startsWith("wo") || input.startsWith("warum") || input.startsWith("wie")) {
            inputType = "FRAGE";
            // FRAGE -> ANTWORT (random)
            if (input.startsWith("wann")) {
                answer = "morgen";
            } else if (input.startsWith("wohin")) {
                answer = "zum Monde";
            } else if (input.startsWith("wo")) {
                answer = "in Timbuktu";
            } else if (input.startsWith("warum")) {
                answer = "och, nur so";
            } else if (input.startsWith("wie")) {
                answer = "irgendwie";
            }
        } else {
            if (answer.startsWith("ich")) {
                answer = answer.replaceAll("[^a-z]?ich", "Du");
            } else {
                answer = "Ich " + answer;
            }
            answer = answer.replaceAll("[Mm]ir", "DIR");
            answer = answer.replaceAll("[Mm]ich", "DICH");
            answer = answer.replaceAll("[Mm]ein", "DEIN");  // inkl. meine, meines, meiner, meinem, meinen
            answer = answer.replaceAll("[Dd]ir", "MIR");
            answer = answer.replaceAll("[Dd]ich", "MICH");
            answer = answer.replaceAll("[Dd]ein", "MEIN");  // inkl. deine, deines, deiner, deinem, deinen

            answer = answer.replaceAll("MIR", "mir");
            answer = answer.replaceAll("MICH", "mich");
            answer = answer.replaceAll("MEIN", "mein");
            answer = answer.replaceAll("DIR", "dir");
            answer = answer.replaceAll("DICH", "dich");
            answer = answer.replaceAll("DEIN", "dein");
        }

        return answer;
    }

    public static String colorize(String input) {
        String answer = input;
        answer = answer.replaceAll("Ich", "<font color='red'>Ich</font>");
        answer = answer.replaceAll("mich", "<font color='red'>mich</font>");
        answer = answer.replaceAll("mir", "<font color='red'>mir</font>");
        answer = answer.replaceAll("mein", "<font color='red'>mein</font>");
        answer = answer.replaceAll("Du", "<font color='red'>Du</font>");
        answer = answer.replaceAll("dir", "<font color='red'>dir</font>");
        answer = answer.replaceAll("dich", "<font color='red'>dich</font>");
        answer = answer.replaceAll("dein", "<font color='red'>dein</font>");
        return answer;
    }
}
