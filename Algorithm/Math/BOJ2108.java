import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer> input = new ArrayList<>();
        int[] count = new int[8001];
        int[] count_copy = new int[8001];
        double mean = 0;
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());
            input.add(x);
            count[x+4000]++;
            count_copy[x+4000]++;
            mean += (double)x/n;
        }
        System.out.println(Math.round(mean));
        Collections.sort(input);
        int mid_idx = (int)Math.floor(input.size()/2);
        System.out.println(input.get(mid_idx));
        Arrays.sort(count);
        if(count[count.length-1] == count[count.length-2]){ // 최빈값이 여러개면
            ArrayList<Integer> most = new ArrayList<>();
            int big = count[count.length-1];
            for(int i=0; i<count.length; i++){
                if(count_copy[i] == big) most.add(i-4000);
            }
            Collections.sort(most);
            System.out.println(most.get(1));
        }else{
            int big = count[count.length-1];
            for(int i=0; i<count.length; i++){
                if(count_copy[i] == big) {
                    System.out.println(i-4000);
                    break;
                }
            }
        }
        int range = input.get(input.size()-1) - input.get(0);
        System.out.println(range);


    }
}