package com.example.datastructure.stack;

public class Postfix {

    /**
     * 中缀表达式
     */
    private String infix;
    /**
     * 存储操作符
     */
    private Stack stack;
    /**
     * 后缀表达式输出
     */
    private StringBuilder output;

    public Postfix(String value) {
        infix = value;
        output = new StringBuilder();
        stack = new Stack(value.length());
    }

    /**
     * 将中缀表达式转换为后缀表达式
     */
    public void transformToPostfix() {

        for (int i = 0; i < infix.length(); i++) {
            char infixChar = infix.charAt(i);
            switch (infixChar) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    output.append(infixChar);//操作数，写到输出
                    break;
                case '(':
                    try {
                        stack.push(infixChar);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case ')':
                    while (!stack.isEmpty()) {//栈非空时
                        try {
                            char data = (char) stack.pop();//弹出一项
                            if (data == '(') {
                                break;
                            } else {
                                output.append(data);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                default:
                    if (stack.isEmpty()) {//若栈为空
                        try {
                            stack.push(infixChar);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {//若栈不为空
                        while (!stack.isEmpty()) {//循环
                            try {
                                char data = (char) stack.pop();
                                if (data == '(') {
                                    stack.push(data);
                                    break;
                                }

                                if (priorityCompare(data) < priorityCompare(infixChar)) {
                                    stack.push(data);
                                    break;
                                } else {
                                    output.append(data);

                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        try {
                            stack.push(infixChar);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
        }

        while (!stack.isEmpty()) {
            try {
                output.append((char) stack.pop());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void display() {
        System.out.println("后缀表达式：" + output.toString());
    }

    /**
     * 优先级判断
     *
     * @param c
     * @return
     */
    private int priorityCompare(char c) {
        int priority = 0;
        switch (c) {
            case '*':
            case '/':
            case '%':
                priority = 2;
                break;
            case '+':
            case '-':
                priority = 1;
                break;
        }
        return priority;
    }

}
