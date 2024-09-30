import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N;
    static int K;
    static int[] distance = new int[100001];
    static int[] idx = new int[100001];
    static LinkedList<Integer> res;
    static int cnt = 1000000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        if(N>K){
            sb.append(N-K+"\n");
            for(int i= N; i>=K; i--) sb.append(i+" ");
            System.out.println(sb);
            System.exit(0);
        }

        BFS(N);
        sb.append(cnt+"\n");
        for (int i = res.size() - 1; i >= 0; i--) sb.append(res.get(i) + " ");
        System.out.println(sb);
    }

    private static void BFS(int n) {
        Queue<Integer> q = new LinkedList<>();
        q.add(n);

        while(!q.isEmpty()){
            int x = q.poll();

            if(x==K){
                cnt = Math.min(cnt, distance[x]);
                if(cnt == distance[x]) {
                    int index = x;
                    res = new LinkedList<>();
                    while (index != N) {
                        res.add(index);
                        index = idx[index];
                    }
                    res.add(N);
                }
            }

            if (x + 1 <= 100000 && distance[x + 1] == 0) {
                distance[x + 1] = distance[x] + 1;
                idx[x + 1] = x;
                q.add(x + 1);
            }

            if (x * 2 <= 100000 && distance[x * 2] == 0) {
                distance[x * 2] = distance[x] + 1;
                idx[x * 2] = x;
                q.add(x * 2);
            }

            if(x-1>=0 && distance[x-1] == 0){
                distance[x-1] = distance[x]+1;
                idx[x-1] = x;
                q.add(x-1);
            }
        }
    }
}