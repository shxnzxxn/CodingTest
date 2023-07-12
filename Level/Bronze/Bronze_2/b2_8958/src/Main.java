import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        for(int i=0; i<n; i++){
            String[] input = sc.nextLine().split("");
            int[] res = new int[input.length];
            int cnt = 0;
            int sum = 0;
            for(int j=0; j<input.length; j++){
                if(input[j].equals("O")) res[j] = ++cnt;
                else cnt=0;
                sum+= res[j];
            }
            System.out.println(sum);
        }
    }
}