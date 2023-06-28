import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int num = (int)Math.ceil(Math.log10(x));
        ArrayList<Integer> res = new ArrayList<>();

        for(int i=Math.max(x-num*9, 0); i<x; i++){
            String[] tmp = String.valueOf(i).split("");
            int a = 0;
            for(int j = 0; j<tmp.length; j++) {
                a += Integer.parseInt(tmp[j]);
            }
            if(a + i == x) res.add(i);
        }
        if(res.isEmpty()) System.out.println(0);
        else{
            Collections.sort(res);
            System.out.println(res.get(0));
        }
    }
}