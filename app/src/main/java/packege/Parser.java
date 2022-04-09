package packege;

import android.text.Html;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static final String PATTERN = "\\{m}[\\d,.]{1,4}";
    private ArrayList<String> arrayData;
    private String htmlCode;
    private final String url = ("http://192.168.209.111/?m=1");


    public Parser() {
//        try {
//            this.htmlCode = readPageFromUrl(this.url);
//                     readPageFromUrl(this.url);
//                    "{t}{s}SI7021 Температура{m}28,9 °C{e}{s}SI7021 Влажность{m}30,5 %{e}{s}SI7021 Dew point{m}9,8 °C{e}</table>{t}<tr><td style='width:100%;text-align:center;font-weight:normal;font-size:62px'>OFF</td></tr><tr></tr></table>\n";
//            this.updateData();
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public void updateData() throws IOException, InterruptedException {
        ArrayList<String> resArray = new ArrayList<String>();
        Pattern pattern = Pattern.compile("\\{m\\}[\\d,.]{1,4}");
        Matcher matcher = pattern.matcher(readPageFromUrl(url));
        if (!matcher.find()) return;
        do {
            resArray.add(matcher.group().substring(3));
        } while (matcher.find());
        arrayData = resArray;
    }

    public ArrayList getArrayData() {
        if(arrayData == null) {
            try {
                updateData();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        return arrayData;
    }

    public String getTemperature() {
        return arrayData.get(0);
    }

    public String getHumidity() {
        return arrayData.get(1);
    }

    public String getDewPoint() {
        return arrayData.get(2);
    }


    public static String readPageFromUrl(String strURL) throws IOException, InterruptedException {
        URL pURL = new URL(strURL);

        URLConnection urlCon = (HttpURLConnection) pURL.openConnection();
        urlCon.setConnectTimeout(30000000);
        urlCon.setReadTimeout(30000000);
        urlCon.setRequestProperty("User-Agent", "Mozilla");

        BufferedReader in = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
        StringBuilder result = new StringBuilder();
        String readLine;
        readLine = in.readLine();
        while (readLine != null) {
            result.append(readLine);

            readLine = in.readLine();
        }
        in.close();
        return result.toString();
    }
}
