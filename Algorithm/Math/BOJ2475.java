import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int sum = 0;
        for(int i=0; i<input.length; i++){
            sum += Math.pow(Integer.parseInt(input[i]), 2);
        }
        System.out.println(sum%10);

    }
}