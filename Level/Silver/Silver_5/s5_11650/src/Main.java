import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        ArrayList<Integer>[] arr = new ArrayList[200001];
        for(int k = Integer.parseInt(br.readLine()); k>0; k--) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if(arr[x+100000] == null) arr[x+100000] = new ArrayList<>();
            arr[x+100000].add(y);
        }
        for(int i=0; i<arr.length; i++){
            if(arr[i] != null){
                Collections.sort(arr[i]);
                for(int j=0; j<arr[i].size(); j++) bw.write((i-100000)+" " +arr[i].get(j)+"\n");
            }
        }
        bw.flush(); bw.close();
    }
}