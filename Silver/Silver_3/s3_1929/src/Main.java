import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int start = sc.nextInt();
        int end = sc.nextInt();
        while(start <= end){
            if(isPrime(start)) sb.append(start+"\n");
            start++;
        }
        System.out.println(sb);
    }

    static boolean isPrime(int x){
        if(x==2) return true;
        if(x==1 || x%2 == 0) return false;
        for(int i=3; i<=Math.sqrt(x); i+=2){
            if(x%i == 0) return false;
        }
        return true;
    }
}