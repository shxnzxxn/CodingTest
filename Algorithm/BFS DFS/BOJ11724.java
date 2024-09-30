import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        Graph gp = new Graph(n);
        int m = Integer.parseInt(st.nextToken());
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            gp.addTwoWayEdge(x-1, y-1);
        }
        System.out.println(gp.countComponent());
    }
}

class Graph{
    int N;
    LinkedList<Integer>[] obj;
    boolean[] visited;
    Graph(int n){
        this.N = n;
        obj = new LinkedList[n];
        for (int i = 0; i < n; i++) obj[i] = new LinkedList<>();
        visited = new boolean[N];
    }

    void addTwoWayEdge(int i, int j){
        obj[i].add(j);
        obj[j].add(i);
    }
    int countComponent(){
        int res=0;
        for(int i=0; i<N; i++) if(findComponent(i)) res++;
        return res;
    }

    boolean findComponent(int x){ // x와 연결된 모든 노드를 list에 담아서 반환
        if(visited[x]) return false;
        LinkedList<Integer> q = new LinkedList<>();
        LinkedList<Integer> res = new LinkedList<>();
        visited[x] = true;
        q.add(x);
        while(!q.isEmpty()){
            int s = q.poll();
            res.add(s);

            ListIterator<Integer> it = obj[s].listIterator();
            while (it.hasNext()) {
                int n = it.next();
                if(!visited[n]){
                    visited[n] = true;
                    q.add(n);
                }
            }
        }
        return true;
    }
}