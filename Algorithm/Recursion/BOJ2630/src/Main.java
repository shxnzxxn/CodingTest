import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int whitecnt = 0;
    static int bluecnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[][] paper = new int[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) paper[i][j] = Integer.parseInt(st.nextToken());
        }

        count(paper, 0, n-1, 0, n-1);
        System.out.println(whitecnt);
        System.out.println(bluecnt);
    }
    static void count(int[][] paper, int r_start, int r_end, int c_start, int c_end){
        if(r_start == r_end || same(paper, r_start, r_end, c_start, c_end)){
            int x = paper[r_start][c_start];
            if(x == 0) whitecnt++;
            else bluecnt++;
            return;
        }
        int gap = (r_end - r_start+1)/2;
        count(paper, r_start, r_start+gap-1, c_start, c_start+gap-1);
        count(paper, r_start, r_start+gap-1, c_start+gap, c_end);
        count(paper, r_start+gap, r_end, c_start, c_start+gap-1);
        count(paper, r_start+gap, r_end, c_start+gap, c_end);
    }

    static boolean same(int[][] paper, int r_start, int r_end, int c_start, int c_end){
        int start = paper[r_start][c_start];
        for(int i=r_start; i<= r_end; i++){
            for(int j=c_start; j<=c_end; j++){
                if(start != paper[i][j]) return false;
            }
        }
        return true;
    }
}