import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int start = sc.nextInt();
        int min=start, max = start;
        for(int i=1; i<n; i++){
            int x = sc.nextInt();
            if(x<min) min = x;
            else if(x>max) max = x;
        }
        System.out.println(min+" "+max);
    }
}