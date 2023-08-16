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

        BackTracking(0);
        System.out.println(sb);
    }

    private static void BackTracking(int x) {
        if(x == m){
            for(int i=0; i<input.length; i++) sb.append(input[i]+" ");
            sb.append("\n");
            return;
        }

        for(int i=1; i<=n; i++){
            input[x] = i;
            BackTracking(x+1);
        }
    }
}