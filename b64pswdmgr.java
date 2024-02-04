import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;

public class b64pswdmgr{

    private static int KEY_MIN_LENGTH = 16;
    private static int KEY_MAX_LENGTH = 64;

    public static SecureRandom random = new SecureRandom();
    
    public static StringBuilder getRandomString() {
	int randInt = random.nextInt( (KEY_MAX_LENGTH - KEY_MIN_LENGTH) + 1)
		+ KEY_MIN_LENGTH;

        String an = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randString = new StringBuilder();
        for (int i = 0; i < randInt; i++) {
            randString.append(an.charAt(random.nextInt(an.length())));  
        }
        return randString;
    }

    public static String generateFileName() {
        int randInt = random.nextInt(12);
        String an = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randString = new StringBuilder();
        for (int i = 0; i < randInt; i++) {
            randString.append(an.charAt(random.nextInt(an.length())));  
        }
        return "output-" + randString + ".txt";
    }

    public static String b64encode(String decoded) {
        return Base64.getEncoder().encodeToString(decoded.getBytes());
    }

    public static String grabInfo() {
        try (Scanner scanmain = new Scanner(System.in)) {
            System.out.printf("Passwords, emails, etc: ");
            String decoded = scanmain.nextLine();
            return b64encode(decoded).replace("=", "");
        }
    }
    
    public static void main(String[] args) {
        int randInt = random.nextInt(1000);
        String filename = generateFileName();
        try (FileWriter f = new FileWriter(filename)) {
            System.out.println("File output saved as " + filename);
            for (int i = 0; i < 999; i++) {
                if (i == randInt) {
                    String key = getRandomString().toString();
                    f.write(key);
                    f.write(grabInfo());
                    String endKey = getRandomString().toString();
                    f.write(endKey);
                    System.out.println(String.format("Your First Key: %s\nYour second Key: %s", key, endKey));
                } else {
                    f.write(getRandomString().toString());
                }
            }
        } catch (IOException e) {
            System.out.println("An Error occured.");
            e.printStackTrace();
        }
    }
}