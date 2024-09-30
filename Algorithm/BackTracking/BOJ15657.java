import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int M;
    static int N;
    static int[] input;
    static int[] res;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        input = new int[N];
        res = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) input[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(input);

        BackTracking(0, 0);

        System.out.println(sb);
    }

    private static void BackTracking(int x, int start) {
        if(x == M){
            for(int i : res) sb.append(i+" ");
            sb.append("\n");
            return;
        }

        for(int i=start; i<N; i++){
            res[x] = input[i];
            BackTracking(x+1, i);
        }
    }
}