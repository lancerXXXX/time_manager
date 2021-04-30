/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-04-29 15:26:40
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-04-29 16:52:31
 */
package com.swithun.backend.controller;

import com.swithun.backend.DTO.AddStatisticTemplateDTO;
import com.swithun.backend.service.StatisticService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin
public class StatisticController {

    @Autowired
    private StatisticService statisticS;
    
    @PostMapping(value="/statistic/add")
    public Integer addStatisticTemplate(@RequestBody AddStatisticTemplateDTO dto) {
        return statisticS.addStatisticTemplate(dto);
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
