import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int price = Integer.parseInt(st.nextToken());
        int[] coin_list = new int[n];
        for(int i=0; i<n; i++) coin_list[i] = Integer.parseInt(br.readLine());

        int cnt = 0;

        for(int i=coin_list.length-1; i>=0 && price >0; i--){
            if(coin_list[i] <= price){
                int num = price/coin_list[i];
                price -= coin_list[i]*num;
                cnt += num;
            }
        }
        System.out.println(cnt);
    }
}