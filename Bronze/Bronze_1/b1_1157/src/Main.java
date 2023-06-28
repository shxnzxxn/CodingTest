import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split("");
        List<String> strList = new ArrayList<>(Arrays.asList(input));
        String[] a = new String[input.length];
        int[] d = new int[input.length];
        int idx = 0;
        for(int i=0; i<input.length; i++){
            String x = input[i].toUpperCase();
            if(Arrays.asList(a).contains(x)){
                d[Arrays.asList(a).indexOf(x)]++;
            }else{
                a[idx] = x;
                d[idx++]=1;
            }
        }
        int real_len = 0;
        for(int i=0; i<input.length; i++)if(d[i] == 0) real_len = i;
        int[] copy_d = new int[real_len];
        for(int i=0; i<real_len; i++) copy_d[i] = d[i];
        Arrays.sort(d);
        int max = d[real_len];
        if(real_len<2) System.out.println(a[0]);
        else if(d[real_len] == d[real_len-1]) System.out.println("?");
        else{
            for(int i=0; i<real_len; i++){
                if(max == copy_d[i]){
                    System.out.println(a[i]);
                    break;
                }
            }
        }

    }
}