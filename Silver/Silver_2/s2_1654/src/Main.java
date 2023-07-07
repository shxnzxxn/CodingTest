import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static long[] height;
    static long[] remain;
    static long[] q;
    static int m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        height = new long[n];
        remain = new long[n];
        q = new long[n];
        long trash = 0;
        long sum = 0;
        for(int i=0; i<n; i++){
            long tmp = Long.parseLong(br.readLine());
            height[i] = tmp;
            sum += tmp/m;
            trash += tmp%m;
        }
        long max = sum + trash/m;
        long num = update(max);

        while(num < m){
            // 우선 랜선을 하나 늘리는 n을 찾자
            max -= find(max);
            // 이제 remain과 바꾸자.
            num = update(max);
        }
        System.out.println(max);
    }

    private static long update(long max) { // max를 가지고 remain과 q를 갱신
        long res = 0;
        for(int i=0; i<n; i++) {
            q[i] = height[i]/max;
            remain[i] = height[i] % max;
            res += q[i];
        }
        return res;
    }

    private static long find(long max){ // q가 갱신되는 최소의 길이 찾기
        long res =(long) Math.ceil((double)(max-remain[0])/(q[0]+1));
        for(int i=1; i<n; i++){
            long tmp = (long)Math.ceil((double)(max-remain[i])/(q[i]+1));
            if(res > tmp) res = tmp;
        }
        return res;
    }

}