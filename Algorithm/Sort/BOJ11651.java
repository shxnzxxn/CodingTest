import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    static int n;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n][2];

        for(int i=0; i<n; i++){
            String[] x = br.readLine().split(" ");
            arr[i][0] = Integer.parseInt(x[0]);
            arr[i][1] = Integer.parseInt(x[1]);
        }

        List<int[]> list = Arrays.stream(arr).sorted(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) {
                    if (o1[0] > o2[0]) {
                        return 1;
                    } else if (o1[0] == o2[0]) {
                        return 0;
                    } else {
                        return -1;
                    }
                }

                if (o1[1] > o2[1]) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }).collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();


        for(int i=0; i<list.size(); i++){
            sb.append(list.get(i)[0] + " " + list.get(i)[1]+"\n");
        }

        System.out.println(sb);
    }


}