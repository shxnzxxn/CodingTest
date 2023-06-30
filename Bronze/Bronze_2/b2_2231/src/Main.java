import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int num = (int)Math.ceil(Math.log10(x));
        int res = x;
        for(int i=Math.max(x-num*9, 0); i<x; i++){
            String[] tmp = String.valueOf(i).split("");
            int a = 0;
            for(int j = 0; j<tmp.length; j++) {
                a += Integer.parseInt(tmp[j]);
            }
            if((a + i == x) && (res>i)) res = i;
        }
        if(res == x) System.out.println(0);
        else{            System.out.println(res);
        }
    }
}