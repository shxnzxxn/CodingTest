import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt();
        sc.nextLine();
        String[] input = sc.nextLine().split(" ");
        for(int i=0; i<input.length; i++){
            int y = Integer.parseInt(input[i]);
            if(x>y) System.out.print(y+" ");
        }
    }
}