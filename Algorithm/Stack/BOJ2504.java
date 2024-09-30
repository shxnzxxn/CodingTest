import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split("");
        int value = 1;
        int res = 0;
        Stack<String> stack = new Stack<>();
        for(int i=0; i<input.length; i++) {
            try {
                if (input[i].equals("(")) {
                    value *= 2;
                    stack.push(input[i]);
                } else if (input[i].equals(")")) {
                    if (stack.isEmpty() || !stack.peek().equals("(")) {
                        System.out.println(0);
                        System.exit(0);
                    } else if (input[i - 1].equals("(")) {
                        res += value;

                    }
                    stack.pop();
                    value /= 2;
                } else if (input[i].equals("[")) {
                    value *= 3;
                    stack.push(input[i]);
                } else {
                    if (stack.isEmpty() || !stack.peek().equals("[")) {
                        System.out.println(0);
                        System.exit(0);
                    } else if (input[i - 1].equals("[")) {
                        res += value;

                    }
                    stack.pop();
                    value /= 3;
                }
            }catch(Exception e){
                System.out.println(0);
                System.exit(0);
            }
        }
        if(stack.isEmpty()) System.out.println(res);
        else System.out.println(0);
    }
}