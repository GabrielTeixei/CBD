import redis.clients.jedis.Jedis;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class AutoCompleteA {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        try {
            BufferedReader reader = new BufferedReader(new FileReader("names.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                jedis.zadd("autocomplete", 0, line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.print("Search for ('Enter' for quit): ");
                String prefix = br.readLine();
                if (prefix.isEmpty()) {
                    break;
                }
                Set<String> results = jedis.zrangeByLex("autocomplete", "[" + prefix, "[" + prefix + "\xff");
                for (String result : results) {
                    System.out.println(result);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }
}
