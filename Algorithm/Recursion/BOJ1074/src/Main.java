import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int r = sc.nextInt();
        int c = sc.nextInt();

        System.out.println(find(N, r, c));
    }

    private static int find(int n, int r, int c) {
        if(n == 0) return 0;

        int half =(int) Math.pow(2, n-1);
        if(r< half && c >= half) { // 2번 사각형
            return half*half + find(n-1, r, c-half);
        }
        else if(r>= half && c < half) { // 3번 사각형
            return 2*half*half + find(n-1, r-half, c);
        }
        else if(r >= half && c >= half){ // 4번 사각형
            return 3*half*half + find(n-1, r-half, c-half);
        }else{
            return find(n-1, r, c);
        }
    }
}