import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int cnt = 0;
        for(int i=0; i<n; i++){
            boolean isPrime = true;
            int x = sc.nextInt();
            if(x == 1) continue;
            if(x==2 || x == 3){
                cnt++;
                continue;
            }
            for(int j=2; j<=Math.sqrt(x); j++){
                if(x%j == 0){
                    isPrime = false;
                    break;
                }
            }
            if(isPrime) cnt++;
        }
        System.out.println(cnt);
    }
}