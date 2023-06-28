import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long x = sc.nextLong();
        if(x==1){
            System.out.println(1);
            System.exit(0);
        }
        long res = 1, idx = 1;
        while(true){
            if(res < x && res+6*idx >= x){
                System.out.println(idx+1);
                break;
            }
            res += 6*(idx++);
        }
    }
}