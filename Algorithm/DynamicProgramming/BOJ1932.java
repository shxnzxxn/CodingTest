import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
    static int[][] arr;
    static int[][] num;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        num = new int[n][n];

        for(int i=0; i<n; i++){
            String[] tmp = br.readLine().split(" ");

            for(int j=0; j<n; j++){
                if(tmp.length-1 < j){
                    arr[i][j] = -1;
                }else{
                    arr[i][j] = Integer.parseInt(tmp[j]);
                }
            }
        }

        int idx = 0;
        for (int i : arr[n - 1]) {
            num[n-1][idx++] = i;
        }

        for(int i=n-2; i>=0; i--){
            int[] x = arr[i];

            for(int j=0; j<x.length; j++){
                if(arr[i][j] < 0) continue;

                num[i][j] = Math.max(num[i+1][j], num[i+1][j+1]) + arr[i][j];
            }
        }

        System.out.println(num[0][0]);
    }
}