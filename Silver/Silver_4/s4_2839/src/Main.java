import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        int n = input/3; // 사용할 수 있는 3키로그램 봉지의 최대 개수
        int res = input;
        for(int i=0; i<=n ;i++){
            int x = input - (i*3); // 3키로그램 봉지를 i개 사용했을 때 남은 설탕의 무게
            // 남은 설탕을 5키로그램 봉지에 담았을 때, 나머지가 0이면 res의 대소비교를 통해 갱신해준다.
            if(x%5 == 0 && (i+x/5)<res) res = i+x/5;
        }
        if(res == input) System.out.println(-1);
        else System.out.println(res);
    }
}