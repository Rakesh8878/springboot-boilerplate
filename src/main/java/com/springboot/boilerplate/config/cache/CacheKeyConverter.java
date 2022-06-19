package com.springboot.boilerplate.config.cache;

import org.springframework.core.convert.converter.Converter;

import com.google.gson.Gson;

public class CacheKeyConverter implements Converter<Object, String> {

	@Override
	public String convert(Object source) {
		// By Default, object is converted to Json String
		return new Gson().toJson(source);
	}

}
