package com.example.myspeechtest;

import android.text.TextUtils;

public class Eliza {


    private static final String[] CONVERSATION_KEYWORDS_DE = {
            "KANNST DU", // 1 -> 1-4
            "KANN ICH",  // 2 -> 4-6
            "DU BIST",   // 3 -> 6-10
            "DUBIST",    // 4 -> 6-10
            "I DONT",
            "ICH FÜHLE", "WARUM NICHT DU", "WARUM KANN ICH NICHT",
            "BIST DU", "ICH KANN NICHT", "ICH BIN", " ICHBIN ", "DU", "ICH WILL", "WAS", "WIE", "WER", "WO",
            "WANN", "WARUM", "NAME", "GRUND", "ENTSCHULDIGE", "TRAUM", "HALLO", "HI", "VIELLEICHT", "NEIN", "DEIN",
            "IMMER", "DENKE", "GLEICH", "JA", "FREUND", "COMPUTER", "NOKEYFOUND" };

    // der Vorteil dieses Ansatzes ist, dass man keine Zwischenlösung braucht
    private static String[] WORDS_TO_REPLACE_DE = {
            "BIST","BIN",
            "WAREN","WAR",
            "DU","ICH",
            "DEIN","MEIN",
            "ICH HABE","DU HAST",
            "ICH BIN","DU BIST",
            "DIR", "MIR"};

    private static String[] QUESTIONS = {
            "GLAUBST DU NICHT, DASS ICH*KANN.", // 1
            "VIELLEICHT WÄRST DU GERN IN DER LAGE ZU*.", // 2
            "DU MÖCHTEST, DASS ICH IN DER LAGE BIN ZU*", // 3
            "VIELLEICHT WILLST DU NICHT*", // 4
            "MÖCHTEST DU IN DER LAGE SEIN ZU*", // 5
            "WARUM DENKST DU ICH BIN*", // 6
            "FREUT ES DICH ZU GLAUBEN DASS ICH*BIN", // 7
            "VIELLEICHT MÖCHTEST DU*SEIN", // 8
            "WÜNSCHT DU DIR MANCHMAL DU WÄRST*", // 9
            "DON'T YOU REALLY*", "WHY DON'T YOU*", // 10
            "WÄRST DU GERN IN DER LAGE ZU*", "HAST DU EIN PROBLEM DAMIT?",
            "ERZÄHL MIR MEHR ÜBER SOLCHE GEFÜHLE*", "FÜHLST DU OFT*",
            "GEFÄLLT ES DIR ZU FÜHLEN*", "GLAUBST DU WIRKLICH, DASS ICH ES NICHT*",
            "PERHAPS IN TIME I WILL*", "MÖCHTEST DU DAS ICH*",
            "DENKST DU DU SOLLTEST IN DER LAGE SEIN ZU*", "WARUM KANNST DU NICHT*",
            "WAS LÄSsT DICH ZWEIFELN OB ODER OB NICHT ICH*BIN",
            "WÄRE DIR LIEBER ICH WÄRE NICHT*", "VIELLEICHT BIN ICH IN DEINER FANTASIE*",
            "WOHER WEISST DU DASS DU NICHT*KANNST", "HAST DU ES PROBIERT?", "VIELLEICHT KANNST DU JETZT*",
            "BIST DU ZU MIR GEKOMMEN WEIL DU*BIST", "WIE LANGE WARST DU*",
            "GLAUBST DU ES IST NORMAL*ZU SEIN", "GENIESST DU ES*ZU SEIN",
            "ICH HABE MEHR INTERESSE, MICH MIT DIR ZU UNTERHALTEN.", "OH, ICH*",
            "BIST DU WIRKLICH SO INTERESSIERT AN JEMANDEM WIE MIR?",
            "WAS WÜRDE ES DIR BEDEUTEN*ZU BEKOMMEN", "WARUM MÖCHTEST DU*",
            "ICH SCHÄTZE DU WIRST BALD*BEKOMMEN", "WAS WENN DU NIEMALS*BEKOMMST", "ICH MÖCHTE MANCHMAL AUCH*",
            "WARUM FRAGST DU?", "INTERESSIERT DICH DIE FRAGE?",
            "WELCHE ANTWORT WÜRDE DIR AM MEISTEN GEFALLEN?", "WAS DENKST DU?",
            "HAST DU OFT SOLCHE GEDANKEN?",
            "WAS WILLST DU WIRKLICH WISSEN?", "HAST DU SCHON JEMAND ANDEREN GEFRAGT?",
            "HAST DU DICH DAS SCHON MAL GEFRAGT?",
            "AN WAS DENKST DU NOCH, WENN DU DAS FRAGST?", "WIR BEHALTEN DAS FÜR UNS.",
            "NAMEN SPIELEN KEINE ROLLE - FAHRE FORT.", "IST DAS DER WIRKLICHE GRUND?",
            "FÄLLT DIR KEIN ANDERER GRUND EIN?",
            "ERKLLÄRT DER GRUND NOCH ETWAS ANDERES?", "WELCHE GRÜNDE KÖNNTE ES NOCH GEBEN?",
            "BITTE KEINE ENTSCHULDIGUNG.", "ENTSCHULDIGUNGEN SIND NICHT NÖTIG.",
            "WAS FÜHLST DU WENN DU DICH ENTSCHULDIGST?", "KEIN GRUND DEFENSIV ZU SEIN!",
            "WAS SAGT DIR DIESER TRAUM?", "TRÄUMST DU OFT?",
            "WELCHE PERSONEN ERSCHEINEN DIR IM TRAUM?", "HAST DU ANGENEHME TRÄUME?",
            "WIE MACHST DU DAS ... GIB BITTE DEIN PROBLEM AN.", "DU SCHEINST NICHT GANZ SICHER ZU SEIN.",
            "WOHER DIE UNSICHERHEIT?", "LASS UNS VERSUCHEN, ES POSITIV ZU HALTEN.", "DU BIST DIR NICHT SICHER?",
            "WEIST DU ES NICHT?", "IST DAS EIN DEFINITIVES NEIN ODER KÖNNTEST DU DEINE MEINUNG NOCH ÄNDERN?",
            "ICH SPÜRE EINE NEGATIVITÄT.", "WARUM NICHT?", "BIST DU SICHER?", "WARUM NICHT?",
            "WARUM BIST DU BESORGT ÜBER MEINE*", "WAS IST MIT DEINEM EIGENEN*",
            "KANNST DU DIR NICHT EIN KONKRETES BEISPIEL VORSTELLEN?", "WANN?", "WORAN DENKST DU?",
            "WIRKLICH. IMMER?", "DENKST DU DAS WIRKLICH?", "ABER DU BIST DIR NICHT SICHER.",
            "GLAUBE AN DICH.", "IN WELCHER WEISE?", "WELCHE ÄHNLICHKEIT SIEHST DU?",
            "WAS SAGT DIR DIE ÄHNLICHKEIT?",
            "WELCHE ANDEREN VERBINDUNGEN SIEHST DU NOCH?", "KÖNNTE ES DA WIRKLICH EINE VERBINDUNG GEBEN?",
            "WIE?", "DU SCHEINST ZIEMLICH POSITIV ZU SEIN.", "BIST DU SICHER?", "VERSTEHE.", "ICH VERSTEHE.",
            "ERZÄHLE MIR VON DEINEN FREUNDEN.", "MACHST DU DIR SORGEN UM DEINE FREUNDE?",
            "HABEN DEINE FREUNDE DIR JEMALS EINE HARTE ZEIT BEREITET?", "WAS GEFÄLLT DIR AN DEINEN FREUNDEN?",
            "LIEBST DU DEINE FREUNDE?", "VIELLEICHT MACHT DIR DEINE LIEBE ZU FREUNDEN SORGEN.",
            "BEGEISTERN DICH COMPUTER?", "SPRICHST DU ÜBER MICH IM BESONDEREN?",
            "WIE GEFÄLLT DIE DEINE UHR?", "WARUM ERWÄHNST DU COMPUTER?",
            "FINDEST DU MASCHINEN GENAUS SO SPANNEND WIE ICH?",
            "DENKST DU NICHT, COMPUTER KÖNNTEN MENSCHEN HELFEN?",
            "WAS FASZINIERT DICH AN MSCHINEN AM MEISTEN?",
            "HALLO, WIE KANN ICH DIR HELFEN?",
            "WAS BEDEUTET DAS FÜR DICH?", "ICH VERSTEHE.",
            "ICH BIN MIR NICHT SICHER, OB ICH DICH RICHTIG VERSTEHE.", "KOMM UND KLÄR DEINE GEDANKEN AUF.",
            "KANNST DU DAS NÄHER ERLÄUTERN?", "DAS IST WIRKLICH INTERESSANT."
    };

    private static char[] CONVERSATION_TO_RESPONSES_MAP = {
            1,3,    // 1-4
            4,2,    // 4-6
            6,4,    // 6-10
            6,4,
            10,4,
            14,3,17,3,20,2,22,3,25,3,
            28,4,28,4,32,3,35,5,40,9,40,9,40,9,40,9,40,9,40,9,
            49,2,51,4,55,4,59,4,63,1,63,1,64,5,69,5,74,2,76,4,
            80,3,83,7,90,3,93,6,99,7,106,6};

    private int[] responseStarts = new int[36];
    private int[] responseCurrentIndices = new int[36];
    private int[] responseEnds = new int[36];
    private String previousInput = null;

    public Eliza() {
        // println "   DEBUG CONVERSATION_TO_RESPONSES_MAP: " + CONVERSATION_TO_RESPONSES_MAP
        for (int i = 0; i < CONVERSATION_TO_RESPONSES_MAP.length / 2; i++) {
            responseStarts[i] = CONVERSATION_TO_RESPONSES_MAP[2 * i];
            responseCurrentIndices[i] = CONVERSATION_TO_RESPONSES_MAP[2 * i];
            responseEnds[i] = responseStarts[i] + CONVERSATION_TO_RESPONSES_MAP[2 * i  + 1];
        }
    }

    public String respondTo(String input) {
        if (null == input) {
            input = "";
        }
        String result = "";

        input = " " + input.toUpperCase().replace("\'", "") + " ";

        if (previousInput != null && input.equals(previousInput)) {
            return "HAST DU DAS NICHT GERADE GESAGT?\n";
        }
        previousInput = input;

        int keywordIndex = 0;
        for (; keywordIndex < CONVERSATION_KEYWORDS_DE.length; ++keywordIndex) {
            int index = input.indexOf(CONVERSATION_KEYWORDS_DE[keywordIndex]);
            if (index != -1) {
                break;
            }
        }

        String afterKeyword = "";
        if (keywordIndex == CONVERSATION_KEYWORDS_DE.length) {
            keywordIndex = 35;
        } else {
            int index = input.indexOf(CONVERSATION_KEYWORDS_DE[keywordIndex]);
            afterKeyword = input.substring(index + CONVERSATION_KEYWORDS_DE[keywordIndex].length());
            String[] parts = afterKeyword.split("\\s+");
            // println "   DEBUG afterKeyword: " + afterKeyword
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
            // println "   DEBUG afterKeyword: " + afterKeyword
            afterKeyword = TextUtils.join(" ", parts);
        }
        //println "   kwIdx: " + keywordIndex + ", resCurrIdx: " + responseCurrentIndices[keywordIndex]
        String question = QUESTIONS[responseCurrentIndices[keywordIndex] - 1];
        // println "   DEBUG question: " + question
        responseCurrentIndices[keywordIndex] = responseCurrentIndices[keywordIndex] + 1;
        if (responseCurrentIndices[keywordIndex] > responseEnds[keywordIndex]) {
            responseCurrentIndices[keywordIndex] = responseStarts[keywordIndex];
        }
        result += question;
        if (result.contains("*")) {
            result = result.replaceAll("\\*", " " + afterKeyword.trim() + " ").trim();
        }

        return result;
    }

}
