import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        long[] N = new long[n];
        for (int i = 0; i < n; i++) N[i] = Long.parseLong(st.nextToken());

        Arrays.sort(N);     // Arrays.sort를 이용한 N 정렬

        int m = Integer.parseInt(br.readLine());
        StringTokenizer judge = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < m; i++) {
            long M = Long.parseLong(judge.nextToken());
            int res = 0;
            // 이진탐색
            int start = 0, end = n-1;
            while(start<=end){
                int mid = (start+end)/2;
                if(N[mid] > M) end = mid-1;
                else if(N[mid] < M) start = mid+1;
                else {res=1; break;}
            }

            bw.write(res+"\n"); // Buffer를 이용했으므로, 스트림에 넣어두기
        }
        bw.flush();             // 마지막에 출력
        bw.close();
    }
}