
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

class message{
    public static String readFile(String path, Charset encoding) throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
    public static String content;

    public static String content() {
        String filePath = "message1.txt";

        String content = null;
        try {
            content = readFile(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public static String picture() {
        String filePath = "picture.txt";

        String picture = null;
        try {
            picture = readFile(filePath, StandardCharsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return picture;
    }
    /*public static String citata() {
        ArrayList<String> list = new ArrayList<>();
        try (Scanner scan = new Scanner(new File("citaty.txt"))) {
            while (scan.hasNextLine()) {
                list.add(scan.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        double n = Math.random()*30-1;
        System.out.println(n);
        String[] array = list.toArray(new String[(int) n]);

String citata = array[(int) n];

        return citata;
    }
*/
}