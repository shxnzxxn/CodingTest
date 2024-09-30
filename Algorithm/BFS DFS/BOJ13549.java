import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int start;
    static int end;
    static int res = 1000000;
    static int n = 100000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        start = sc.nextInt();
        end = sc.nextInt();

        BFS();
        System.out.println(res);

    }

    private static void BFS() {
        boolean[] visited = new boolean[n+1];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{start, 0});
        visited[start] = true;
        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            int x = tmp[0];
            int xTime = tmp[1];
            visited[x] = true;

            if(x==end){
                res = Math.min(res, xTime);
            }

            if(x*2 <= n && !visited[x*2]) q.add(new int[]{x*2, xTime});
            if(x+1 <= n && !visited[x+1]) q.add(new int[]{x+1, xTime+1});
            if(x-1 >= 0 && !visited[x-1]) q.add(new int[]{x-1, xTime+1});

        }
    }
}