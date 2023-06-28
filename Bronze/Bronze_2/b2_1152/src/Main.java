import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().trim().split(" ");
        if (input.length == 1 && input[0].equals("")) System.out.println(0);
        else System.out.println(input.length);
    }
}