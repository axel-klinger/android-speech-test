package com.example.myspeechtest;

public class ResearchBot implements Bot {

    public ResearchBot() {

    }

    @Override
    public String respondTo(String input) {

        if (input.toLowerCase().equals("who are you")) {
            return "i am a research bot";
        }

        if (input.toLowerCase().equals("what can you do")) {
            return "i can find authors for topics";
        }

        String answer = "X";

        if (input.toLowerCase().startsWith("who is working on")) {
            answer = "Axel Klinger";
        }

        return "i don't know";
    }

}
