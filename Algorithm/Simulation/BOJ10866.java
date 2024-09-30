import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Deque<Integer> dq = new ArrayDeque<>();
        StringTokenizer st;
        for(int i=Integer.parseInt(br.readLine());i>0; i--){
            st = new StringTokenizer(br.readLine());
            String order = st.nextToken();
            if(order.equals("push_front")) dq.addFirst(Integer.parseInt(st.nextToken()));
            else if(order.equals("push_back")) dq.addLast(Integer.parseInt(st.nextToken()));
            else if(order.equals("pop_front")) bw.write((dq.isEmpty() == true?-1:dq.pollFirst())+"\n");
            else if(order.equals("pop_back")) bw.write((dq.isEmpty() == true?-1:dq.pollLast())+"\n");
            else if(order.equals("size")) bw.write(dq.size()+"\n");
            else if(order.equals("empty")) bw.write( (dq.isEmpty()==true?1:0)+"\n");
            else if(order.equals("front")) bw.write( (dq.isEmpty()==true?-1:dq.peekFirst())+"\n");
            else bw.write( (dq.isEmpty()==true?-1:dq.peekLast())+"\n");
        }
        bw.flush(); bw.close();
    }
}