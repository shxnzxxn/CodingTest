import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
    static long[][] arr;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new long[n+1][2];

        arr[1] = new long[]{0, 1};

        for(int i=2; i<=n; i++){
            // 끝이 0으로 끝나는거는 i-1번째 자리의 0으로 끝나는 수의 개수와 i-1번째 자리의 1로 끝나는 개수를 더한다.
            arr[i][0] = arr[i-1][0] + arr[i-1][1];
            arr[i][1] = arr[i-1][0];
        }

        System.out.println(arr[n][0] + arr[n][1]);
    }
}