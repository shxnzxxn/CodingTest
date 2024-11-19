import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int n;
    static int[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n+1];
        visited = new boolean[n+1];

        for(int i = 1; i < n+1; i++) {
            // x*3
            if(i*3 <= n) {
                if (!visited[i * 3]) {
                    arr[i * 3] = arr[i] + 1;
                    visited[i * 3] = true;
                } else if (arr[i * 3] > arr[i] + 1) {
                    arr[i * 3] = arr[i] + 1;
                }
            }

            if(i*2 <= n) {
                if (!visited[i * 2]) {
                    arr[i * 2] = arr[i] + 1;
                    visited[i * 2] = true;
                } else if (arr[i * 2] > arr[i] + 1) {
                    arr[i * 2] = arr[i] + 1;
                }
            }

            if(i+1 <= n) {
                if (!visited[i + 1]) {
                    arr[i + 1] = arr[i] + 1;
                    visited[i + 1] = true;
                } else if (arr[i + 1] > arr[i] + 1) {
                    arr[i + 1] = arr[i] + 1;
                }
            }
        }

        System.out.println(arr[n]);
    }
}