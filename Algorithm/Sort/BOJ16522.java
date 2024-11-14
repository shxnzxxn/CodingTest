import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int n;
    static long[] arr;
    static int cnt;
    static long maxVal;

    // -2^(62) ~ 2^(62)의 숫자가 카드에 쓰여있음.
    // n <= 100,000
    // 가장 많이 나온 숫자 return.
    // 가장 많이 나온 숫자가 여러개라면 그 중 가장 작은 수 return
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new long[n];

        for(int i=0; i<n; i++){
            long tmp = Long.parseLong(br.readLine());
            arr[i] = tmp;
        }

        Arrays.sort(arr);

        cnt = 1;
        int cntNew = 0;
        long maxValNew = 0;
        boolean isMaxValueMatched = false;
        maxVal = arr[0];
        for(int i=1; i<n; i++){
            if(maxVal == arr[i]){
                cnt++;
            }else{
                if(!isMaxValueMatched){
                    // 처음이면
                    maxValNew = arr[i];
                    cntNew++;
                    isMaxValueMatched = true;
                }else if(arr[i-1] == arr[i]){
                    // 처음이 아니고, 이전값과 같은 애라면
                    cntNew++;
                }else{
                    // 처음이 아니고, 이전과 다른 값이라면
                    cntNew = 1;
                    maxValNew = arr[i];
                }
            }

            if(cntNew > cnt){
                maxVal = arr[i];
                cnt = cntNew;
                cntNew = 0;
            }
        }

        System.out.println(maxVal);
    }
}