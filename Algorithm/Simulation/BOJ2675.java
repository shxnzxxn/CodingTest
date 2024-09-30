import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for(int i=0; i<n; i++) {
            int iter = sc.nextInt();
            String[] input = sc.nextLine().trim().split("");
            for (int k = 0; k < input.length; k++) {
                for (int j = 0; j < iter; j++) {
                    System.out.print(input[k]);
                }
            }
            System.out.print("\n");
        }
    }
}