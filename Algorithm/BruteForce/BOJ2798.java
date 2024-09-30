import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int obj = Integer.parseInt(st.nextToken());
        String[] tmp = br.readLine().split(" ");
        int[] input = Arrays.stream(tmp).mapToInt(Integer::parseInt).toArray();
        int res = 0;
        for(int i=input.length-1; i>=0; i--){
            for(int j=i-1; j>=0; j--){
                for(int k=j-1; k>=0; k--){
                   int sum = input[i] + input[j] + input[k];
                   if((sum <= obj) && (res < sum)) res = sum;
                   if(res == obj){
                       System.out.println(res);
                       System.exit(0);
                   }
                }
            }
        }
        System.out.println(res);
    }
}