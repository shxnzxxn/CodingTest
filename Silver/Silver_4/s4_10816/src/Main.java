import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st_n = new StringTokenizer(br.readLine());
        int[][] pl_input = new int[10001][1000];
        int[][] mi_input = new int[10001][1000];
        for(int i=0; i<n; i++){
            int x = Integer.parseInt(st_n.nextToken());
            int a = x/1000;
            int b = x%1000;
            if(x<0) mi_input[-a][-b]++;
            else pl_input[a][b]++;
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int m = Integer.parseInt(br.readLine());
        StringTokenizer st_m = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++){
            int y = Integer.parseInt(st_m.nextToken());
            int a = y/1000;
            int b = y%1000;
            if(y<0) bw.write(mi_input[-a][-b]+" ");
            else bw.write(pl_input[a][b]+" ");
        }
        bw.flush();
        bw.close();
    }
}