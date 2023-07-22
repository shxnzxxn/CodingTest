import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        String[] num = String.valueOf(input).split("");
        int input_len = num.length;
        LinkedList<Integer> res = new LinkedList<>();
        // 백트래킹을 통해서 res에 가능한 모든 조합의 수를 등록함.
        backTracking(num, new String[input_len], new boolean[input_len], 0, 0, input_len, res);

        int min = 1000000;
        for(int i=1; i<res.size(); i++){ // 자기 자신은 제외
            int x = res.get(i);
            if(x > input && min > x) min = x;
        }

        if(min == 1000000) System.out.println(0);
        else System.out.println(min);
    }
    static void backTracking(String arr[], String[] out, boolean visited[], int depth,
                             int start, int r, LinkedList<Integer> res){
        if(depth == r){ // 재귀 메서드의 중단 조건
            StringBuilder sb = new StringBuilder(); // string을 이어 붙이기 위해 StringBuilder 사용
            for(String num : out) sb.append(num);
            res.add(Integer.parseInt(sb.toString()));
            return;
        }

        for(int i=0; i<arr.length; i++){
            if(!visited[i]){
                visited[i] = true;
                out[depth] = arr[i];
                backTracking(arr, out, visited,depth+1, start+1, r, res);
                visited[i] = false;
            }
        }

    }
}