package com.zhuoyue.researchManement.controller;

import com.zhuoyue.researchManement.util.CounterUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/counter")
public class CounterController {

    @GetMapping("/increase")
    public String increase(@RequestParam Long id) {
        CounterUtils.addCounter(id);
        return "success";
    }

    @GetMapping("/getIncreaseMap")
    public ConcurrentHashMap<Long, AtomicInteger> getIncreaseMap() {
        return CounterUtils.getIncreaseCounteMap();
    }

}
