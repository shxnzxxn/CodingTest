import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int iter = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int k=0; k<iter; k++){
            boolean error = false;
            String[] func = br.readLine().split("");
            int n = Integer.parseInt(br.readLine());
            String inputFunc = br.readLine();
            inputFunc = inputFunc.replace("[", "");
            inputFunc = inputFunc.replace("]", "");
            String[] list = inputFunc.split(",");
//
            int start = 0;
            int end = list.length-1;

            for(int i=0; i<func.length; i++){
                if(func[i].equals("R")){
                    int tmp = start;
                    start = end;
                    end = tmp;
                }else{
                    if(n == 0){
                        sb.append("error\n");
                        error = true;
                        break;
                    }else{
                        if(start > end) start--;
                        else start++;
                        n--;
                    }
                }
            }

            if(!error){
                sb.append("[");
                if(n != 0) {
                    if (start < end) {
                        for (int i = start; i <= end; i++) {
                            sb.append(list[i]);
                            if (i != end) sb.append(",");
                        }
                    } else {
                        for (int i = start; i >= end; i--) {
                            sb.append(list[i]);
                            if (i != end) sb.append(",");
                        }
                    }
                }
                sb.append("]\n");
            }
        }
        System.out.println(sb);
    }
}