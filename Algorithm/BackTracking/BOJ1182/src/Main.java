import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static int[] input;
    static int N;
    static int S;
    static int res = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        input = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) input[i] = Integer.parseInt(st.nextToken());

        BackTracking(0, 0);
        if(S == 0) res--;
        System.out.println(res);
    }

    private static void BackTracking(int x, int sum) {
        if(N == x){
            if(sum == S) {
                res++;
            }
            return;
        }

        BackTracking(x+1, sum);
        BackTracking(x+1, sum+input[x]);
    }
}