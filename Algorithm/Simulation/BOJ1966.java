import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int iter = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int k=0; k<iter; k++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int obj = Integer.parseInt(st.nextToken());
            Queue<Integer> idx = new LinkedList<>();
            ArrayList<Integer> q = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                q.add(Integer.parseInt(st.nextToken()));
                idx.add(i);
            }
            int pop = -1;
            int res = 0;
            while(pop != obj){
                if(max(q) != q.get(0)){
                    q.add(q.remove(0));
                    idx.add(idx.remove());
                }else{
                    pop = idx.remove();
                    q.remove(0);
                    res++;
                }
            }
            bw.write(res+"\n");
        }
        bw.flush(); bw.close();
    }

    public static int max(ArrayList<Integer> res){
        int max = res.get(0);
        int obj;
        for(int i=1; i<res.size(); i++){
            obj = res.get(i);
            if(max < obj) max = obj;
        }
        return max;
    }
}