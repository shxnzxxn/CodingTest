import java.util.Scanner;

public class Main {
    static int n;
    static int m;
    static boolean[] visited;
    static int[] input;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        visited = new boolean[n];
        input = new int[m];

        BackTracking(0, 1);
        System.out.println(sb);
    }

    private static void BackTracking(int x, int i) { // x는 배열에 들어있는 원소의 개수. i는 배열 탐색 시작 위치
        if(x==m){
            for(int j=0; j<input.length; j++) sb.append(input[j]+" ");
            sb.append("\n");
            return;
        }

        for(int j=i; j<=n; j++){
            if(!visited[j-1]){
                visited[j-1] = true;
                input[x] = j;
                BackTracking(x+1, j+1);
                visited[j-1] = false;
            }
        }
    }
}