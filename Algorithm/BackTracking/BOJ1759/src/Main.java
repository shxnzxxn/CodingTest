import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    static String[] input;
    static int m;
    static int n;
    static boolean[] visited;
    static String[] res;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        sc.nextLine();

        input = new String[n];
        visited = new boolean[n];
        res = new String[m];
        input = sc.nextLine().split(" ");
        Arrays.sort(input);

        BackTracking(0, 0);
        System.out.println(sb);
    }

    private static void BackTracking(int x, int start) {
        if(x==m){
            if(check(res)){
                for(int i=0; i<res.length; i++) sb.append(res[i]);
                sb.append("\n");
            }
            return;
        }

        for(int i=start; i<input.length; i++){
            if(!visited[i]){
                visited[i] = true;
                res[x] = input[i];
                BackTracking(x+1, i+1);
                visited[i] = false;
            }
        }
    }

    private static boolean check(String[] res) {
        // a, e, i, o, u가 적어도 하나.
        // 그 외가 적어도 두개 이상!
        int vowel = 0;
        for(int i=0; i<res.length; i++){
            if(res[i].equals("a") || res[i].equals("e") || res[i].equals("i")
                    || res[i].equals("o") || res[i].equals("u")) vowel++;
        }

        if(vowel < 1 || res.length - vowel < 2) return false;
        else return true;
    }
}