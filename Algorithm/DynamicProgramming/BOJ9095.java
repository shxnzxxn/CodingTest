import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static int n;
    static int[] arr;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[12];
        arr[1] = 1;
        arr[2] = 2;
        arr[3] = 4;

        for(int i=4; i<= 11; i++){
            arr[i] = arr[i-1] + arr[i-2] + arr[i-3];
        }

        StringBuilder sb = new StringBuilder();

        for(int i=0; i<n; i++){
            int x = Integer.parseInt(br.readLine());

            sb.append(arr[x]+"\n");
        }

        System.out.println(sb);
    }
}