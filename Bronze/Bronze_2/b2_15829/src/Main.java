import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String input = br.readLine();
        long res = 0;
        long r = 1;
        long M = 1234567891;
        for(int i=0; i<n; i++){
            res+= ((input.charAt(i)-96)*r)%M;
            r = (r*31)%M;
            if(res >= M) res%=M;
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(res));
        bw.flush();
        bw.close();
    }
}