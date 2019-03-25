package util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.ParseException;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Mappers {
    private static Map<String, Object> data = new HashMap<String, Object>();
    private static final Logger log = LoggerFactory.getLogger(Mappers.class);
    static {
        URL dirURL = Mappers.class.getResource("/testData");
        File[] files = null;
        if(dirURL != null && dirURL.getProtocol().equals("file")) {
            try {
                files = new File(dirURL.toURI()).listFiles();
            } catch (URISyntaxException e) {
                // TODO Auto-generated catch block
                log.error("", e);
            }
        }

        for (File file : files) {
            try {
                Reader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                JsonFactory factory = new JsonFactory();
                ObjectMapper mapper = new ObjectMapper(factory);
                TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
                };

                Map<String, Object> localData = mapper.readValue(reader, typeRef);
                data.putAll(localData);
            } catch (IOException e) {
                log.error("", e);
            }
        }
    }

    public static String getValue(String property){
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(data.get(property));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    public static <T> T convertJsonToObject(@Nonnull String jsonString, Class<T> clazz) {
        try {
            JsonFactory factory = new JsonFactory();
            ObjectMapper mapper = new ObjectMapper(factory);
            T obj = mapper.readValue(jsonString.replace("%s",getRandomAlphaNumericString()), clazz);
            return obj;
        } catch (ParseException | IOException e) {
            log.error("", e);
        }
        return null;
    }
    public static JSONObject getJSONObject(Object objDto) {
        String json = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            Map<String, Object> objectMap = objectMapper.convertValue(objDto, Map.class);
            return new JSONObject(objectMap);
        } catch (JSONException e) {
            log.info(json);
            log.error(e.getMessage(), e);
            return null;
        }
    }
    public static String getRandomAlphaNumericString(){
        return RandomStringUtils.random(32, true, true);
    }

}
