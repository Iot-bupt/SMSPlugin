package com.bupt.SMSPlugin.controller;

import com.bupt.SMSPlugin.data.SMSData;
import com.bupt.SMSPlugin.service.SMSService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.Future;

@RequestMapping("/api/v1/smsplugin")
public class SMSController {

    @Autowired
    private SMSService smsService;

    @RequestMapping(value = "/sendSms", method = RequestMethod.POST)
    @ResponseBody
    public Future<String> sendSms(@RequestBody String jsonStr) throws Exception{
        JsonObject jsonObj = (JsonObject)new JsonParser().parse(jsonStr);
        SMSData smsData = new SMSData(jsonObj);
        String result="";

        for(String phone:smsData.getPhones()){
            result=result.concat(smsService.sendSms(phone,smsData.getText())+"|");
        }

        return new AsyncResult<String>(result);
    }
}
