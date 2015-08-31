package com.cbice.distribute.buyer.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

public class JsonUtil {
	
	public static Map jsonToMap(String json){
		
		ObjectMapper mapper = new ObjectMapper();
		Map map = null;
		try {
			map = mapper.readValue(json, Map.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Map classMap = new HashMap();
//		classMap.put("map", Map.class);
//		Map map = (Map)JSONObject.toBean(JSONObject.fromObject(json), Map.class, classMap);
		// 转换null
		Iterator it=map.keySet().iterator();
		while(it.hasNext()){
			String key = (String)it.next();
			Object value = map.get(key);
			if (value == null) {
				map.put(key, null);
			}
			if (value instanceof JSONNull) {
				map.put(key, null);
			}
		}
		return map;
	}
	
	public static String map2Json(Map map) {
		// 转换null
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			Object value = map.get(key);
			if (value == null) {
				map.put(key, "");
			}
		}
		
		JSONObject j = JSONObject.fromObject(map);
		return j.toString();
	}
	
	public static Map jsonArrayToMap(String json) { 		
		Map classMap = new HashMap();
		classMap.put("map", Map.class);
		Map map = (Map)JSONObject.toBean(JSONObject.fromObject(json), Map.class, classMap);
		// 转换null
		Iterator it=map.keySet().iterator();
		while(it.hasNext()){
			String key = (String)it.next();
			Object value = map.get(key);
			if (value == null) {
				map.put(key, "");
			}
			if (value instanceof JSONNull) {
				map.put(key, "");
			}
			if (value instanceof ArrayList) {
				List list = (ArrayList)value;
				StringBuffer sb = new StringBuffer();
				for(int i = 0 ;i<list.size();i++){
					if(i==0){
						sb.append(list.get(i));
					}else{
						sb.append(";"+list.get(i));
					}
				}
				map.put(key,sb.toString() );
			}
		}
		return map;
	}
	
	//String 转成Map
	public static Map String2Map(String responseStr){
		Map hashMap = new HashMap();
		String[] values = responseStr.split("&");
		for (String value : values) {
			String[] ms = value.split("=");
			if(ms.length==2){
				hashMap.put(ms[0], ms[1]);
			}else if(ms.length==1){
				hashMap.put(ms[0], "");
			}
		}
		return hashMap;
	}
	
	public static void main(String[] args) {
		String json = "{\"tpdu\":\"6004010000\",\"additionalResponseData\":\"01050001   49910005   \",\"torder.id\":\"5A9930B881BDA8DCC904A77F95760255\",\"field63\":\"435550\",\"icData\":null,\"primaryAccount\":\"6226901803378284\",\"riskId\":null,\"timeOfLocalTransaction\":\"134630\",\"dateOfLocalTransaction\":\"1009\",\"dateOfSettlement\":\"1009\",\"errorMsg\":\"成功\",\"authCode38\":\"123456\",\"header\":\"603100311300\",\"errorCode\":\"0000\",\"aiic\":\"12345654321\",\"refNo\":\"000000209247\"}";
		
		Map map = jsonToMap(json);
		System.out.println(map.toString());
		System.out.println(map.get("icData"));
	}
}
