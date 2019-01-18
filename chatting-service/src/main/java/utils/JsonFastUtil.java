package utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


public class JsonFastUtil {
	

	
	/**
	 * 解决部分日期 yyyy-MM-dd HH:mm 反序列化报错
	 * @author hkd
	 * @param json
	 * @param clazz
	 * @return 
	 * @since JDK 1.7
	 */
	public static <T> T parseObject(String json, Class<T> clazz) {
		ParserConfig config = new ParserConfig();
		config.putDeserializer(Date.class, new MyDateFormatDeserializer()); // 我们自己实现的Deserializer
        return JSON.parseObject(json,clazz, config, JSON.DEFAULT_PARSER_FEATURE, new Feature[0]);
	}
}
