import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static int m;
    static int n;
    static int k;
    static int[][] notebook;
    static LinkedList<int[][]> stickers = new LinkedList<>();
    static LinkedList<Integer> stickerAreas = new LinkedList<>();
    static int attachedArea = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        notebook = new int[m][n];
        for(int i=0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            int stickerX = Integer.parseInt(st.nextToken());
            int stickerY = Integer.parseInt(st.nextToken());

            int[][] sticker = new int[stickerX][stickerY];
            int stickerArea = 0;
            for(int j=0; j<stickerX; j++){
                st = new StringTokenizer(br.readLine());
                for (int l = 0; l < stickerY; l++) {
                    sticker[j][l] = Integer.parseInt(st.nextToken());
                    if(sticker[j][l] == 1) stickerArea++;
                }
            }
            stickerAreas.add(stickerArea);
            stickers.add(sticker);
        }

//        int[][] tmp = stickers.get(1);
//        for(int i=0; i<tmp.length; i++) System.out.println(Arrays.toString(tmp[i]));
//        int[][] ints = rotateSticker(tmp);
//        for(int i=0; i<ints.length; i++) System.out.println(Arrays.toString(ints[i]));
//        System.exit(0);

        for(int i = 0; i< stickers.size(); i++){
            int[][] sticker = stickers.get(i).clone();
            int cnt = 1;
            while(!putOn(sticker, i)){
                cnt++;
                if(cnt > 4) break;
                sticker = rotateSticker(sticker);
            }
        }
        System.out.println(attachedArea);
    }

    private static int[][] rotateSticker(int[][] sticker) {
        int[][] rotateSticker = new int[sticker[0].length][sticker.length];
        for(int i=0; i<sticker[0].length; i++){
            for(int j1=sticker.length-1, j2=0; j1>=0; j1--, j2++){
                rotateSticker[i][j2] =  sticker[j1][i];
            }
        }
        return rotateSticker;
    }

    private static boolean putOn(int[][] sticker, int idx) { // idx의 면저

        for(int i=0; i<= m-sticker.length; i++){
            for(int j=0; j<= n-sticker[0].length; j++){
                if(canPut(sticker, i, j)){ // 만약 노트북의 해당 위치에 스티커를 놓을 수 있다면,
                    for(int a=0; a<sticker.length; a++){
                        for(int b=0; b<sticker[a].length; b++)
                            if(sticker[a][b] == 1) {
                                notebook[i + a][j + b] = sticker[a][b];
                            }
                    }
                    attachedArea += stickerAreas.get(idx);
                    return true; // 스티커를 붙였으면 함수 탈출
                }
            }
        }
        return false;
    }

    private static boolean canPut(int[][] sticker, int x, int y) { // sticker를 notebook의 왼쪽 시작이 x, y에 놓을 수 있는 지
        for(int i=0; i<sticker.length; i++){
            for(int j=0; j<sticker[i].length; j++){
                // 이미 노트북에 스티커가 붙은 자리에, 스티커를 붙이려고 할 때
                if(sticker[i][j] == 1 && notebook[x+i][y+j]==1) return false;
            }
        }
        return true;
    }
}