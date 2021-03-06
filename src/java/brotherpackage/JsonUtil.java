/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brotherpackage;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author maple
 */
public class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T>T toObject(String json, TypeReference<T> typeRef){
        T t = null;
        try {
            t = mapper.readValue(json, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }
    
    public static String toJsonString(List<Task> taskList) {
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(taskList);
        } catch (JsonGenerationException e) {
                    e.printStackTrace();
            } catch (JsonMappingException e) {
                    e.printStackTrace();
            } catch (IOException e) {
                    e.printStackTrace();
            }
        return jsonString;
    }
}
