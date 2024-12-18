import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
    static int[] time;
    static int[] parent;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        time = new int[Math.max(n+1, 4)];
        parent = new int[Math.max(n + 1, 4)];

        time[2] = 1;
        time[3] = 1;
        parent[2] = 1;
        parent[3] = 1;

        for(int i=4; i<=n; i++){
            int x = i-1;

            if(i%2 == 0){
                x = time[x] > time[i/2] ? i/2 : x;
            }

            if(i%3 == 0){
                x = time[x] > time[i/3] ? i/3 : x;
            }

            time[i] = time[x]+1;
            parent[i] = x;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(time[n]+"\n");

        int idx = n;
        while(idx != 0){
            sb.append(idx+" ");
            idx = parent[idx];
        }

        System.out.println(sb);
    }
}