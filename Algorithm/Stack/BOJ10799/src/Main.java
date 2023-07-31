import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split("");
        int cnt = 0;
        int res = 0;
        for(int i=0; i<input.length; i++){
            if(input[i].equals("(")) {
                cnt++;
            }
            else if(input[i].equals(")")) {
                if(input[i-1].equals("(")){ // 레이져일 때
                    cnt--;
                    res += cnt;
                }else{ // 막대기가 끝나는 것일 떄
                    res++;
                    cnt--;
                }
            }
        }
        System.out.println(res);
    }
}