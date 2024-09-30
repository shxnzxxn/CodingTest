import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        if(n+1/2 < k) k = n-k;
        int up = 1;
        int down = 1;
        for(int i=0; i<k; i++) {
            up *= n-i;
            down *= i+1;
        }
        System.out.println(up/down);
    }
}