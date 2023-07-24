import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int[][] tree = new int[n][n];

        for(int i=0; i<n; i++){
            String[] input = sc.nextLine().split("");
            for (int j = 0; j < n; j++) tree[i][j] = Integer.parseInt(input[j]);
        }
        start(tree, 0, n-1, 0, n-1);

    }
    static void start(int[][] tree, int r_start, int r_end, int c_start, int c_end){
        if(r_start == r_end || same(tree, r_start, r_end, c_start, c_end)){
            int x = tree[r_start][c_start];
            if(x==0) System.out.print(0);
            else System.out.print(1);
            return;
        }
        int gap = (r_end - r_start+1)/2;
        System.out.print("(");
        start(tree, r_start, r_start+gap-1, c_start, c_start+gap-1);
        start(tree, r_start, r_start+gap-1, c_start+gap, c_end);
        start(tree, r_start+gap, r_end, c_start, c_start+gap-1);
        start(tree, r_start+gap, r_end, c_start+gap, c_end);
        System.out.print(")");
    }
    static boolean same(int[][] tree, int r_start, int r_end, int c_start, int c_end){
        int start = tree[r_start][c_start];
        for(int i=r_start; i<= r_end; i++){
            for(int j=c_start; j<=c_end; j++){
                if(start != tree[i][j]) return false;
            }
        }
        return true;
    }
}