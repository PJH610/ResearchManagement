package com.zhuoyue.researchManement.common;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhuoyue.researchManement.bean.Menu;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import sun.misc.IOUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MenuDataManager {

    public static final String city_office = "city_office";
    public static final String area_office = "area_office";
    public static final String economy_manager = "economy_manager";
    public static final String expert_guidance = "expert_guidance";
    public static final String school_manager = "school_manager";
    public static final String subject_host = "subject_host";

    @Value("classpath:data/" + city_office + ".json")
    private Resource data_city_office;

    @Value("classpath:data/" + area_office + ".json")
    private Resource data_area_office;

    @Value("classpath:data/" + economy_manager + ".json")
    private Resource data_economy_manager;

    @Value("classpath:data/" + expert_guidance + ".json")
    private Resource data_expert_guidance;

    @Value("classpath:data/" + school_manager + ".json")
    private Resource data_school_manager;

    @Value("classpath:data/" + subject_host + ".json")
    private Resource data_subject_host;

    private static Map<String, List<Menu>> datas;

    @PostConstruct
    public void init() throws IOException {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Menu>>(){}.getType();
        datas = new HashMap<String, List<Menu>>();
        datas.put(city_office, gson.fromJson(new String(IOUtils.readFully(data_city_office.getInputStream(), -1,true), "utf-8"), type));
        datas.put(area_office, gson.fromJson(new String(IOUtils.readFully(data_area_office.getInputStream(), -1,true), "utf-8"), type));
        datas.put(economy_manager, gson.fromJson(new String(IOUtils.readFully(data_economy_manager.getInputStream(), -1,true), "utf-8"), type));
        datas.put(expert_guidance, gson.fromJson(new String(IOUtils.readFully(data_expert_guidance.getInputStream(), -1,true), "utf-8"), type));
        datas.put(school_manager, gson.fromJson(new String(IOUtils.readFully(data_school_manager.getInputStream(), -1,true), "utf-8"), type));
        datas.put(subject_host, gson.fromJson(new String(IOUtils.readFully(data_subject_host.getInputStream(), -1,true), "utf-8"), type));
    }

    public static List<Menu> getMenu(String menu) {
        if (datas != null) return datas.get(menu);
        return null;
    }

}
