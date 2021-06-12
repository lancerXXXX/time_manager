/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-05-15 19:19:04
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-05-15 19:43:08
 */
package com.swithun.backend.controller;

import java.util.Map;

import com.swithun.backend.entity.TaskTemplateEntity;
import com.swithun.backend.service.TaskTemplateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@CrossOrigin
public class TaskTemplateController {

    @Autowired
    private TaskTemplateService ttS;

    @PostMapping(value="/task/template/addCustom")
    public Integer addCustomTaskTemplate(@RequestBody TaskTemplateEntity template) {
        return ttS.addCustomTaskTemplate(template);
    }

    @PostMapping(value="/task/template/addCommon")
    public Integer addCommonTaskTemplate(@RequestBody TaskTemplateEntity template) {
        return ttS.addCommonTaskTemplate(template);
    }

    @PostMapping(value="/task/template/removeCustom")
    public void removeCustomTaskTemplate(@RequestBody TaskTemplateEntity template) {
        ttS.removeCustomTaskTemplate(template);
    }

    @PostMapping(value="/task/template/removeCommon")
    public void removeCommonTaskTemplate(@RequestBody TaskTemplateEntity template) {
        ttS.removeCommonTaskTemplate(template);
    }

    @PostMapping(value="/task/template/updateCustom")
    public Integer updateCustomTaskTemplate(@RequestBody TaskTemplateEntity template) {
        return ttS.addCustomTaskTemplate(template);
    }

    @PostMapping(value="/task/template/updateCommon")
    public Integer updateCommonTaskTemplate(@RequestBody TaskTemplateEntity template) {
        return ttS.addCommonTaskTemplate(template);
    }

    @GetMapping(value="/task/template/get")
    public Map<String, Object> get() {
        return ttS.getAllTemplates();
    }
    
}
/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-05-15 19:19:04
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-05-15 19:19:05
 */
