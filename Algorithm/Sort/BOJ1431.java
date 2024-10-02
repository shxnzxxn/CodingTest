import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    static int n;
    static Serial[] serials;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        serials = new Serial[n];

        for(int i=0; i<n; i++){
            String tmp = br.readLine();

            int length = tmp.length();
            int sum = getSum(tmp);

            serials[i] = Serial.of(length, sum, tmp);
        }

        List<Serial> sortedSerials = Arrays.stream(serials).sorted(new Comparator<Serial>() {
            @Override
            public int compare(Serial o1, Serial o2) {
                if (o1.length > o2.length) {
                    return 1;
                } else if (o1.length < o2.length) {
                    return -1;
                }

                if (o1.sum > o2.sum) {
                    return 1;
                } else if (o1.sum < o2.sum) {
                    return -1;
                }

                return o1.text.compareTo(o2.text);
            }
        }).collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();

        for (Serial sortedSerial : sortedSerials) {
            sb.append(sortedSerial.text + "\n");
        }

        System.out.println(sb);
    }

    private static int getSum(String tmp) {
        int sum = 0;
        for (String str : tmp.split("")) {
            try {
                int i = Integer.parseInt(String.valueOf(str));
                sum += i;
            }catch (Exception ignored){}
        }

        return sum;
    }

    static class Serial{
        int length;
        int sum;
        String text;

        public Serial(int length, int sum, String text) {
            this.length = length;
            this.sum = sum;
            this.text = text;
        }

        public static Serial of(int length, int sum, String txt){
            return new Serial(length, sum, txt);
        }


    }


}