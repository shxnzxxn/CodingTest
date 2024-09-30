import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static boolean[] visited;
    static int[] input;
    static int[] res;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        input = new int[n];
        res = new int[m];
        visited = new boolean[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) input[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(input);

        BackTracking(0);
        System.out.println(sb);
    }

    private static void BackTracking(int x) {
        if(x==m){
            for(int i : res) sb.append(i+" ");
            sb.append("\n");
            return;
        }

        for(int i=0; i<input.length; i++){
            if(!visited[i]){
                visited[i] = true;
                res[x] = input[i];
                BackTracking(x+1);
                visited[i] = false;
            }
        }
    }
}