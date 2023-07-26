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

        int[] datePerMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30};
        int sum = 0;
        int[] fullDate = new int[12];
        for(int i=0; i<datePerMonth.length; i++){
            sum += datePerMonth[i];
            fullDate[i] = sum;
        }
        int n = Integer.parseInt(br.readLine());
        int[][] schedule = new int[n][2];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());

            int start_month = Integer.parseInt(st.nextToken());
            int start_date = Integer.parseInt(st.nextToken());

            int end_month = Integer.parseInt(st.nextToken());
            int end_date = Integer.parseInt(st.nextToken());

            int start_day = fullDate[start_month-1] + start_date;
            int end_day = fullDate[end_month-1] + end_date;

            schedule[i][0] = start_day;
            schedule[i][1] = end_day;
        }

        Arrays.sort(schedule, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] != o2[0] ? o1[0]-o2[0] : o1[1]-o2[1];
            }
        });

        // 초기 시작은 3월 1일 이전에 피어야하며, 가장 늦게 끝나는 아이여야한다.
        // 우선 scehdule의 첫번째 값을 임의로 정한다.
        if(schedule[0][0] >60){
            System.out.println(0);
            System.exit(0);
        }
        int startIdx = 0;
        for(int i=1; i<n; i++){
            if(schedule[i][0] > 60) break;
            if((schedule[startIdx][1] < schedule[i][1])) startIdx = i;
        }

        int cnt = 1;
        int nextIdx;

        if(schedule[startIdx][1] >= 335){
            System.out.println(1);
            System.exit(0);
        }
        while(true){
            nextIdx = -1;
            for(int i=startIdx+1; i<n; i++){
                if(schedule[startIdx][1] < schedule[i][0]) break;
                if(nextIdx == -1) nextIdx = i;
                else if(schedule[nextIdx][1] < schedule[i][1]) nextIdx = i;
            }
            if(nextIdx == -1) break;
            cnt++;
            if(schedule[nextIdx][1] > 334) break;
            startIdx = nextIdx;
        }
        if(nextIdx== -1 && schedule[startIdx][1] < 335) System.out.println(0);
        else System.out.println(cnt);

    }
}