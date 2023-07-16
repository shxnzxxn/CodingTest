import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int start = sc.nextInt();
        int end = sc.nextInt();
        LinkedList<Integer>[] obj = new LinkedList[100001];
        int[] distance = new int[100001];
        for(int i=0; i<obj.length; i++){
            obj[i] = new LinkedList<>();
            if(i!=0 && i*2 <= obj.length) obj[i].add(i*2);
            if(i != 0) obj[i].add(i-1);
            if(i != obj.length-1) obj[i].add(i+1);
            distance[i] = -1;
        }
        LinkedList<Integer> q = new LinkedList<>();
        q.add(start);
        distance[start] = 0;
        while(!q.isEmpty()){
            int s = q.poll();
            ListIterator<Integer> it = obj[s].listIterator();
            while(it.hasNext()){
                int n = it.next();
                if(distance[n] == -1){
                    q.add(n);
                    distance[n] = distance[s]+1;
                }
                else if(distance[n] > distance[s]+1) distance[n] = distance[s]+1;
            }
        }
        System.out.println(distance[end]);
    }
}