package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[] num;
    static boolean[] visit;
    static int count = 0;
    static Map<Integer, List<int[]>> perfectNum = new LinkedHashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        num = new int[n];
        visit = new boolean[n];

        for(int i=0; i<n; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }

        choose2Number(n ,0, new int[2], 0, new int[2]);
        for(int i=0; i<num.length; i++){
            int j = num[i];
            List<int[]> ints = perfectNum.get(j);
            if(ints == null) continue;

            boolean isContain = true;
            for (int[] idx : ints) {
                if(idx[0] != i && idx[1] != i) {
                    isContain = false;
                    break;
                }
            }

            if(!isContain) count++;

        }

        System.out.println(count);
    }

    private static void choose2Number(int n, int x, int[] arr, int start, int[] idx) {
        if(x == arr.length){
            if(perfectNum.get(arr[0]+arr[1]) == null){
                LinkedList<int[]> ints = new LinkedList<>();
                ints.add(new int[]{idx[0], idx[1]});
                perfectNum.put(arr[0]+arr[1], ints);
            }else{
                List<int[]> ints = perfectNum.get(arr[0]+arr[1]);
                ints.add( new int[]{idx[0], idx[1]});
                perfectNum.put(arr[0]+arr[1], ints);
            }

            return;
        }

        for(int i=start; i<n; i++){
            arr[x] = num[i];
            idx[x] = i;
            choose2Number(n, x + 1, arr, i + 1, idx);
        }
    }

    // 그런데 이 방식보다 투 포인터 방식이 더 쩐다...
//    Arrays.sort(nums);  // 정렬
//    int res = 0;
//        for (int sumIdx = 0; sumIdx < N; sumIdx++) {
//        int left = 0; int right = N-1;  // 투포인터
//        while (true) {
//            if (left == sumIdx) left++;
//            else if (right == sumIdx) right --;
//
//            if (left >= right) break;
//
//            if (nums[left] + nums[right] > nums[sumIdx]) right--;
//            else if (nums[left] + nums[right] < nums[sumIdx]) left++;
//            else{
//                res++;
//                break;
//            }
//        }
//    }
}