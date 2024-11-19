import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int n;
    static StringBuilder sb = new StringBuilder();
    static int[] a;
    static int[] b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        for(int i=0; i<n; i++){
            int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            a = new int[tmp[0]];
            b = new int[tmp[1]];

            a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            b = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            Arrays.sort(a);
            Arrays.sort(b);

            int cnt = 0;

            for (int i1 : a) {
                for(int j=b.length-1; j>=0; j--){
                    if(i1 > b[j]){
                        cnt += j+1;
                        break;
                    }
                }
            }

            sb.append(cnt + "\n");
        }

        System.out.println(sb.toString());
    }
}