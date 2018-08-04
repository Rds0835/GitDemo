package com.demo.demo;


import com.alibaba.fastjson.JSON;
import net.sf.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: json 序列化测试
 *
 * @author: ruands
 * @date: 2018-07-14 下午9:14
 **/
public class JSONDemoTest {

    @Test
    public void test(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("a", "1");
        jsonObject.put("b", "2");
        jsonObject.put("c","3");
        System.err.println(jsonObject.toString());
        Map<String, Object> map = new HashMap<>();
        map.put("a", "1");
        map.put("b", "2");
        map.put("c","3");
        System.err.println(JSON.toJSONString(map));
    }
}

