package mashapelucas;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Administrator
 */
public class IpInfo {

    public static void main(String[] args) throws UnirestException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite o IP: ");
        String ip = scan.nextLine();

        HttpResponse<JsonNode> response = Unirest.post("https://community-neutrino-ip-info.p.mashape.com/ip-info")
                .header("X-Mashape-Key", "qYW0rfQcBRmshAgPIxuJUPrVfgT1p19FOIkjsnruP8KNZmqfdj")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Accept", "application/json")
                .field("ip", ip)
                .field("reverse-lookup", false)
                .asJson();

        JSONArray array = response.getBody().getArray();

        for (int i = 0; i < array.length(); i++) {
            JSONObject match = array.getJSONObject(i);

            String country = match.getString("country");
            String countrycode = match.getString("country-code");
            String city = match.getString("city");
            String region = match.getString("region");
            System.out.printf("Resultado: " + "\n" + "País: %s" + "\nCódido do País: %s" + "\nRegião: %s" + "\nCidade: %s",
                    country, countrycode, region, city);

        }
    }

}
