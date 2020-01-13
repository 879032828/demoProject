package com.example.datastructure.stack;

/**
 * 后缀表达式计算算术表达式
 */
public class Postfix {

    /**
     * 中缀表达式
     */
    private String infix;
    /**
     * 存储操作符
     * <p>
     * 中缀表达式转后缀表达式时使用
     */
    private Stack operatorStack;
    /**
     * 后缀表达式输出
     */
    private StringBuilder output;
    /**
     * 存储操作数
     * <p>
     * 计算后缀表达式时使用
     */
    private Stack operandStack;

    public Postfix(String value) {
        infix = value;
        output = new StringBuilder();
        operatorStack = new Stack(value.length());
        operandStack = new Stack(value.length());
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
                        operatorStack.push(infixChar);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case ')':
                    while (!operatorStack.isEmpty()) {//栈非空时
                        try {
                            char data = (char) operatorStack.pop();//弹出一项
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
                    if (operatorStack.isEmpty()) {//若栈为空
                        try {
                            operatorStack.push(infixChar);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {//若栈不为空
                        while (!operatorStack.isEmpty()) {//循环
                            try {
                                char data = (char) operatorStack.pop();
                                if (data == '(') {
                                    operatorStack.push(data);
                                    break;
                                }

                                if (priorityCompare(data) < priorityCompare(infixChar)) {
                                    operatorStack.push(data);
                                    break;
                                } else {
                                    output.append(data);

                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        try {
                            operatorStack.push(infixChar);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
        }

        while (!operatorStack.isEmpty()) {
            try {
                output.append((char) operatorStack.pop());
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

    public long calculate() {
        String data = output.toString();
        long operand1;
        long operand2;
        long result = 0;
        for (int i = 0; i < data.length(); i++) {
            char operand = data.charAt(i);
            switch (operand) {
                case '+':
                case '-':
                case '*':
                case '/':
                    try {
                        operand1 = operandStack.pop();
                        operand2 = operandStack.pop();
                        if ('+' == operand) {
                            result = operand2 + operand1;
                        }
                        if ('-' == operand) {
                            result = operand2 - operand1;
                        }
                        if ('*' == operand) {
                            result = operand2 * operand1;
                        }
                        if ('/' == operand) {
                            result = operand2 / operand1;
                        }
                        operandStack.push(result);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    try {
                        operandStack.push(toInt(operand));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
        try {
            return operandStack.pop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private int toInt(char ch) {
        int num = 0;
        if (Character.isDigit(ch)) {  // 判断是否是数字
            num = Integer.parseInt(String.valueOf(ch));
        }
        return num;
    }

}
