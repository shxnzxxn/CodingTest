import java.util.Scanner;

public class Main {
    static int n;
    static int m;
    static int[] input;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        input = new int[m];

        BackTracking(0, 1);
        System.out.println(sb);
    }

    private static void BackTracking(int x, int k) {
        if(x==m){
            for(int i: input) sb.append(i+" ");
            sb.append("\n");
            return;
        }

        for(int i=k; i<= n; i++){
            input[x] = i;
            BackTracking(x+1, i);
        }
    }
}