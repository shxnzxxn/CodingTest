import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int n;
    static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        n = Integer.parseInt(tmp[0]);
        arr = new long[n];

        int tmpIdx = 0; // arr에 담길 인덱스 위치.

        if(tmp.length > 1){
            for(int i=1; i<tmp.length; i++){
                arr[tmpIdx++] = Long.parseLong(flip(tmp[i]));
            }
        }

        while(tmpIdx<n){
            String s1 = br.readLine();

            if(s1.trim().equals("")) continue;
            String[] tmpArr = s1.split(" ");

            for (String s : tmpArr) {
                arr[tmpIdx++] = Long.parseLong(flip(s));
            }
        }

        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder();

        for (long i : arr) {
            sb.append(i+"\n");
        }

        System.out.println(sb);
    }

    private static String flip(String s) {
        StringBuilder sb = new StringBuilder();

        for(int a = s.length(); a>0; a--){
            sb.append(s.substring(a-1, a));
        }

        return sb.toString();
    }
}