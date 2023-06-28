import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();
            if(x==0 && y == 0 && z == 0) break;
            if (x < y) {
                int tmp = x;
                x = y;
                y = tmp;
            }
            if (x < z) {
                int tmp = x;
                x = z;
                z = tmp;
            }
            if (x * x == y * y + z * z) System.out.println("right");
            else System.out.println("wrong");
        }
    }
}