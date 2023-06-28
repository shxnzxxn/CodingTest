import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        int n = input/3;
        List<Integer> res = new LinkedList<>();
        for(int i=0; i<=n ;i++){
            int x = input - (i*3);
            if(x%5 == 0) res.add(i+x/5);

        }
        if(res.isEmpty()) System.out.println(-1);
        else{
            Collections.sort(res);
            System.out.println(res.get(0));
        }
    }
}