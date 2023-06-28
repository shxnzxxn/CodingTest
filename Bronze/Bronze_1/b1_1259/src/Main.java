import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true){
            boolean istrue = false;
            int input2 = sc.nextInt();
            String[] input1 = String.valueOf(input2).split("");
            int[] input = new int[input1.length];
            for(int i=0; i<input.length; i++) input[i] = Integer.parseInt(input1[i]);
            if(input.length == 1 && input[0] == 0) System.exit(0);
            Stack<Integer> stack = new Stack<>();
            int mid = input.length/2;

            for(int i=0; i<mid; i++) stack.push(input[i]);

            if(input.length%2 != 0) mid++;

            for(int i=mid; i<input.length; i++){
                int x = stack.pop();
                if(x != input[i]){
                    System.out.println("no");
                    istrue = true;
                    break;
                }
            }
            if(!istrue) System.out.println("yes");
        }
    }
}