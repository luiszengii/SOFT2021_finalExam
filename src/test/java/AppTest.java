import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.Silent.class)
public class AppTest {

    private Application application;
    private DataManager realDataManager = new DataManager();
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    DummyInputAPIHandler mockDummyInputAPI;
    @Mock
    RealInputAPIHandler mockRealInputAPI;
    @Mock
    DummyOutputAPIHandler mockDummyOutputAPI;
    @Mock
    RealOutputAPIHandler mockRealOutputAPI;
    @Mock
    DataManager mockDataManager;
    @Captor
    ArgumentCaptor<String> stringCaptor;

    @Before
    public void setUp(){
        System.setOut(new PrintStream(outputStreamCaptor));
        realDataManager.cleanDB();
    }

    @After
    public void tearDown() {
        System.setOut(standardOut);
        realDataManager.cleanDB();
    }

    @Test
    public void DummyAPIGetCurrentWeatherDataTest() {
        String expectedJsonString = "{\"sunrise\":\"09: 56\"," +
                "\"pod\":\"d\",\"pres\":1016.7,\"timezone\":\"America/New_York\",\"ob_time\":\"2021-06-04 11:36\"," +
                "\"wind_cdir\":\"SE\",\"lon\":-77.3781,\"clouds\":21,\"wind_spd\":0.45,\"city_name\":\"Country Club Acres\"," +
                "\"h_angle\":-67.5,\"datetime\":\"2021-06-04:11\",\"precip\":0,\"weather\":{\"code\":801,\"icon\":\"c02d\",\"description\":\"Few clouds\"}," +
                "\"station\":\"E5945\",\"elev_angle\":11.01,\"dni\":487.4,\"lat\":34.774,\"vis\":5,\"uv\":0.932543,\"temp\":21.7,\"dhi\":54.31," +
                "\"ghi\":139.36,\"app_temp\":22.5,\"dewpt\":21.7,\"wind_dir\":135,\"solar_rad\":138.7,\"country_code\":\"US\",\"rh\":100," +
                "\"slp\":1017.5,\"snow\":0,\"sunset\":\"00:20\",\"aqi\":26,\"state_code\":\"NC\",\"wind_cdir_full\":\"southeast\",\"ts\":1622806560}";

        application = new Application("offline", "online", 0); //the input api would be a dummy one
        application.setInputApi(mockDummyInputAPI); //setting the dummy api a mock one

        when(application.getCurrentWeatherData("28546", "fb6d55b649db4857b569c84977bad72b", false)).thenReturn(expectedJsonString);
        application.getCurrentWeatherData("28546", "fb6d55b649db4857b569c84977bad72b", false);
        verify(mockDummyInputAPI).getCurrentWeatherData("28546", "fb6d55b649db4857b569c84977bad72b");
    }

    @Test
    public void DummyAPIGetCurrentWeatherDataWithAlertTest(){
        String expectedJsonString = "{\"sunrise\":\"09: 56\"," +
                "\"pod\":\"d\",\"pres\":1016.7,\"timezone\":\"America/New_York\",\"ob_time\":\"2021-06-04 11:36\"," +
                "\"wind_cdir\":\"SE\",\"lon\":-77.3781,\"clouds\":21,\"wind_spd\":0.45,\"city_name\":\"Country Club Acres\"," +
                "\"h_angle\":-67.5,\"datetime\":\"2021-06-04:11\",\"precip\":0,\"weather\":{\"code\":801,\"icon\":\"c02d\",\"description\":\"Few clouds\"}," +
                "\"station\":\"E5945\",\"elev_angle\":11.01,\"dni\":487.4,\"lat\":34.774,\"vis\":5,\"uv\":0.932543,\"temp\":21.7,\"dhi\":54.31," +
                "\"ghi\":139.36,\"app_temp\":22.5,\"dewpt\":21.7,\"wind_dir\":135,\"solar_rad\":138.7,\"country_code\":\"US\",\"rh\":100," +
                "\"slp\":1017.5,\"snow\":0,\"sunset\":\"00:20\",\"aqi\":26,\"state_code\":\"NC\"," +
                "\"wind_cdir_full\":\"southeast\",\"ts\":1622806560," +
                "\"title\": \"Flood Warning issued February 8 at 8:51PM CST expiring February 12 at 8:24AM CST by NWS St Louis MO\",\n" +
                "      \"description\": \"Mississippi River at Chester\\n\\n.This Flood Warning is a result of 1-2 inches of rainfall across\\nthe basin earlier this week...\\nThe Flood Warning continues for\\nthe Mississippi River at Chester\\n* until Tuesday morning.\\n* At  8:30 PM Friday the stage was 26.8 feet.\\n* Flood stage is 27.0 feet.\\n* Minor flooding is forecast.\\n* The river is forecast to rise above flood stage by tonight and to\\ncrest near 29.0 feet by Saturday evening. The river is forecast to\\nfall below flood stage by Monday morning.\\n* Impact:  At 28.0 feet...Unleveed islands near Chester and the prison\\nfarm floods.\\n* Impact:  At 27.0 feet...Flood Stage.  Unprotected farmland on right\\nbank begins to flood.\",\n" +
                "      \"severity\": \"Warning\",\n" +
                "      \"effective_utc\": \"2019-02-09T02:51:00\",\n" +
                "      \"effective_local\": \"2019-02-08T21:51:00\",\n" +
                "      \"expires_utc\": \"2019-02-10T02:51:00\",\n" +
                "      \"expires_local\": \"2019-02-09T21:51:00\",\n" +
                "      \"uri\": \"https://api.weather.gov/alerts/NWS-IDP-PROD-3361975-2942026\",\n" +
                "      \"alerts\": [\n" +
                "        \"St Louis, MO\"\n" +
                "      ]\n" +
                "    }";

        application = new Application("offline", "online", 0); //the input api would be a dummy one
        application.setInputApi(mockDummyInputAPI); //setting the dummy api a mock one

        when(application.getCurrentWeatherData("28547","fb6d55b649db4857b569c84977bad72b", false)).thenReturn(expectedJsonString);
        application.getCurrentWeatherData("28547","fb6d55b649db4857b569c84977bad72b", false);
        verify(mockDummyInputAPI).getCurrentWeatherData("28547","fb6d55b649db4857b569c84977bad72b");
    }

    @Test
    public void RealAPIGetCurrentWeatherDataTest(){
        String expectedJsonString = "{\"sunrise\":\"09: 56\"," +
                "\"pod\":\"d\",\"pres\":1016.7,\"timezone\":\"America/New_York\",\"ob_time\":\"2021-06-04 11:36\"," +
                "\"wind_cdir\":\"SE\",\"lon\":-77.3781,\"clouds\":21,\"wind_spd\":0.45,\"city_name\":\"Country Club Acres\"," +
                "\"h_angle\":-67.5,\"datetime\":\"2021-06-04:11\",\"precip\":0,\"weather\":{\"code\":801,\"icon\":\"c02d\",\"description\":\"Few clouds\"}," +
                "\"station\":\"E5945\",\"elev_angle\":11.01,\"dni\":487.4,\"lat\":34.774,\"vis\":5,\"uv\":0.932543,\"temp\":21.7,\"dhi\":54.31," +
                "\"ghi\":139.36,\"app_temp\":22.5,\"dewpt\":21.7,\"wind_dir\":135,\"solar_rad\":138.7,\"country_code\":\"US\",\"rh\":100," +
                "\"slp\":1017.5,\"snow\":0,\"sunset\":\"00:20\",\"aqi\":26,\"state_code\":\"NC\",\"wind_cdir_full\":\"southeast\",\"ts\":1622806560}";

        JSONObject map = (JSONObject) JSONValue.parse(expectedJsonString);

        application = new Application("online", "online", 0); //the input api would be a dummy one
        application.setInputApi(mockRealInputAPI); //setting the dummy api a mock one

        when(application.getCurrentWeatherData("28546","fb6d55b649db4857b569c84977bad72b", false)).thenReturn(expectedJsonString);
        application.getCurrentWeatherData("28546","fb6d55b649db4857b569c84977bad72b", false);
        verify(mockRealInputAPI).getCurrentWeatherData("28546","fb6d55b649db4857b569c84977bad72b");
    }

    @Test
    public void RealAPIGetCurrentWeatherDataWithAlertTest(){
        String expectedJsonString = "{\"sunrise\":\"09: 56\"," +
                "\"pod\":\"d\",\"pres\":1016.7,\"timezone\":\"America/New_York\",\"ob_time\":\"2021-06-04 11:36\"," +
                "\"wind_cdir\":\"SE\",\"lon\":-77.3781,\"clouds\":21,\"wind_spd\":0.45,\"city_name\":\"Country Club Acres\"," +
                "\"h_angle\":-67.5,\"datetime\":\"2021-06-04:11\",\"precip\":0,\"weather\":{\"code\":801,\"icon\":\"c02d\",\"description\":\"Few clouds\"}," +
                "\"station\":\"E5945\",\"elev_angle\":11.01,\"dni\":487.4,\"lat\":34.774,\"vis\":5,\"uv\":0.932543,\"temp\":21.7,\"dhi\":54.31," +
                "\"ghi\":139.36,\"app_temp\":22.5,\"dewpt\":21.7,\"wind_dir\":135,\"solar_rad\":138.7,\"country_code\":\"US\",\"rh\":100," +
                "\"slp\":1017.5,\"snow\":0,\"sunset\":\"00:20\",\"aqi\":26,\"state_code\":\"NC\"," +
                "\"wind_cdir_full\":\"southeast\",\"ts\":1622806560," +
                "\"title\": \"Flood Warning issued February 8 at 8:51PM CST expiring February 12 at 8:24AM CST by NWS St Louis MO\",\n" +
                "      \"description\": \"Mississippi River at Chester\\n\\n.This Flood Warning is a result of 1-2 inches of rainfall across\\nthe basin earlier this week...\\nThe Flood Warning continues for\\nthe Mississippi River at Chester\\n* until Tuesday morning.\\n* At  8:30 PM Friday the stage was 26.8 feet.\\n* Flood stage is 27.0 feet.\\n* Minor flooding is forecast.\\n* The river is forecast to rise above flood stage by tonight and to\\ncrest near 29.0 feet by Saturday evening. The river is forecast to\\nfall below flood stage by Monday morning.\\n* Impact:  At 28.0 feet...Unleveed islands near Chester and the prison\\nfarm floods.\\n* Impact:  At 27.0 feet...Flood Stage.  Unprotected farmland on right\\nbank begins to flood.\",\n" +
                "      \"severity\": \"Warning\",\n" +
                "      \"effective_utc\": \"2019-02-09T02:51:00\",\n" +
                "      \"effective_local\": \"2019-02-08T21:51:00\",\n" +
                "      \"expires_utc\": \"2019-02-10T02:51:00\",\n" +
                "      \"expires_local\": \"2019-02-09T21:51:00\",\n" +
                "      \"uri\": \"https://api.weather.gov/alerts/NWS-IDP-PROD-3361975-2942026\",\n" +
                "      \"alerts\": [\n" +
                "        \"St Louis, MO\"\n" +
                "      ]\n" +
                "    }";

        JSONObject map = (JSONObject) JSONValue.parse(expectedJsonString);

        application = new Application("online", "online", 0); //the input api would be a dummy one
        application.setInputApi(mockRealInputAPI); //setting the dummy api a mock one

        when(application.getCurrentWeatherData("28547","fb6d55b649db4857b569c84977bad72b", false)).thenReturn(expectedJsonString);
        application.getCurrentWeatherData("28547","fb6d55b649db4857b569c84977bad72b", false);
        verify(mockRealInputAPI).getCurrentWeatherData("28547","fb6d55b649db4857b569c84977bad72b");
    }

    @Test
    public void DummyAPISendWeatherDataTest() throws IOException, URISyntaxException, InterruptedException {
        String weatherData = "{\"sunrise\":\"09: 56\"," +
                "\"pod\":\"d\",\"pres\":1016.7,\"timezone\":\"America/New_York\",\"ob_time\":\"2021-06-04 11:36\"," +
                "\"wind_cdir\":\"SE\",\"lon\":-77.3781,\"clouds\":21,\"wind_spd\":0.45,\"city_name\":\"Country Club Acres\"," +
                "\"h_angle\":-67.5,\"datetime\":\"2021-06-04:11\",\"precip\":0,\"weather\":{\"code\":801,\"icon\":\"c02d\",\"description\":\"Few clouds\"}," +
                "\"station\":\"E5945\",\"elev_angle\":11.01,\"dni\":487.4,\"lat\":34.774,\"vis\":5,\"uv\":0.932543,\"temp\":21.7,\"dhi\":54.31," +
                "\"ghi\":139.36,\"app_temp\":22.5,\"dewpt\":21.7,\"wind_dir\":135,\"solar_rad\":138.7,\"country_code\":\"US\",\"rh\":100," +
                "\"slp\":1017.5,\"snow\":0,\"sunset\":\"00:20\",\"aqi\":26,\"state_code\":\"NC\",\"wind_cdir_full\":\"southeast\",\"ts\":1622806560}";

        String jsonString = "{\"personalizations\":[{\"to\":[{\"email\":\"john.doe@example.com\",\"name\":\"John Doe\"}]," +
                "\"subject\":\"Hello, World!\"}],\"content\": [{\"type\": \"text/plain\", \"value\": " + weatherData + "}]," +
                "\"from\":{\"email\":\"sam.smith@example.com\",\"name\":\"Sam Smith\"}," +
                "\"reply_to\":{\"email\":\"sam.smith@example.com\",\"name\":\"Sam Smith\"}}";//should be no " around
        // weatherdata

        application = new Application("offline", "offline", 0); //the input api would be a dummy one
        application.setInputApi(mockDummyInputAPI); //setting the dummy api a mock one
        application.setOutputAPIHandler(mockDummyOutputAPI);

        when(application.getCurrentWeatherData("28546", "fb6d55b649db4857b569c84977bad72b", false)).thenReturn(jsonString);
        String sendData = application.getCurrentWeatherData("28546", "fb6d55b649db4857b569c84977bad72b", false);
        application.sendWeatherData(sendData, "fb6d55b649db4857b569c84977bad72b", "abc@123", "receiver", "weather", 
                "123@abc", "sender");

        verify(mockDummyInputAPI).getCurrentWeatherData("28546", "fb6d55b649db4857b569c84977bad72b");
        verify(mockDummyOutputAPI).sendWeatherData(sendData, "fb6d55b649db4857b569c84977bad72b", "abc@123", "receiver", "weather",
                "123@abc", "sender");
    }

    @Test
    public void RealAPISendWeatherDataTest() throws IOException, URISyntaxException, InterruptedException {
        String weatherData = "{\"sunrise\":\"09: 56\"," +
                "\"pod\":\"d\",\"pres\":1016.7,\"timezone\":\"America/New_York\",\"ob_time\":\"2021-06-04 11:36\"," +
                "\"wind_cdir\":\"SE\",\"lon\":-77.3781,\"clouds\":21,\"wind_spd\":0.45,\"city_name\":\"Country Club Acres\"," +
                "\"h_angle\":-67.5,\"datetime\":\"2021-06-04:11\",\"precip\":0,\"weather\":{\"code\":801,\"icon\":\"c02d\",\"description\":\"Few clouds\"}," +
                "\"station\":\"E5945\",\"elev_angle\":11.01,\"dni\":487.4,\"lat\":34.774,\"vis\":5,\"uv\":0.932543,\"temp\":21.7,\"dhi\":54.31," +
                "\"ghi\":139.36,\"app_temp\":22.5,\"dewpt\":21.7,\"wind_dir\":135,\"solar_rad\":138.7,\"country_code\":\"US\",\"rh\":100," +
                "\"slp\":1017.5,\"snow\":0,\"sunset\":\"00:20\",\"aqi\":26,\"state_code\":\"NC\",\"wind_cdir_full\":\"southeast\",\"ts\":1622806560}";

        String jsonString = "{\"personalizations\":[{\"to\":[{\"email\":\"john.doe@example.com\",\"name\":\"John Doe\"}]," +
                "\"subject\":\"Hello, World!\"}],\"content\": [{\"type\": \"text/plain\", \"value\": " + weatherData + "}]," +
                "\"from\":{\"email\":\"sam.smith@example.com\",\"name\":\"Sam Smith\"}," +
                "\"reply_to\":{\"email\":\"sam.smith@example.com\",\"name\":\"Sam Smith\"}}";//should be no " around
        // weatherdata

        application = new Application("offline", "offline", 0); //the input api would be a dummy one
        application.setInputApi(mockRealInputAPI); //setting the dummy api a mock one
        application.setOutputAPIHandler(mockRealOutputAPI);

        when(application.getCurrentWeatherData("28546", "fb6d55b649db4857b569c84977bad72b", false)).thenReturn(jsonString);
        String sendData = application.getCurrentWeatherData("28546", "fb6d55b649db4857b569c84977bad72b", false);
        application.sendWeatherData(sendData, "fb6d55b649db4857b569c84977bad72b", "abc@123", "receiver", "weather",
                "123@abc", "sender");

        verify(mockRealInputAPI).getCurrentWeatherData("28546", "fb6d55b649db4857b569c84977bad72b");
        verify(mockRealOutputAPI).sendWeatherData(sendData, "fb6d55b649db4857b569c84977bad72b", "abc@123", "receiver", "weather",
                "123@abc", "sender");
    }

    @Test
    public void ApplicationReadNoRecordDataTest() {
        application = new Application("offline", "offline", 0);
        application.setInputApi(mockDummyInputAPI);
        application.setDataManager(mockDataManager);

        String weatherData = "{\"sunrise\":\"09: 56\"," +
                "\"pod\":\"d\",\"pres\":1016.7,\"timezone\":\"America/New_York\",\"ob_time\":\"2021-06-04 11:36\"," +
                "\"wind_cdir\":\"SE\",\"lon\":-77.3781,\"clouds\":21,\"wind_spd\":0.45,\"city_name\":\"Country Club Acres\"," +
                "\"h_angle\":-67.5,\"datetime\":\"2021-06-04:11\",\"precip\":0,\"weather\":{\"code\":801,\"icon\":\"c02d\",\"description\":\"Few clouds\"}," +
                "\"station\":\"E5945\",\"elev_angle\":11.01,\"dni\":487.4,\"lat\":34.774,\"vis\":5,\"uv\":0.932543,\"temp\":21.7,\"dhi\":54.31," +
                "\"ghi\":139.36,\"app_temp\":22.5,\"dewpt\":21.7,\"wind_dir\":135,\"solar_rad\":138.7,\"country_code\":\"US\",\"rh\":100," +
                "\"slp\":1017.5,\"snow\":0,\"sunset\":\"00:20\",\"aqi\":26,\"state_code\":\"NC\",\"wind_cdir_full\":\"southeast\",\"ts\":1622806560}";

        when(mockDataManager.checkCache(28546)).thenReturn(null);
        when(mockDummyInputAPI.getCurrentWeatherData("28546", "fb6d55b649db4857b569c84977bad72b")).thenReturn(weatherData);

        assertEquals(weatherData, application.getCurrentWeatherData("28546", "fb6d55b649db4857b569c84977bad72b", true));
        assertTrue(outputStreamCaptor.toString().contains("there is no 28546\'s information, proceed to store " +
                "the data..."));
    }

    @Test
    public void ApplicationReadCachedDataTest(){
        application = new Application("offline", "offline", 0);
        application.setInputApi(mockDummyInputAPI);
        application.setDataManager(mockDataManager);
        String weatherData = "api data";

        when(mockDataManager.checkCache(28546)).thenReturn("test data");
        when(mockDummyInputAPI.getCurrentWeatherData("28546", "fb6d55b649db4857b569c84977bad72b")).thenReturn(weatherData);

        application.getCurrentWeatherData("28546", "fb6d55b649db4857b569c84977bad72b", true);
        assertTrue(outputStreamCaptor.toString().trim().contains("the data of the given zip code: " + "28546" + " is " + "test data"));
    }

    @Test
    public void ApplicationUpdataCachedDataTest(){
        application = new Application("offline", "offline", 0);
        application.setInputApi(mockDummyInputAPI);
        application.setDataManager(mockDataManager);
        String weatherData = "api data";

        when(mockDataManager.checkCache(28546)).thenReturn("test data");
        when(mockDummyInputAPI.getCurrentWeatherData("28546", "fb6d55b649db4857b569c84977bad72b")).thenReturn(weatherData);

        application.getCurrentWeatherData("28546", "fb6d55b649db4857b569c84977bad72b", false);
        assertTrue(outputStreamCaptor.toString().trim().contains("the updated data for "+ "28546" +" is " + "api " +
                "data"));
    }

    @Test
    public void ApplicationThresholdTest(){
        application = new Application("offline", "online", 0);
        application.setThreshold(100);
        assertEquals(100, application.getThreshold());
    }

    @Test
    public void ApplicationCurrentWeatherDataTest(){
        application = new Application("offline", "online", 0);
        application.setCurrentWeatherData("test");
        assertEquals("test", application.getCurrentWeatherDataVariable());
    }

    @Test
    public void isOverHeatTest(){
        String expectedJsonString = "sunrise:09: 56\n" +
                "pod:d\n" +
                "pres:1016.7\n" +
                "timezone:America/New_York\n" +
                "ob_time:2021-06-04 11:36\n" +
                "wind_cdir:SE\n" +
                "lon:-77.3781\n" +
                "clouds:21\n" +
                "wind_spd:0.45\n" +
                "city_name:Country Club Acres\n" +
                "h_angle:-67.5\n" +
                "datetime:2021-06-04:11\n" +
                "precip:0\n" +
                "weather:{\"code\":801,\"icon\":\"c02d\",\"description\":\"Few clouds\"}\n" +
                "station:E5945\n" +
                "elev_angle:11.01\n" +
                "dni:487.4\n" +
                "lat:34.774\n" +
                "vis:5\n" +
                "uv:0.932543\n" +
                "temp:  21.7\n" +
                "dhi:54.31\n" +
                "ghi:139.36\n" +
                "app_temp:22.5\n" +
                "dewpt:21.7\n" +
                "wind_dir:135\n" +
                "solar_rad:138.7\n" +
                "country_code:US\n" +
                "rh:100\n" +
                "slp:1017.5\n" +
                "snow:0\n" +
                "sunset:00:20\n" +
                "aqi:26\n" +
                "state_code:NC\n" +
                "wind_cdir_full:southeast\n" +
                "ts:1622806560\n";

        application = new Application("offline", "online", 0);
        application.setCurrentWeatherData(expectedJsonString);

        application.setThreshold(-99);
        assertTrue(application.isOverHeat());
        application.setThreshold(30);
        assertTrue(!application.isOverHeat());
    }

    @Test
    public void sendWithAsteriskTest() throws InterruptedException, IOException, URISyntaxException {
        String expectedJsonString = "sunrise:09: 56\n" +
                "pod:d\n" +
                "pres:1016.7\n" +
                "timezone:America/New_York\n" +
                "ob_time:2021-06-04 11:36\n" +
                "wind_cdir:SE\n" +
                "lon:-77.3781\n" +
                "clouds:21\n" +
                "wind_spd:0.45\n" +
                "city_name:Country Club Acres\n" +
                "h_angle:-67.5\n" +
                "datetime:2021-06-04:11\n" +
                "precip:0\n" +
                "weather:{\"code\":801,\"icon\":\"c02d\",\"description\":\"Few clouds\"}\n" +
                "station:E5945\n" +
                "elev_angle:11.01\n" +
                "dni:487.4\n" +
                "lat:34.774\n" +
                "vis:5\n" +
                "uv:0.932543\n" +
                "temp:  21.7\n" +
                "dhi:54.31\n" +
                "ghi:139.36\n" +
                "app_temp:22.5\n" +
                "dewpt:21.7\n" +
                "wind_dir:135\n" +
                "solar_rad:138.7\n" +
                "country_code:US\n" +
                "rh:100\n" +
                "slp:1017.5\n" +
                "snow:0\n" +
                "sunset:00:20\n" +
                "aqi:26\n" +
                "state_code:NC\n" +
                "wind_cdir_full:southeast\n" +
                "ts:1622806560\n";

        application = new Application("offline", "offline", 0);
        application.setOutputAPIHandler(mockDummyOutputAPI);
        application.setCurrentWeatherData(expectedJsonString);
        application.setThreshold(-99);

        application.sendWeatherData(expectedJsonString, "api", "address", "name", "subject", "from", "name");
        verify(mockDummyOutputAPI).sendWeatherData(stringCaptor.capture(), stringCaptor.capture(), stringCaptor.capture(), stringCaptor.capture(), stringCaptor.capture(), stringCaptor.capture(), stringCaptor.capture());

        assertEquals('*', stringCaptor.getAllValues().get(0).charAt(0));
    }

}
