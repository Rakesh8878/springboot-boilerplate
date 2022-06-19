package com.springboot.boilerplate.exception;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.io.InputStream;

import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import java.util.stream.Collectors;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Component
public class ErrorMessageConfiguration {

	JsonObject object;
	
	ErrorMessageConfiguration() throws NotFoundException {
        String jsonString;
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("messages.json");
        if (inputStream == null) {
            throw new NotFoundException("Message Config not found");
        }
        jsonString = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8)).lines().collect(Collectors.joining("\n"));
        this.object = JsonParser.parseString(jsonString).getAsJsonObject();
    }
	
	public ErrorMessage getMessage(String messageId) {
        return getMessage(messageId, null, null);
    }

    public ErrorMessage getMessage(String messageId, String text) {
        return getMessage(messageId, text, null);
    }

    public ErrorMessage getMessage(String messageId, String text, String details) {
        JsonObject messages = object.getAsJsonObject().getAsJsonObject("messages");
        JsonObject message = messages.getAsJsonObject(messageId);
        return new ErrorMessage(message.getAsJsonPrimitive("code").getAsString(),
                (text != null ? text : message.getAsJsonPrimitive("text").getAsString()),
                (details != null ? details : message.getAsJsonPrimitive("detail").getAsString()));
    }

}
