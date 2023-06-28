import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] res = new int[10000];
        for(int i=0; i<n; i++) {
            int x = Integer.parseInt(bf.readLine());
            res[x-1]++;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i=0; i<res.length; i++){
            if(res[i] != 0) {
                String str = (i+1)+"\n";
                bw.write(str.repeat(res[i]));
                bw.flush();
            }
        }
        bw.close();
    }
}