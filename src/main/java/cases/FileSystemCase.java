package cases;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileSystemCase {

    public static void main(String[] args) {
        try {
            List<String> list =  Files.readAllLines(Paths.get("D:\\repository\\Cases\\java_note\\source\\123.txt"));
            List<String> com = new ArrayList<>();
            for(String s : list){
                com.add(s+",11");
            }
            Files.write(Paths.get("D:\\repository\\Cases\\java_note\\source\\+66-5.txt"), com);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
