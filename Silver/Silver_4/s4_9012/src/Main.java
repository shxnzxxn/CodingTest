import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++){
            String res = "YES";
            String[] input = br.readLine().split("");
            Queue<String> q = new LinkedList<>();
            for(int j=0; j<input.length; j++){
                if (input[j].equals("(")) q.add(input[j]);
                else {
                    if(q.isEmpty()){res = "NO"; break;}
                    else q.remove();
                }
            }
            if(!q.isEmpty()) res = "NO";
            System.out.println(res);
        }
    }
}