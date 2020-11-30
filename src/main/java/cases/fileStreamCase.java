package cases;

import java.io.*;
import java.util.List;

public class fileStreamCase {
    public static void write2File(String fileName, List<String> lines, String encoding) throws IOException {
        // 错误的文件名
        if (fileName.endsWith(File.separator)) {
            throw new IOException("file name:" + fileName + " is a dir, not a regular file");
        }

        File file = new File(fileName);
        if (!file.exists()) {
            file.getParentFile().mkdirs();// create parent dir
            file.createNewFile();
        }

        if (!file.isFile()) {
            throw new FileNotFoundException("target is not a regular file");
        }

        FileOutputStream out = new FileOutputStream(file);// 定义文件输出流
        PrintStream ps = new PrintStream(out, true, encoding);
        for (String line : lines) {
            ps.println(line);
        }
        ps.flush();
        ps.close();
        out.close();
    }
}
