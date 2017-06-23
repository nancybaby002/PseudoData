package controllers;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import play.data.binding.As;
import play.mvc.Controller;
import utils.NumberToos;
import utils.TextUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by adinlead on 17-6-22.
 */
public class DataGenerator extends Controller {
//    实时游（访）客人数查询服务
    public static void currentTouristsCount(String provinceId, String cityId, String districtId, String areaID, String time) {
        if (TextUtil.isEmpty(provinceId) && TextUtil.isEmpty(cityId) && TextUtil.isEmpty(districtId) && TextUtil.isEmpty(areaID))
            badRequest();
        JSONObject json = new JSONObject();
        int result;
        if (TextUtil.notEmpty(areaID)) {
            result = NumberToos.randomInt(10000, 100000);
            json.put("regionID", areaID);
        } else if (TextUtil.notEmpty(districtId)) {
            result = NumberToos.randomInt(20000, 200000);
            json.put("regionID", districtId);
        } else if (TextUtil.notEmpty(cityId)) {
            result = NumberToos.randomInt(3000000, 10000000);
            json.put("regionID", cityId);
        } else {
            result = NumberToos.randomInt(80000000, 150000000);
            json.put("regionID", provinceId);
        }
        int otherProvinceCount = NumberToos.randomInt(result / 7, result / 4);
        int localProvinceCount = result - otherProvinceCount;

        json.put("dataTime", time);
        json.put("count", result);
        json.put("otherProvinceCount", otherProvinceCount);
        json.put("localProvinceCount", localProvinceCount);
        renderJSON(json);
    }
//    实时游（访）客人数趋势变化服务
    public static void currentTouristsTrend(String provinceId, String cityId, String districtId, String areaID, @As("yyyyMMddhhmmss")Date time) {
        if (TextUtil.isEmpty(provinceId) && TextUtil.isEmpty(cityId) && TextUtil.isEmpty(districtId) && TextUtil.isEmpty(areaID))
            badRequest();
        if (time == null){
            SimpleDateFormat tsdf = new SimpleDateFormat("yyyyMMddHH");
            try {
                time = tsdf.parse(tsdf.format(new Date()));
            } catch (ParseException e) {
                e.printStackTrace();
                time = new Date();
            }
        }
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        int result;
        if (TextUtil.notEmpty(areaID)) {
            for (int i = 12; i > 0; i--) {
                JSONObject son = new JSONObject();
                calendar.setTime(time);
                calendar.add(Calendar.HOUR_OF_DAY,-i);
                son.put("dataTime", TextUtil.stringOf(sdf.format(calendar.getTime())));
                son.put("regionID", areaID);
                int count = NumberToos.randomInt(10000, 100000);
                int otherProvinceCount = NumberToos.randomInt(count / 7, count / 4);
                int localProvinceCount = count - otherProvinceCount;
                son.put("count", count);
                son.put("otherProvinceCount", otherProvinceCount);
                son.put("localProvinceCount", localProvinceCount);
                array.add(son);
            }
        } else if (TextUtil.notEmpty(districtId)) {
            for (int i = 12; i > 0; i--) {
                JSONObject son = new JSONObject();
                calendar.setTime(time);
                calendar.add(Calendar.HOUR_OF_DAY,-i);
                son.put("dataTime", TextUtil.stringOf(sdf.format(calendar.getTime())));
                son.put("regionID", districtId);
                int count = NumberToos.randomInt(20000, 200000);
                int otherProvinceCount = NumberToos.randomInt(count / 7, count / 4);
                int localProvinceCount = count - otherProvinceCount;
                son.put("count", count);
                son.put("otherProvinceCount", otherProvinceCount);
                son.put("localProvinceCount", localProvinceCount);
                array.add(son);
            }
        } else if (TextUtil.notEmpty(cityId)) {
            for (int i = 12; i > 0; i--) {
                JSONObject son = new JSONObject();
                calendar.setTime(time);
                calendar.add(Calendar.HOUR_OF_DAY,-i);
                son.put("dataTime", TextUtil.stringOf(sdf.format(calendar.getTime())));
                son.put("regionID", cityId);
                int count = NumberToos.randomInt(3000000, 10000000);
                int otherProvinceCount = NumberToos.randomInt(count / 7, count / 4);
                int localProvinceCount = count - otherProvinceCount;
                son.put("count", count);
                son.put("otherProvinceCount", otherProvinceCount);
                son.put("localProvinceCount", localProvinceCount);
                array.add(son);
            }
        } else {
            for (int i = 12; i > 0; i--) {
                JSONObject son = new JSONObject();
                calendar.setTime(time);
                calendar.add(Calendar.HOUR_OF_DAY,-i);
                son.put("dataTime", TextUtil.stringOf(sdf.format(calendar.getTime())));
                son.put("regionID", provinceId);
                int count = NumberToos.randomInt(80000000, 150000000);
                int otherProvinceCount = NumberToos.randomInt(count / 7, count / 4);
                int localProvinceCount = count - otherProvinceCount;
                son.put("count", count);
                son.put("otherProvinceCount", otherProvinceCount);
                son.put("localProvinceCount", localProvinceCount);
                array.add(son);
            }
        }
        json.put("datas",array);
        renderJSON(json);
    }
//    实时指定日期各区域游（访）客人数排行榜服务
    public static void currentTouristsRanks(String provinceId, String cityId, String districtId, String areaID, @As("yyyyMMddhhmmss")Date time) {
        if (TextUtil.isEmpty(provinceId) && TextUtil.isEmpty(cityId) && TextUtil.isEmpty(districtId) && TextUtil.isEmpty(areaID))
            badRequest();
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        if (TextUtil.notEmpty(areaID)) {
            for (int i = 12; i > 0; i--) {
                JSONObject son = new JSONObject();
                son.put("dataTime", time);
                son.put("regionID", areaID + i);
                son.put("count", NumberToos.randomInt(10000, 100000));
                array.add(son);
            }
        } else if (TextUtil.notEmpty(districtId)) {
            for (int i = 12; i > 0; i--) {
                JSONObject son = new JSONObject();
                son.put("dataTime", time);
                son.put("regionID", districtId + i);
                son.put("count", NumberToos.randomInt(20000, 200000));
                array.add(son);
            }
        } else if (TextUtil.notEmpty(cityId)) {
            for (int i = 12; i > 0; i--) {
                JSONObject son = new JSONObject();
                son.put("dataTime", time);
                son.put("regionID", cityId + i);
                son.put("count", NumberToos.randomInt(3000000, 10000000));
                array.add(son);
            }
        } else {
            for (int i = 12; i > 0; i--) {
                JSONObject son = new JSONObject();
                son.put("dataTime", time);
                son.put("regionID", provinceId + i);
                son.put("count", NumberToos.randomInt(80000000, 150000000));
                array.add(son);
            }
        }
        json.put("datas",array);
        renderJSON(json);
    }
}
