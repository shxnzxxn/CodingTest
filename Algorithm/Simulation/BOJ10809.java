import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split("");
        List<String> list = Arrays.asList(input);
        int[] res = new int[26];
        for(int i=97; i<123; i++){
            String x = Character.toString((char) i);
            if(list.contains(x)) res[i-97] = list.indexOf(x);
            else res[i-97] = -1;
        }

        for(int i=0; i<res.length; i++) System.out.print(res[i]+" ");
    }
}