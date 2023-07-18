import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        String[] num = String.valueOf(input).split("");
        int input_len = num.length;
        LinkedList<Integer> res = new LinkedList<>();
        backTracking(num, new String[input_len], new boolean[input_len], 0, 0, input_len, res);

        int min = 1000000;
        for(int i=1; i<res.size(); i++){ // 자기 자신은 제외
            int x = res.get(i);
            if(x > input && min > x) min = x;
        }

        if(min == 1000000) System.out.println(0);
        else System.out.println(min);
    }
    static void backTracking(String arr[], String[] out, boolean check[], int depth, int start, int r, LinkedList<Integer> res){
        if(depth == r){
            StringBuilder sb = new StringBuilder();
            for(String num : out) sb.append(num);
            res.add(Integer.parseInt(sb.toString()));
            return;
        }

        for(int i=0; i<arr.length; i++){
            if(!check[i]){
                check[i] = true;
                out[depth] = arr[i];
                backTracking(arr,out,check,depth+1, start+1, r, res);
                check[i] = false;
            }
        }

    }
}