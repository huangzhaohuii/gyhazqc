package com.ruoyi.quartz.task;

import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.work.service.ITbWorkTasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import com.ruoyi.common.utils.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 定时任务调度测试
 * 
 * @author ruoyi
 */
@Component("ryTask")
public class RyTask
{
    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Autowired
    private ITbWorkTasksService tbWorkTasksService;

    public void ryMultipleParams(String s, Boolean b, Long l, Double d, Integer i)
    {
        System.out.println(StringUtils.format("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{}", s, b, l, d, i));
    }

    public void ryParams(String params)
    {
        System.out.println("执行有参方法：" + params);
    }

    public void ryNoParams()
    {
        System.out.println("执行无参方法");
    }


    /**
     * 订单任务
     */
    public void sendMail(){
        //查询所有未完成用户及数量
        List<Map<String, Object>> list = tbWorkTasksService.getUndoCount("");
        if(list.size() > 0){
            for (Map<String, Object> map: list) {
                String userName = (String) map.get("name");
                long count = (long) map.get("count");
                SysUser sysUser = sysUserService.selectUserByUserName(userName);
                if(ObjectUtil.isEmpty(sysUser)){
                    return;
                }
                String email = sysUser.getEmail();
                SimpleMailMessage message = new SimpleMailMessage();
                //邮件设置
                message.setSubject("工作任务延时提醒");
                message.setText("您有"+count+"条工作任务已延时，请前往贵阳恒大足球场钢结构项目精细化管理平台查看延时详情。");
                message.setTo(email);
                message.setFrom("GuiyangGangjiegou@163.com");
                message.setSentDate(new Date());
                javaMailSender.send(message);
            }
        }

    }

}
