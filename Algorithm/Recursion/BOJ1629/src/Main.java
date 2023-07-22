import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextInt();
        long b = sc.nextInt();
        long c = sc.nextInt();
        System.out.println(multiple(a, b, c));
    }
    public static long multiple(long a, long b, long c){
        if(b == 1) return a%c;
        long x = multiple(a, b/2, c);
        if(b%2 == 0) {
            return (x*x)%c;
        }else{
            return ((x*x)%c * a)%c;
        }
    }
}