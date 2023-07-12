import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken())-1;

        Graph graph = new Graph(M);
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1; // 인덱스는 0부터
            int b = Integer.parseInt(st.nextToken())-1; // 인덱스는 0부터
            graph.addEdge(a, b);
        }
        StringBuilder sb = new StringBuilder();
        int[] distance = graph.BFS(X);
        LinkedList<Integer> res = new LinkedList<>();
        for(int i=0; i<distance.length; i++){
            if(distance[i] == K) res.add(i+1);
        }
        Collections.sort(res);
        for(int i=0; i<res.size(); i++) sb.append(res.get(i)+"\n");
        if(res.isEmpty()) sb.append(-1);
        System.out.println(sb);
    }
}
class Graph{
    int N;
    LinkedList<Integer>[] adj;

    Graph(int n){
        this.N = n;
        adj = new LinkedList[n];
        for (int i = 0; i < n; i++) adj[i] = new LinkedList<>();
    }

    void addEdge(int a, int b) {adj[a].add(b);}

    int[] BFS(int a){
        int[] distance = new int[N];
        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i<distance.length; i++) distance[i] = -1;
        q.add(a);
        distance[a] = 0;
        while(!q.isEmpty()){
            int now = q.poll();
            ListIterator<Integer> it = adj[now].listIterator();
            while(it.hasNext()){
                int n = it.next();
                if(distance[n] == -1){
                    distance[n] = distance[now]+1;
                    q.add(n);
                }
            }
        }
        return distance;
    }
}
