public class DummyInputAPIHandler implements InputAPIHandler {

    /**
     *
     * @param USZipCode
     * @param apiKey
     * @return the json form String that contains the information of weather
     */
    public String getCurrentWeatherData(String USZipCode, String apiKey){
        String jsonString = null;
        String output;

        if (USZipCode.equals("28546") && apiKey.equals("fb6d55b649db4857b569c84977bad72b")) {
            output = "sunrise:09: 56\n" +
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
                    "temp:21.7\n" +
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
            System.out.println( output + "no alerts in the area");

            jsonString = "{\n" +
                    "      \"city_name\": \"Raleigh\",\n" +
                    "      \"state_code\": \"NC\",\n" +
                    "      \"country_code\": \"US\",\n" +
                    "      \"timezone\": \"America/New_York\",\n" +
                    "      \"lat\": 38,\n" +
                    "      \"lon\": -78.25,\n" +
                    "      \"station\": \"KRDU\",\n" +
                    "      \"vis\": 10000,\n" +
                    "      \"rh\": 75,\n" +
                    "      \"dewpt\": 12,\n" +
                    "      \"wind_dir\": 125,\n" +
                    "      \"wind_cdir\": \"ENE\",\n" +
                    "      \"wind_cdir_full\": \"East-North-East\",\n" +
                    "      \"wind_speed\": 5.85,\n" +
                    "      \"temp\": 13.85,\n" +
                    "      \"app_temp\": 14.85,\n" +
                    "      \"clouds\": 42,\n" +
                    "      \"weather\": {\n" +
                    "        \"icon\": \"c02\",\n" +
                    "        \"code\": \"802\",\n" +
                    "        \"description\": \"Broken clouds\"\n" +
                    "      },\n" +
                    "      \"datetime\": \"2017-03-15:13\",\n" +
                    "      \"ob_time\": \"2017-03-15 13:11\",\n" +
                    "      \"ts\": 1490990400,\n" +
                    "      \"sunrise\": \"06:22\",\n" +
                    "      \"sunset\": \"19:34\",\n" +
                    "      \"slp\": 1013.12,\n" +
                    "      \"pres\": 1010,\n" +
                    "      \"aqi\": 50,\n" +
                    "      \"uv\": 6.5,\n" +
                    "      \"solar_rad\": 300.4,\n" +
                    "      \"ghi\": 450.4,\n" +
                    "      \"dni\": 450.4,\n" +
                    "      \"dhi\": 450.4,\n" +
                    "      \"elev_angle\": 37,\n" +
                    "      \"hour_angle\": 45,\n" +
                    "      \"pod\": \"string\",\n" +
                    "      \"precip\": 2,\n" +
                    "      \"snow\": 10\n" +
                    "    }";
        }

        //assume some alert info would pop out if looking for 28457
        else if (USZipCode.equals("28547") && apiKey.equals("fb6d55b649db4857b569c84977bad72b")){
            output = "sunrise:09: 56\n" +
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
                    "temp:21.7\n" +
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
                    "ts:1622806560\n" +
                    "ALERT: \n" +
                    "title: Flood Warning issued February 8 at 8:51PM CST expiring February 12 at 8:24AM CST by NWS St Louis MO\n" +
                    "description: Mississippi River at Chester.This Flood Warning is a result of 1-2 inches of rainfall acrossthe basin earlier this week...The Flood Warning continues forthe Mississippi River at Chester* until Tuesday morning.* At  8:30 PM Friday the stage was 26.8 feet.* Flood stage is 27.0 feet.* Minor flooding is forecast.* The river is forecast to rise above flood stage by tonight and tocrest near 29.0 feet by Saturday evening. The river is forecast tofall below flood stage by Monday morning.* Impact:  At 28.0 feet...Unleveed islands near Chester and the prisonfarm floods.* Impact:  At 27.0 feet...Flood Stage.  Unprotected farmland on rightbank begins to flood.\n" +
                    "severity: Warning \n" +
                    "effective_utc: 2019-02-09T02:51:00 \n" +
                    "effective_local: 2019-02-08T21:51:00 \n" +
                    "expires_utc: 2019-02-10T02:51:00 \n" +
                    "expires_local: 2019-02-09T21:51:00 \n" +
                    "uri: https://api.weather.gov/alerts/NWS-IDP-PROD-3361975-2942026 \n" +
                    "alerts: [ St Louis, MO] \n";

            jsonString = "{\n" +
                    "      \"city_name\": \"Raleigh\",\n" +
                    "      \"state_code\": \"NC\",\n" +
                    "      \"country_code\": \"US\",\n" +
                    "      \"timezone\": \"America/New_York\",\n" +
                    "      \"lat\": 38,\n" +
                    "      \"lon\": -78.25,\n" +
                    "      \"station\": \"KRDU\",\n" +
                    "      \"vis\": 10000,\n" +
                    "      \"rh\": 75,\n" +
                    "      \"dewpt\": 12,\n" +
                    "      \"wind_dir\": 125,\n" +
                    "      \"wind_cdir\": \"ENE\",\n" +
                    "      \"wind_cdir_full\": \"East-North-East\",\n" +
                    "      \"wind_speed\": 5.85,\n" +
                    "      \"temp\": 13.85,\n" +
                    "      \"app_temp\": 14.85,\n" +
                    "      \"clouds\": 42,\n" +
                    "      \"weather\": {\n" +
                    "        \"icon\": \"c02\",\n" +
                    "        \"code\": \"802\",\n" +
                    "        \"description\": \"Broken clouds\"\n" +
                    "      },\n" +
                    "      \"datetime\": \"2017-03-15:13\",\n" +
                    "      \"ob_time\": \"2017-03-15 13:11\",\n" +
                    "      \"ts\": 1490990400,\n" +
                    "      \"sunrise\": \"06:22\",\n" +
                    "      \"sunset\": \"19:34\",\n" +
                    "      \"slp\": 1013.12,\n" +
                    "      \"pres\": 1010,\n" +
                    "      \"aqi\": 50,\n" +
                    "      \"uv\": 6.5,\n" +
                    "      \"solar_rad\": 300.4,\n" +
                    "      \"ghi\": 450.4,\n" +
                    "      \"dni\": 450.4,\n" +
                    "      \"dhi\": 450.4,\n" +
                    "      \"elev_angle\": 37,\n" +
                    "      \"hour_angle\": 45,\n" +
                    "      \"pod\": \"string\",\n" +
                    "      \"precip\": 2,\n" +
                    "      \"snow\": 10,\n" +
                    "      \"title\": \"Flood Warning issued February 8 at 8:51PM CST expiring February 12 at 8:24AM" +
                    " CST by NWS St Louis MO\",\n" +
                    "      \"description\": \"Mississippi River at Chester\\n\\n.This Flood Warning is a result of 1-2 inches of rainfall across\\nthe basin earlier this week...\\nThe Flood Warning continues for\\nthe Mississippi River at Chester\\n* until Tuesday morning.\\n* At  8:30 PM Friday the stage was 26.8 feet.\\n* Flood stage is 27.0 feet.\\n* Minor flooding is forecast.\\n* The river is forecast to rise above flood stage by tonight and to\\ncrest near 29.0 feet by Saturday evening. The river is forecast to\\nfall below flood stage by Monday morning.\\n* Impact:  At 28.0 feet...Unleveed islands near Chester and the prison\\nfarm floods.\\n* Impact:  At 27.0 feet...Flood Stage.  Unprotected farmland on right\\nbank begins to flood.\",\n" +
                    "      \"severity\": \"Warning\",\n" +
                    "      \"effective_utc\": \"2019-02-09T02:51:00\",\n" +
                    "      \"effective_local\": \"2019-02-08T21:51:00\",\n" +
                    "      \"expires_utc\": \"2019-02-10T02:51:00\",\n" +
                    "      \"expires_local\": \"2019-02-09T21:51:00\",\n" +
                    "      \"uri\": \"https://api.weather.gov/alerts/NWS-IDP-PROD-3361975-2942026\",\n" +
                    "      \"alerts\": [" +
                    "\"St Louis, MO\"" +
                    "]\n"+
                    "    }";
            System.out.println(output);
        }

        //if any argument is wrong print exception(simulating wrong user input)
        else {
            System.out.println("exception: java.lang.RuntimeException: Failed : HTTP Error code : 403");
            System.out.println("chech your api key or us zip code");
        }

        return jsonString;
    }

}
