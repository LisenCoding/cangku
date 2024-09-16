package com.utils;

import java.util.*;

/**
 * 公共方法
 */
public class CommonUtil {
	/**
     * 获取随机字符串
     *
     * @param num
     * @return
     */
    public static String getRandomString(Integer num) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }


    /**
     * 检查map参数并添加缺失参数
     * @param params
     * @return
     */
    public static void checkMap(Map<String, Object> params) {
        boolean page=true,limit=true,sort=true,order=true;
        Iterator<Map.Entry<String, Object>> iter = params.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, Object> info = iter.next();
            Object key = info.getKey();
            Object value = info.getValue();
            if(value==null || "".equals(value) || "null".equals(value)){
                iter.remove();
            }else if("page".equals(key)){page=false;
            }else if("limit".equals(key)){limit=false;
            }else if("sort".equals(key)){sort=false;
            }else if("order".equals(key)){order=false;
            }
        }
        if(page){params.put("page","1");}
        if(limit){params.put("limit","10");}
        if(sort){params.put("sort","id");}
        if(order){params.put("order","desc");}
    }
}
