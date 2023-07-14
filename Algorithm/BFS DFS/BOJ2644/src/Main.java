import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Graph gp = new Graph(n);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(br.readLine());
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            gp.addTwoWayEdge(a-1, b-1);
        }
        System.out.println(gp.BFS(x-1, y-1));
    }
}
class Graph{
    int N;
    LinkedList<Integer>[] obj;
    int[] distance;
    LinkedList<Integer> q = new LinkedList<>();
    Graph(int N){
        this.N = N;
        obj = new LinkedList[N];
        distance = new int[N];
        for (int i = 0; i < N; i++) {
            obj[i] = new LinkedList<>();
        }
    }

    void addTwoWayEdge(int x, int y){
        obj[x].add(y);
        obj[y].add(x);
    }

    int BFS(int m, int nn){
        int[] distance = new int[N];
        for(int i=0; i<distance.length; i++) distance[i] = -1;
        distance[m] = 0;
        q.add(m);
        while(!q.isEmpty()){
            m = q.poll();
            ListIterator<Integer> it = obj[m].listIterator();
            while(it.hasNext()){
                int n = it.next();
                if(distance[n] == -1){
                    distance[n] = distance[m]+1;
                    q.add(n);
                    if(n==nn) return distance[nn];
                }
            }
        }
        return -1;
    }
}