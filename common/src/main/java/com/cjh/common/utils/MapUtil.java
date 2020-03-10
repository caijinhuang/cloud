package com.cjh.common.utils;

import org.apache.commons.collections4.MapUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cjh
 * @date 2020/3/10 15:17
 **/
public class MapUtil {

    private Map map;

    private MapUtil() {
        this.map = new HashMap();
    }

    public static MapUtil builder() {
        return new MapUtil();
    }

    public MapUtil put(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

    public Map build() {
        return this.map;
    }
}
