import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int max;
    static int now;
    static int goal;
    static int up;
    static int down;
    static int[] distance;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        max = sc.nextInt();
        now = sc.nextInt()-1;
        goal = sc.nextInt()-1;
        up = sc.nextInt();
        down = sc.nextInt();
        distance = new int[max];
        BFS();
    }

    private static void BFS() {
        Queue<Integer> q = new LinkedList<>();
        q.add(now);
        distance[now] = 1;

        while (!q.isEmpty()){
            int x = q.poll();

            if(x==goal){
                System.out.println(distance[x]-1);
                return;
            }

            if(x+up < max && distance[x+up] == 0) {
                q.add(x+up);
                distance[x+up] = distance[x]+1;
            }

            if(x-down >= 0 && distance[x-down] == 0){
                q.add(x-down);
                distance[x-down] = distance[x]+1;
            }
        }
        System.out.println("use the stairs");
    }
}