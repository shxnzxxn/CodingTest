import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<String> input = new ArrayList<>();
        ArrayList<String> res = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            if (!input.contains(tmp)) input.add(tmp);
        }
        Collections.sort(input);

        res.add(input.get(0));
        for (int i = 1; i < input.size(); i++) {
            String obj = input.get(i);
            if(obj.length() < res.get(res.size()-1).length()) {
                for (int j = 0; j < res.size(); j++) {
                    if (obj.length() < res.get(j).length()){
                        res.add(j, obj);
                        break;
                    }
                }
            }else{
                res.add(obj);
            }
        }

        for(int i=0; i<res.size(); i++){
            System.out.println(res.get(i));
        }

    }
}