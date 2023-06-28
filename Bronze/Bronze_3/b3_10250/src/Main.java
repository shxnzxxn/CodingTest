import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for(int i=0; i<n; i++){
            int floor = sc.nextInt();
            int number = sc.nextInt();
            int custom = sc.nextInt();
            int real_number = (custom/floor) +1;
            int real_floor = custom%floor;
            if(real_floor == 0){
                real_floor = floor;
                real_number--;
            }
            if(real_number < 10){
                System.out.println(real_floor+"0"+real_number);
            }else{
                System.out.println(real_floor+""+real_number);
            }
        }
    }
}