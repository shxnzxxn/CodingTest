import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int obj = Integer.parseInt(st.nextToken())-1;
        ArrayList<Integer> res = new ArrayList<>();
        for(int i=1; i<=n; i++) res.add(i);
        int idx = 0;
        bw.write("<");
        while(res.size()>0){
            idx = idx+obj>=res.size()? (idx+obj)%res.size() : idx+obj;
            if(res.size() == 1) bw.write(res.remove(0)+">");
            else bw.write(res.remove(idx)+", ");
        }
        bw.flush(); bw.close();
    }
}