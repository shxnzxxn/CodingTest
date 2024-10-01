import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
    static boolean[] arr = new boolean[2000001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        for(int i=0; i<n; i++){
            int x = Integer.parseInt(br.readLine());
            arr[x+1000000] = true;
        }

        StringBuilder sb = new StringBuilder();

        int tmp = 0;
        for(int i=arr.length-1; i>= 0; i--){
            if(arr[i]){
                sb.append(i-1000000+"\n");
                tmp++;
            }

            if(tmp == n){
                break;
            }
        }

        System.out.println(sb);
    }


}