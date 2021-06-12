/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-05-11 16:06:28
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-05-15 16:54:29
 */
package com.swithun.backend.controller;

import java.util.Map;

import com.swithun.backend.entity.RewardRecordEntity;
import com.swithun.backend.entity.RewardSettingEntity;
import com.swithun.backend.service.RewardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@CrossOrigin
public class RewardController {

    @Autowired
    private RewardService rewardS;

    @PostMapping(value = "/reward/addSetting")
    public Integer addSetting(@RequestBody RewardSettingEntity setting) {
        return rewardS.addSetting(setting);
    }

    @PostMapping(value="/reward/addRecord")
    public String addRecord(@RequestBody RewardRecordEntity record) {
        rewardS.addRecord(record);
        return "ok";
    }

    @PostMapping(value="/reward/removeSetting")
    public String removeSetting(@RequestBody Map<String, Object> mp) {
        rewardS.removeSetting((Integer)mp.get("id"));
        return "ok";
    }
    
    @PostMapping(value="/reward/updateSetting")
    public String updateSetting(@RequestBody RewardSettingEntity setting) {
        rewardS.addSetting(setting);
        return "ok";
    }
    

    @GetMapping(value="/reward/getAll")
    public Map<String, Object> getAllInfo() {
        return rewardS.getAllInfo();
    }
    

}
/*
 * @Descripttion:
 * 
 * @version:
 * 
 * @@Company: None
 * 
 * @Author: Swithun Liu
 * 
 * @Date: 2021-05-11 16:06:28
 * 
 * @LastEditors: Swithun Liu
 * 
 * @LastEditTime: 2021-05-11 16:06:29
 */
