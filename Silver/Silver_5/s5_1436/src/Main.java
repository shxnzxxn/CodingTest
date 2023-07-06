import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        // 1. 개수를 체크하고 있는 변수와 num이 같을 때 종료.
        // 2. 0부터 하나씩 늘려가면서, 종말의 수를 만들어 줄 것이다.
        // 3. 우선 늘려가는 변수를 idx라고 한다면 이 수를 x앞에 붙여준다.
        // 4. 그리고 해당 수에서 연속된 6이 나오기 시작한 부분까지를 제외하고 남은 수를 뒤에 채워준다.
        // 6. 그 후에, 다시 idx를 늘려가면서 카운트하자.
        int cnt = 0;
        String res = "";
        for(int i=0; cnt < num; i++){ // 1, 2단계
            String x = String.valueOf(Integer.parseInt(i + "666")); // 3단계
            int start;
            for(start=0; start<x.length()-2; start++){if(x.substring(start, start+3).equals("666")) break;}
            int back_fill = start+3;
            int fill_n = (int) Math.pow(10, x.length()-back_fill);
            if(fill_n >1) {
                for (int j = 0; j < fill_n && cnt < num; j++) {
                    int a = j == 0 ? 1 : (int) Math.log10(j) + 1;
                    res = x.substring(0, back_fill) + "0".repeat(x.length()-back_fill-a) + j;
                    cnt++;
                }
            }else {
                cnt++;
                res = x;
            }
        }
        System.out.println(Integer.parseInt(res));

    }
}