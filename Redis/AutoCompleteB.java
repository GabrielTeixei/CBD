import redis.clients.jedis.Jedis;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class AutoCompleteB {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        try {
            BufferedReader reader = new BufferedReader(new FileReader("nomes-pt-2021.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String name = parts[0];
                    int count = Integer.parseInt(parts[1]);
                    jedis.zadd("autocomplete", count, name);
                }
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
                Set<String> results = jedis.zrevrangeByScore("autocomplete", "+inf", "-inf", 0, 10);
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
