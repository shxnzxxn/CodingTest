import java.util.Scanner;

public class Main {
    static int M;
    static int N;
    static StringBuilder sb = new StringBuilder();
    static int[] res;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();


        boolean[] visited = new boolean[N];
        res = new int[M];

        BackTracking(visited, 0);
        System.out.println(sb);
    }

    private static void BackTracking(boolean[] visited, int x) {
        if(M==x){
            for(int i=0; i<res.length; i++){
                sb.append(res[i]+" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=0; i<N; i++){
            if(!visited[i]){
                visited[i] = true;
                res[x] = i+1;
                BackTracking(visited, x+1);
                visited[i] = false;
            }
        }
    }
}