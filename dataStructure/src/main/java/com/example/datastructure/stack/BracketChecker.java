package com.example.datastructure.stack;

public class BracketChecker {

    private String input;

    public BracketChecker(String input) {
        this.input = input;
    }

    public void check() throws Exception {
        int stackSize = this.input.length();
        Stack stack = new Stack(stackSize);

        for (int i = 0; i < stackSize; i++) {
            char data = this.input.charAt(i);
            switch (data) {
                case '{':
                    stack.push(data);
                    break;
                case '[':
                    stack.push(data);
                    break;
                case '(':
                    stack.push(data);
                    break;
                case '}':
                case ']':
                case ')':
                    if (!stack.isEmpty()) {
                        char temp = (char) stack.pop();
                        if (data == '}' && temp != '{' ||
                                data == ']' && temp != '[' ||
                                data == ')' && temp != '(') {
                            System.out.println("Error : " + data + "at" + i);
                        }
                    } else {
                        System.out.println("Error : " + data + "at" + i);
                    }
                default:
            }
        }
        if (!stack.isEmpty()) {
            System.out.println("Error: missing right delemiter");
        }
    }
}
