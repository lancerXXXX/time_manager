/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-29 15:26:40
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-05-29 18:52:11
 */
package com.swithun.backend.controller;

import java.util.List;

import com.swithun.backend.DTO.AddStatisticTemplateDTO;
import com.swithun.backend.entity.FinishedTaskRecordEntity;
import com.swithun.backend.entity.StatisticTemplateEntity;
import com.swithun.backend.service.StatisticService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@CrossOrigin
public class StatisticController {

    @Autowired
    private StatisticService statisticS;
    
    // @PostMapping(value="/statistic/add")
    // public Integer addStatisticTemplate(@RequestBody AddStatisticTemplateDTO dto) {
    //     return statisticS.addStatisticTemplate(dto);
    // }


    @PostMapping(value="/statistic/add")
    public Integer addStatisticTemplate(@RequestBody StatisticTemplateEntity st) {
        return statisticS.addStatisticTemplate(st).getId();
    }

    @GetMapping(value="/statistic/getAllTemplate")
    public List<StatisticTemplateEntity> getAllTemplate() {
        return statisticS.getAllTemplate();
    }

    @DeleteMapping(value="/statistic/remove/{id}")
    public String removeST(@PathVariable Integer id) {
        statisticS.removeSt(id);
        return "删除成功";
    }

    // @PostMapping(value="/statistic/update")
    // public String updateST(@RequestBody AddStatisticTemplateDTO dto) {
    //     statisticS.addStatisticTemplate(dto);
    //     return "更新成功";
    // }
    
    @GetMapping(value="/statistic/getDataWithId")
    public List<FinishedTaskRecordEntity> getFilteredDataBySTId(@RequestParam Integer id) {
        return statisticS.getFilteredDataBySTId(id);
    }
    
    @PostMapping(value="/statistic/getDataWithoutId")
    public List<FinishedTaskRecordEntity> getFilteredDataBySTId(@RequestBody StatisticTemplateEntity st) {
        return statisticS.getFilteredDataWithouSTId(st);
    }
}
/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-29 15:26:40
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-29 15:26:41
 */
