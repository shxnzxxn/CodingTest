import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int m;
    static int n;
    static int[] number;
    static int[] sum;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        n = Integer.parseInt(tmp[0]);
        m = Integer.parseInt(tmp[1]);

        number = new int[n];
        sum = new int[n+1];

        String[] numbers = br.readLine().split(" ");

        for(int i=0; i<n; i++){
            int x = Integer.parseInt(numbers[i]);

            number[i] = x;
        }

        for(int i=1; i<=n; i++){
            sum[i] = sum[i-1] + number[i-1];
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<m; i++){
            String[] tmp1 = br.readLine().split(" ");
            int a = Integer.parseInt(tmp1[0]);
            int b = Integer.parseInt(tmp1[1]);

            sb.append(sum[b] - sum[a-1]+"\n");
        }

        System.out.println(sb);
    }
}