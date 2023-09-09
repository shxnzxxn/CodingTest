import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class test {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[][] obj = new int[5][5];

        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) obj[i][j] = Integer.parseInt(st.nextToken());
        }

        int[][] res = new int[5][5];
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                res[i][j] = obj[j][5-i-1];
            }
        }

        for(int i=0; i<5; i++) System.out.println(Arrays.toString(res[i]));
    }
}
