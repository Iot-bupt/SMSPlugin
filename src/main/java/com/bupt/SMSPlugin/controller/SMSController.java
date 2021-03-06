package com.bupt.SMSPlugin.controller;

import com.bupt.SMSPlugin.data.SMSData;
import com.bupt.SMSPlugin.service.SMSService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Future;

@RestController
@RequestMapping("/api/v1/smsplugin")
public class SMSController {

    @Autowired
    private SMSService smsService;

    @RequestMapping(value = "/sendSms", method = RequestMethod.POST)
    @ResponseBody
    public Future<String> sendSms(@RequestBody String jsonStr) throws Exception{
        JsonObject jsonObj = (JsonObject)new JsonParser().parse(jsonStr);
        SMSData smsData = new SMSData(jsonObj);
        String result;

        result = smsService.sendSms(smsData.getPhones(),smsData.getText());


        return new AsyncResult<String>(result);
    }
}
