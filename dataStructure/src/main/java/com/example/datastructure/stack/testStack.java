package com.example.datastructure.stack;

public class testStack {


    public static void main(String[] args) {
//        Stack stack = new Stack(100);
//        try {
//            stack.push(9);
//            stack.push(1);
//            stack.push(5);
//            stack.push(8);
//            stack.push(2);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        while (!stack.isEmpty()) {
//            try {
//                System.out.println(stack.pop());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

//        Reverser reverser = new Reverser();
//        reverser.putInputString("abcdefg");
//        System.out.println(reverser.doReverser());

//        BracketChecker bracketChecker = new BracketChecker("a{b(c)d}e");
//        try {
//            bracketChecker.check();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        Postfix postfix = new Postfix("5*5+6-4+1");
        postfix.transformToPostfix();
        postfix.display();
        System.out.println("The result is :" + postfix.calculate());
    }

}
