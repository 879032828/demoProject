package com.example.datastructure.stack;

/**
 * 单词逆序
 */
public class Reverser {

    private String inputString;
    private StringBuilder outString;

    public Reverser() {
        outString = new StringBuilder();
    }

    public void putInputString(String inputString) {
        this.inputString = inputString;
    }

    public String doReverser() {
        Stack stack = new Stack(this.inputString.length());

        for (int i = 0; i < this.inputString.length(); i++) {
            try {
                stack.push(this.inputString.charAt(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (int j = 0; j < this.inputString.length(); j++) {
            try {
                outString.append((char) stack.pop());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return outString.toString();
    }
}
