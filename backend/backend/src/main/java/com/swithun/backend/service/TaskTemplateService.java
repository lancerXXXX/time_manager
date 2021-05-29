/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-05-15 19:24:32
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-05-26 15:44:31
 */
package com.swithun.backend.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.swithun.backend.dao.TaskTemplateRepository;
import com.swithun.backend.entity.SubTaskEntity;
import com.swithun.backend.entity.TaskTemplateEntity;
import com.swithun.backend.utils.ClassConvert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskTemplateService {

    @Autowired
    private TaskTemplateRepository ttR;
    @Autowired
    private ClassConvert convert;
    Logger logger = LoggerFactory.getLogger(TaskTemplateService.class);

    public Integer addCustomTaskTemplate(TaskTemplateEntity template) {
        template.setType(0);
        TaskTemplateEntity save = ttR.save(template);
        return save.getId();
    }

    public Integer addCommonTaskTemplate(TaskTemplateEntity template) {
        template.setType(1);
        // debug begin
        Collection<SubTaskEntity> subTasks = template.getPlanByPlanId().getSubTasksById();
        for (SubTaskEntity subTask : subTasks) {
            logger.info(subTask.getName());
            subTask.setPlanByParentPlan(template.getPlanByPlanId());
        }
        // debug end
        TaskTemplateEntity save = ttR.save(template);
        return save.getId();
    }

    public void removeCustomTaskTemplate(TaskTemplateEntity template) {
        ttR.delete(template);
    }

    public void removeCommonTaskTemplate(TaskTemplateEntity template) {
        ttR.delete(template);
    }

    public Map<String, Object> getAllTemplates() {
        Map<String, Object> mp = new HashMap<String, Object>();
        List<TaskTemplateEntity> customTs = new ArrayList<>();
        List<TaskTemplateEntity> commonTs = new ArrayList<>();
        List<TaskTemplateEntity> all = ttR.findAll();
        for (TaskTemplateEntity tp : all) {
            tp.getPlanByPlanId().setTypeList(convert.getTypeList(tp.getPlanByPlanId()));
            if(tp.getType() == 0) {
                customTs.add(tp);
            }else if (tp.getType() == 1) {
                commonTs.add(tp);
            }
        }
        mp.put("customTemplates", customTs);
        mp.put("commonTemplates", commonTs);
        return mp;
    }
    
}
/*
 * @Descripttion: 
 * @version: 
 * @@Company: None
 * @Author: Swithun Liu
 * @Date: 2021-05-15 19:24:32
 * @LastEditors: Swithun Liu
 * @LastEditTime: 2021-05-15 19:24:33
 */
