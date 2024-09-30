import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int M = 6;
    static int N;
    static boolean[] visited;
    static int[] input;
    static int[] res = new int[M];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            String[] inputArr = br.readLine().split(" ");
            if(inputArr[0].equals("0")) break;
            N = Integer.parseInt(inputArr[0]);
            input = new int[N];
            visited = new boolean[N];
            for(int i=1; i<=N; i++) input[i-1] = Integer.parseInt(inputArr[i]);

            Arrays.sort(input);

            BackTracking(0, 0);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void BackTracking(int x, int start) {
        if(x==M){
            for (int i : res) sb.append(i+" ");
            sb.append("\n");
            return;
        }

        for(int i=start; i<N; i++){
            if(!visited[i]){
                res[x] = input[i];
                visited[i] = true;
                BackTracking(x+1, i+1);
                visited[i] = false;
            }
        }
    }
}