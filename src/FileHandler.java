import java.io.*;

public class FileHandler {

    public static String readFile(String path) throws Exception {

        BufferedReader reader =
                new BufferedReader(new FileReader(path));

        StringBuilder data = new StringBuilder();

        String line;

        while ((line = reader.readLine()) != null) {
            data.append(line);
            data.append("\n");
        }

        reader.close();

        return data.toString();
    }

    public static void writeFile(String path,
                                 String content) throws Exception {

        BufferedWriter writer =
                new BufferedWriter(new FileWriter(path));

        writer.write(content);

        writer.close();
    }
}
