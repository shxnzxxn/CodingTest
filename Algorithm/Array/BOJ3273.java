import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String[] input = sc.nextLine().split(" ");
        int x = sc.nextInt();
        int res = 0;
        int[] cnt = new int[2000001];
        for(int i=0; i<n; i++){
            int tmp = Integer.parseInt(input[i]);
            if(x-tmp >=0 && cnt[x-tmp] >= 1) res++;
            cnt[tmp]++;
        }
        System.out.println(res);
    }
}