package com.bupt.SMSPlugin.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Data;

import java.util.List;

@Data
public class SMSData {
    private JsonObject jsonObj ;
    private List<String> phones ;
    private String text ;

    public SMSData(JsonObject jsonObject){
        this.jsonObj = jsonObject;
        JsonArray array = jsonObj.get("phone").getAsJsonArray();
        for(JsonElement element:array)
        {
            this.phones.add(element.getAsString());
        }
        this.text = jsonObject.get("text").getAsString();
    }
}
