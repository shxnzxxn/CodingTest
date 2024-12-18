import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
    static int[][] arr;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n+1][3];
        int[][] res = new int[n+1][3];

        for(int i=1; i<=n; i++){
            String[] tmp = br.readLine().split(" ");

            for(int j=0; j<3; j++){
                int x = Integer.parseInt(tmp[j]);
                arr[i][j] = x;
            }
        }

        for(int i=1; i<=n; i++){
            res[i][0] = arr[i][0] + Math.min(res[i-1][1], res[i-1][2]);
            res[i][1] = arr[i][1] + Math.min(res[i-1][0], res[i-1][2]);
            res[i][2] = arr[i][2] + Math.min(res[i-1][0], res[i-1][1]);
        }

        System.out.println(Math.min(Math.min(res[n][0], res[n][1]), res[n][2]));
    }
}