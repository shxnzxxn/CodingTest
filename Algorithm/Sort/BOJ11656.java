import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static String[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String word = br.readLine();

        arr = new String[word.length()];

        for(int i=0; i<arr.length; i++){
            arr[i] = word.substring(i, arr.length);
        }

        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder();

        for (String s : arr) {
            sb.append(s + "\n");
        }

        System.out.println(sb);
    }
}