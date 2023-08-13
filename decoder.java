import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Base64;
import java.util.Scanner;
import java.util.ArrayList;

public class decoder {
    public static String b64decode(String encoded) {
        byte[] decodedBytes = Base64.getDecoder().decode(encoded.getBytes());
        return new String(decodedBytes);
    }

    public static void main(String[] args ){
        try (Scanner input = new Scanner(System.in)) {
            System.out.printf("Key: ");
            String key1 = input.nextLine();
            System.out.printf("Key: ");
            String key2 = input.nextLine();
            System.out.printf("File Name: "); // --->V
            String file = input.nextLine(); // subsitute for open file
            try (FileReader f = new FileReader(file)) {
                BufferedReader r = new BufferedReader(f);
                String line;
                ArrayList<String> l = new ArrayList<>();
                while ((line = r.readLine()) != null) {
                    l.add(line);
                }
                String content = String.valueOf(l);
                String firstDecode = content.split(key1)[1];
                String encoded = firstDecode.split(key2)[0];
                System.out.println("Decoded: " + b64decode(encoded));
            } catch (Exception e) {
                System.out.println("An Error occured.");
                e.printStackTrace();
            }

        }
    }
}