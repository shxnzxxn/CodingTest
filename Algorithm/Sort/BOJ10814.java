import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
//        ArrayList<Integer> age_arr = new ArrayList<>(n);
//        ArrayList<String> name_arr = new ArrayList<>(n);
//        for(int i=0; i<n; i++){
//            st = new StringTokenizer(br.readLine());
//            int age = Integer.parseInt(st.nextToken());
//            String name = st.nextToken();
//
//            int start = age_arr.indexOf(age);
//            int input = age_arr.size();
//            start = start==-1 ? 0: start;
//            for(int j = start; j<age_arr.size(); j++){
//                if(age < age_arr.get(j)){
//                    input = j;
//                    break;
//                }
//            }
//            age_arr.add(input, age);
//            name_arr.add(input, name);
//        }
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        for (int i = 0; i < n; i++) bw.write(age_arr.get(i) + " " + name_arr.get(i) + "\n");
//        bw.flush();
//        bw.close();
        ArrayList<String>[] res = new ArrayList[200];
        for(int i=0; i<res.length; i++) res[i] = new ArrayList<>();
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            res[idx-1].add(name);
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i=0; i<res.length; i++){
            if (res[i] != null) {
                for (int j = 0; j < res[i].size(); j++) bw.write((i+1) + " " + res[i].get(j) + "\n");
            }
        }
        bw.flush();
        bw.close();
    }
}