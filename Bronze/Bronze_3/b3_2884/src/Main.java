import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int hour = sc.nextInt();
        int min = sc.nextInt();
        int res_hour, res_min;
        if(min < 45) {
            if(hour == 0) res_hour = 23;
            else res_hour = hour - 1;
            res_min = 60-(45-min);
        }else{
            res_hour = hour;
            res_min = min - 45;
        }
        System.out.println(res_hour+" "+res_min);
    }
}