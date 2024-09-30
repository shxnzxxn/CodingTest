import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    //  ArrayList를 Collections.sort()를 이용하여 푼 방법
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int n = Integer.parseInt(br.readLine());
//        ArrayList<Integer> input = new ArrayList<>();
//        for (int i = 0; i < n; i++) input.add(Integer.parseInt(br.readLine()));
//        Collections.sort(input);
//
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        for(int i=0; i<input.size(); i++) bw.write(input.get(i)+"\n");
//        bw.flush();
//        bw.close();
//    }
    // 정렬 알고리즘을 직접 구현하기
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] input = new int[n];
        for (int i = 0; i < n; i++) input[i] = Integer.parseInt(br.readLine());

        // 가장 빠른 퀵 정렬
        sort(input, 0, input.length-1);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i=0; i<input.length; i++) bw.write(input[i]+"\n");
        bw.flush();
        bw.close();
    }

    public static void sort(int[] a, int low, int mid, int high) {
        if(a[low]>a[mid]) swap(a, low, mid);
        if(a[mid]>a[high]) swap(a, mid, high);
        if(a[low]>a[mid]) swap(a, low, mid);
    }
    public static int partition(int[] a, int pivot, int high){
        int i = pivot;
        int j = high+1;
        int p =a[pivot];
        while(true){
            while(a[++i] < p) if(i == high) break;
            while(a[--j] > p) if(j == pivot) break;
            if(i >= j) break;
            swap(a, i, j);
        }
        swap(a, pivot, j);
        return j;
    }
    public static void swap(int[] a, int i, int j){
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }


}