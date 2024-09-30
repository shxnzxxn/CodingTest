import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int up = Integer.parseInt(st.nextToken());
        int down = Integer.parseInt(st.nextToken());
        int height = Integer.parseInt(st.nextToken());
        int res;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        if((height-up)%(up-down) == 0){
            res = (height-up)/(up-down)+1;
        }else{
            res = (height-up)/(up-down)+2;
        }
        bw.write(String.valueOf(res));
        bw.flush();
        bw.close();
    }
}