package com.codegen.context;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public class CopyFileContext extends Context<Map> {


    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
