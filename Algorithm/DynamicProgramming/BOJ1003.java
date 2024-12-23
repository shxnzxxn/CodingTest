import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
    static int[] arr;
    static int[][] num;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        int max = -1;
        for(int i=0; i<n; i++){
            int x = Integer.parseInt(br.readLine());
            arr[i] = x;

            max = Math.max(max, x);
        }

        num = new int[Math.max(max+1, 2)][2];

        num[0] = new int[]{1, 0};
        num[1] = new int[]{0, 1};

        for(int i=2; i<num.length; i++){
            int x = num[i-1][0] + num[i-2][0];
            int y = num[i-1][1] + num[i-2][1];

            num[i] = new int[]{x, y};
        }

        StringBuilder sb = new StringBuilder();
        for (int i : arr) {
            sb.append(num[i][0]+" ");
            sb.append(num[i][1]+"\n");
        }

        System.out.println(sb);
    }
}