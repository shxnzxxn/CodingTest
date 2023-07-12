import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        if(a==b){
            System.out.println(a);
            System.out.println(a);
            System.exit(0);
        }
        if(a%b == 1 || b%a == 1){
            System.out.println(1);
            System.out.println(a*b);
            System.exit(0);
        }

        int x = Math.max(a, b);
        int y = Math.min(a, b);
        int max_div = 1;
        for(int i=2; i<Math.sqrt(x); i++){
            if(x%i == 0){
                if(y%(x/i) == 0 && max_div < x/i) max_div = x/i;
                else if(y%i == 0 && max_div < i) max_div = i;
            }
        }
        System.out.println(max_div);
        int m = x / max_div;
        int n = y / max_div;
        System.out.println(max_div * m * n);


    }
}