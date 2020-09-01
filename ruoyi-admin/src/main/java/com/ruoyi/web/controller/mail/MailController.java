package com.ruoyi.web.controller.mail;

import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.work.service.ITbWorkTasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @create 2020/8/19
 * @Description: 邮件发送
 * @since 1.0.0
 */
@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Autowired
    private ITbWorkTasksService tbWorkTasksService;

    @Autowired
    private ISysUserService sysUserService;

    @RequestMapping("/mail")
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
