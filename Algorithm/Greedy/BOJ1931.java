import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        Long[][] schedule = new Long[n][2];
        int earlyStopIdx = 0;

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            schedule[i][0] = Long.parseLong(st.nextToken());
            schedule[i][1] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(schedule, new Comparator<Long[]>() {
            @Override
            public int compare(Long[] o1, Long[] o2) {
                return (int) (o1[1]!=o2[1] ? o1[1]-o2[1] : o1[0]-o2[0]);
            }
        });

        boolean[] visited = new boolean[n];
        visited[0] = true;

        int cnt = 1;
        while(true){
            int nextStopIdx = -1;
            for(int i=earlyStopIdx+1; i<n; i++){
                if(schedule[earlyStopIdx][1] <= schedule[i][0]) {
                    if(!visited[i]) {
                        nextStopIdx = i;
                        visited[i] = true;
                        break;
                    }
                }
            }
            if(nextStopIdx == -1) break;
            earlyStopIdx = nextStopIdx;
            cnt++;
        }
        System.out.println(cnt);
    }
}