import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());

        int cnt = 0;
        String res = "";
        for(int i=0; cnt < num; i++){
            String x = String.valueOf(Integer.parseInt(i + "666"));
            int start;
            for(start=0; start<x.length()-2; start++){if(x.substring(start, start+3).equals("666")) break;}
            int back_fill = start+3;
            int fill_n = (int) Math.pow(10, x.length()-back_fill);
            if(fill_n >1) {
                for (int j = 0; j < fill_n && cnt < num; j++) {
                    int a = j == 0 ? 1 : (int) Math.log10(j) + 1;
                    res = x.substring(0, back_fill) + "0".repeat(x.length()-back_fill-a) + j;
                    cnt++;
                }
            }else {
                cnt++;
                res = x;
            }
        }
        System.out.println(Integer.parseInt(res));

    }
}