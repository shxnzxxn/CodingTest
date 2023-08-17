import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int M;
    static int N;
    static int[] res;
    static int[] input;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        res = new int[M];
        input = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) input[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(input);


        BackTracking(0);

        System.out.println(sb);
    }

    private static void BackTracking(int x) {
        if(x==M){
            for(int i : res) sb.append(i+" ");
            sb.append("\n");
            return;
        }

        int tmp = 0;
        for(int i=0; i<N; i++){
            if(tmp != input[i]){
                res[x] = input[i];
                tmp = input[i];
                BackTracking(x+1);
            }
        }
    }
}