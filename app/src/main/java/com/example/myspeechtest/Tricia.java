package com.example.myspeechtest;

import android.text.TextUtils;

public class Tricia implements Bot {

    public Tricia () {

    }

    public String respondTo(String input) {

        if (input.toLowerCase().equals("wer bist du")) {
            return "ich bin Tricia";
        }

        if (input.toLowerCase().equals("was kannst du")) {
            return "ich kann sätze umdrehen";
        }

        String answer = "";

        if (input.startsWith("wann") || input.startsWith("wo") || input.startsWith("warum") || input.startsWith("wie")) {
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
            if (!answer.startsWith("ich")) {
                answer = "Ich " + answer;
            }
            answer = replaceWords(answer);
        }

        return answer;
    }

    private static String[] WORDS_TO_REPLACE_DE = {
            "bist","bin",
            "warst","war",
            "machst","mache",
            "mach","mache",
            "sagst","sage",
            "sag","sage",
            "erzählst","erzähle",
            "erzähl","erzähle",
            "gehst","gehe",
            "geh","gehe",
            "gibst","gebe",
            "gib","gebe",
            "du","ich",
            "dein","mein",
            "deine","meine",
            "deines","meines",
            "deiner","meiner",
            "deinem","meinem",
            "deinen","meinen",
            "du hast","ich habe",
            "du bist","ich bin",
            "dich", "mich",
            "dir", "mir"
    };

    private static String replaceWords(String input) {
        String output = input;
        String[] parts = output.split("\\s+");
        for (int i = 0; i < WORDS_TO_REPLACE_DE.length / 2; i++) {
            String first = WORDS_TO_REPLACE_DE[i * 2];
            String second = WORDS_TO_REPLACE_DE[i * 2 + 1];
            for (int j = 0; j < parts.length; ++j) {
                if (parts[j].equals(first)) {
                    parts[j] = second;
                } else if (parts[j].equals(second)) {
                    parts[j] = first;
                }
            }
        }
        output = TextUtils.join(" ", parts);
        return output;
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
