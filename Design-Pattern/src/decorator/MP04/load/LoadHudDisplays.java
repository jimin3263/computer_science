package decorator.MP04.load;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//list 반환하는 함수
public class LoadHudDisplays implements LoadDisplayInterface{

    private String filename;
    public LoadHudDisplays(String filename) {
        this.filename = filename;
    }

    @Override
    public ArrayList<String> load() {
        ArrayList<String> alist = new ArrayList<String>();
        try{
            FileReader fr = new FileReader(filename); //파일자체 읽고
            BufferedReader br = new BufferedReader(fr); //버퍼로 가져옴

            String readLine = br.readLine(); //한줄씩 읽음
            while (readLine != null){//읽었는데 null이 아니면 추가
                alist.add(readLine);
                readLine = br.readLine(); // 다시 읽음
            }

        }
        catch (FileNotFoundException e){
            System.out.println(filename + "does not exist.");
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return alist;
    }
}
