import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        if(n==0){
            System.out.println(0);
            System.exit(0);
        }
        long res = 1;
        int cnt = 0;
        for(int i=1; i<=n; i++){
            res *= i;
            String tmp = String.valueOf(res);
            int num = 0;
            if(res%10 == 0){
                for(int j=tmp.length()-1; j>=0; j--){
                    if(tmp.charAt(j) == '0') num++;
                    else break;
                }
                cnt+= num;
            }
            int start = tmp.length()-10-num<0? 0:tmp.length()-10-num;
            res = Long.parseLong(tmp.substring(start, tmp.length()-num));

        }
        System.out.println(cnt);
    }
}