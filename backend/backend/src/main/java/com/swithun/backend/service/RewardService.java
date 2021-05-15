/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-05-11 16:10:54
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-05-11 17:04:04
 */
package com.swithun.backend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.swithun.backend.dao.RewardPointRepository;
import com.swithun.backend.dao.RewardRecordRepository;
import com.swithun.backend.dao.RewardSettingRepository;
import com.swithun.backend.entity.RewardPointEntity;
import com.swithun.backend.entity.RewardRecordEntity;
import com.swithun.backend.entity.RewardSettingEntity;
import com.swithun.backend.utils.ClassConvert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RewardService {

    @Autowired
    private RewardSettingRepository settingR;

    @Autowired
    private RewardRecordRepository recordR;

    @Autowired
    private RewardPointRepository pointR;

    @Autowired
    private ClassConvert conveter;

    public Integer addSetting(RewardSettingEntity setting) {
        return (settingR.save(setting)).getId();
    }

    public void addRecord(RewardRecordEntity record) {
        recordR.save(record);
        RewardPointEntity point = pointR.findById(1).get();
        point.setPoint(point.getPoint() + record.getPoint());
        pointR.save(point);
    }

    public void removeSetting(Integer id) {
        RewardSettingEntity setting = settingR.findById(id).get();
        setting.setOn(false);
        settingR.save(setting);
    }

    public Map<String, Object> getAllInfo() {

        Map<String, Object> mp = new HashMap<String, Object>();

        RewardPointEntity point = pointR.findById(1).get();

        mp.put("point", point.getPoint());

        List<RewardSettingEntity> settings = settingR.findAll();
        for (RewardSettingEntity setting : settings) {
            setting.setTypeList(conveter.getTypeListByPlanType(setting.getPlanTypeByTaskType()));
        }
        mp.put("settings", settings);

        mp.put("records", recordR.findAll());

        return mp;

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
 * @Date: 2021-05-11 16:10:54
 * 
 * @LastEditors: Swithun Liu
 * 
 * @LastEditTime: 2021-05-11 16:10:55
 */
