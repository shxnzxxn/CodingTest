import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int minuscnt = 0;
    static int zerocnt = 0;
    static int onecnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] paper = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) paper[i][j] = Integer.parseInt(st.nextToken());
        }

        count(paper, 0, paper.length-1, 0, paper[0].length-1);
        System.out.println(minuscnt);
        System.out.println(zerocnt);
        System.out.println(onecnt);
    }

    static void count(int[][] paper, int r_start, int r_end, int c_start, int c_end){
        if((r_start == r_end) || isCount(paper, r_start, r_end, c_start, c_end)){
            int x = paper[r_start][c_start];
            if(x == -1) minuscnt++;
            else if(x == 0) zerocnt++;
            else onecnt++;
            return;
        }
        int len = (r_end - r_start+1)/3;
        for(int i=r_start; i<=r_end; i+=len){ // paper에 같은 숫자가 들어가지 않은 친구들! 쪼개서 다시 확인해야함.
            for(int j=c_start; j<=c_end; j+=len){
                count(paper, i, i+len-1, j, j+len-1);
            }
        }
    }
    static boolean isCount(int[][] paper, int r_start, int r_end, int c_start, int c_end){
        int start = paper[r_start][c_start];
        for(int i=r_start; i<= r_end; i++){
            for(int j=c_start; j<=c_end; j++){
                if(start != paper[i][j]) return false;
            }
        }
        return true;
    }
}