package sample.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.JSONObject;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField city;

    @FXML
    private Button getData;

    @FXML
    private Text temp;

    @FXML
    private Text tempMax;

    @FXML
    private Text tempMin;

    @FXML
    private Text wind;

    @FXML
    private Text wlazh;

    @FXML
    void initialize() {
        getData.setOnAction(event -> {
            // Получаем данные из текстового поля
            String getUserCity = city.getText().trim();
            if(!getUserCity.equals("")) { // Если данные не пустые
                // Получаем данные о погоде с сайта openweathermap
                String output = getUrlContent("https://api.openweathermap.org/data/2.5/weather?q=" + getUserCity + "&appid=7c5a598d73eafb607007c004aa7e5209&units=metric");

                if (!output.isEmpty()) { // Нет ошибки и такой город есть
                    JSONObject obj = new JSONObject(output);
                    temp.setText("Температура: " + obj.getJSONObject("main").getDouble("temp"));
                    tempMax.setText("Максимум: " + obj.getJSONObject("main").getDouble("temp_max"));
                    tempMin.setText("Минимум: " + obj.getJSONObject("main").getDouble("temp_min"));
                    wind.setText("Ветер: " + obj.getJSONObject("wind").getDouble("speed"));
                    wlazh.setText("Влажность: " + obj.getJSONObject("main").getDouble("humidity"));
                }
            }
        });
    }

    // Обработка URL адреса и получение данных с него
    private static String getUrlContent(String urlAdress) {
        StringBuffer content = new StringBuffer();

        try {
            URL url = new URL(urlAdress);
            URLConnection urlConn = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch(Exception e) {
            System.out.println("Такой город был не найден!");
        }
        return content.toString();
    }

}
