import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    static int n;
    static long c;
    static List<NumberCount> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        n = Integer.parseInt(tmp[0]);
        c = Long.parseLong(tmp[1]);

        String[] s = br.readLine().split(" ");
        for (String string : s) {
            long x = Long.parseLong(string);

            boolean isContain = false;
            NumberCount tmpNumberCount = null;

            for (NumberCount numberCount : list) {
                if(numberCount.number == x){
                    isContain = true;
                    tmpNumberCount = numberCount;
                }
            }

            if(isContain){
                tmpNumberCount.cnt++;
            }else{
                list.add(new NumberCount(x, 1));
            }
        }
        list.sort(new Comparator<NumberCount>() {
            @Override
            public int compare(NumberCount o1, NumberCount o2) {
                if(o1.cnt > o2.cnt){
                    return -1;
                }else if(o1.cnt == o2.cnt){
                    return 0;
                }else {
                    return 1;
                }
            }
        });

        StringBuilder sb = new StringBuilder();

        for (NumberCount numberCount : list) {
            for(int i=0; i<numberCount.cnt; i++){
                sb.append(numberCount.number+" ");
            }
        }

        System.out.println(sb);
    }

    static class NumberCount {
        public long number;
        public int cnt;

        public NumberCount(long number, int cnt) {
            this.number = number;
            this.cnt = cnt;
        }
    }
}