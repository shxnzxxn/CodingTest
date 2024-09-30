import java.util.Scanner;

public class Main {
    static int N;
    static int cnt = 0;
    static int[] chess;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        chess = new int[N]; // 인덱스 자체는 행을 나타내고, 인덱스의 값은 열을 나타냄.
        backTracking(0);
        System.out.println(cnt);
    }

    static void backTracking(int n){
        if(n == N){
            cnt++;
            return;
        }

        for(int i=0; i<N; i++){
            chess[n] = i; // n행에서 1열부터 N열까지의 가능성을 탐색하기
            if(canPut(n)){ // 놓을 수 없으면, n행 다음 열을 판단함.
                backTracking(n+1);
            }
        }
    }
    static boolean canPut(int n){
        for(int i=0; i<n; i++){
            // 같은 열이거나 || 각 행과 각 열의 차이가 같으면 안됨(대각선에 위치하는 경우)
            if(chess[i] == chess[n] || (Math.abs(chess[n] - chess[i]) == n-i)) return false;
        }
        return true;
    }
}