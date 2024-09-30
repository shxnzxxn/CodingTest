import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] input;
    static int res = 0;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        input = new int[N][2]; // { durability, weight }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            input[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }

        // 순서가 상관있는 중복을 허용하지 않는 수열을 먼저 만들자.
        // 우선 크기는 N까지 다 가도록!
        BackTracking(0);

        System.out.println(res);
    }

    private static void BackTracking(int x) { // x가지고 내려칠거임
        if (x == N) {
            res = Math.max(res, cnt);
            return;
        }

        if (input[x][0] <= 0 || cnt == N - 1) { // 손에 드는 계란이 꺠졌거나, 이미 내가 손에 든 계란을 제외한 모든 계란이 깨졌으면 패스
            BackTracking(x + 1);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (input[i][0] > 0 && i != x) { // 안 깨진 계란이거나 내가 손에 든 계란이 아닌 계란을 쳐야함.
                input[i][0] -= input[x][1];
                input[x][0] -= input[i][1];
                if (input[i][0] <= 0) cnt++;
                if (input[x][0] <= 0) cnt++;
                BackTracking(x + 1);
                if (input[i][0] <= 0) cnt--;
                if (input[x][0] <= 0) cnt--;
                input[i][0] += input[x][1];
                input[x][0] += input[i][1];
            }
        }
    }
}