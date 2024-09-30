import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine()); // 입력받을 횟수
        int[] res = new int[10000];
        for(int i=0; i<n; i++) {
            int x = Integer.parseInt(bf.readLine());
            res[x-1]++;         // 배열에서 입력받은 값의 인덱스의 출현 빈도를 증가
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i=0; i<res.length; i++){
            // 만약 0이 아닌 값이 들어오면, 해당 값만큼 인덱스를 반복하여 출력
            if(res[i] != 0) {bw.write(((i+1)+"\n").repeat(res[i]));}
        }
        bw.flush();
        bw.close();
    }
}