package Buider;

import com.google.gson.Gson;

public class BodyJSONBuilder {
    // Chuyển bất cứ dạng gì qua Json (String)
    public  static <T> String getJSONContent(T dataObject){
        return new Gson().toJson(dataObject);

    }
}
