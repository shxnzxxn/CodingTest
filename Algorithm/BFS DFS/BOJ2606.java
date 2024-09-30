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
        int iter = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i=0; i<iter; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            gp.addTwoWayEdge(x-1, y-1);
        }
        System.out.println(gp.findVirus()+1);
    }
}

class Graph{
    int N;
    LinkedList<Integer>[] obj;

    Graph(int N){
        this.N = N;
        obj = new LinkedList[N];
        for (int i = 0; i < N; i++) obj[i] = new LinkedList<>();
    }

    void addTwoWayEdge(int i, int j){
        obj[i].add(j);
        obj[j].add(i);
    }

    int findVirus(){
        boolean[] visited = new boolean[N];
        LinkedList<Integer> q = new LinkedList<>();
        visited[0] = true;
        q.add(0);
        int num = -1;
        while(!q.isEmpty()){
            int s = q.poll();
            ListIterator<Integer> it = obj[s].listIterator();
            while(it.hasNext()){
                int n = it.next();
                if(!visited[n]){
                    visited[n] = true;
                    num++;
                    q.add(n);
                }
            }
        }
        return num;
    }
}