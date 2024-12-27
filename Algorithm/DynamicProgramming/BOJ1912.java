import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
    static int[] arr;
    static int[] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        sum = new int[n];

        String[] tmp = br.readLine().split(" ");
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(tmp[i]);
        }

        sum[0] = arr[0];

        int max = sum[0];
        for(int i=1; i<n; i++){
            sum[i] = Math.max(sum[i-1] + arr[i], arr[i]);
            max = Math.max(sum[i], max);
        }

        System.out.println(max);
    }
}