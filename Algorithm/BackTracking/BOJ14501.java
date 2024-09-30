import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int n;
    static List<ScheduledJob> schedule = new ArrayList<>();
    static int maxValue = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        for(int i=0; i<n; i++){
            String[] input = br.readLine().split(" ");

            schedule.add(new ScheduledJob(input[0], input[1]));
        }

        calculate(0, 0);

        System.out.println(maxValue);
    }

    private static void calculate(int x, int value) {
        if(x == n){
            // x > n일 때(더 이상 진행할 수 없을 때)는 x == n으로 호출하는 대신 value는 갱신하지 않고 호출하게 한다.
            maxValue = Math.max(maxValue, value);
            return;
        }

        for(int i=x; i<n; i++){
            ScheduledJob job = schedule.get(i);

            if(job.day + i <= n){
                // i 위치 방문.
                calculate(i+ job.day, value+job.value);
            }else if(job.day + i > n){
                // 아무 곳도 방문하지 않고 넘어감.
                calculate(n, value);
            }
        }
    }

    public static class ScheduledJob{
        int day;
        int value;

        public ScheduledJob(String day, String value){
            this.day = Integer.parseInt(day);
            this.value = Integer.parseInt(value);
        }
    }
}