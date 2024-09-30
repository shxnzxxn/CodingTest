import java.util.Scanner;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int cnt = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        move(1, 3, n); // 1에서 3으로 n개를 옮겨라!
        System.out.println(cnt);
        System.out.println(sb);
    }
    public static void move(int a, int b, int n){
        if(n==1){
            cnt++;
            sb.append(a+" "+b+"\n");
            return;
        }
        move(a, 6-a-b, n-1); // a에서 c로 n-1개를 옮겨라!!
        cnt++;
        sb.append(a+" "+b+"\n"); // 그 다음 a에서 b로 n번째를 옮긴다.
        move(6-a-b, b, n-1); // 그 후, c에서 b로 n-1개를 옮겨라.
    }
}