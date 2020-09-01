package com.ruoyi.web.websocket.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.web.websocket.socket.WebSocket;
import com.ruoyi.work.service.ITbWorkTasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @create 2020/8/18
 * @Description:
 * @since 1.0.0
 */
@RestController
@RequestMapping("undo")
public class WebSocketController extends BaseController {

    @Autowired
    private WebSocket webSocket;

    @Autowired
    private ITbWorkTasksService tbWorkTasksService;

    @GetMapping("/sendAllWebSocket")
    public String test() {
        String text="你们好！这是websocket群体发送！";
        webSocket.sendAllMessage(text);
        return text;
    }

    @GetMapping("/sendOneWebSocket/{userName}")
    public String sendOneWebSocket(@PathVariable("userName") String userName) {

//        TbWorkTasks tbWorkTasks = new TbWorkTasks();
//        //创建人
//        tbWorkTasks.setCreateBy(userName);
//        //完成情况,未完成
//        tbWorkTasks.setCompletion("000702");
//        //完成时间
//        tbWorkTasks.setTimeEnd(DateUtil.today());
//        List<TbWorkTasks> list = tbWorkTasksService.selectTbWorkTasksList(tbWorkTasks);
//        JSONArray array= JSONArray.parseArray(JSON.toJSONString(list));

        List<Map<String, Object>> list = tbWorkTasksService.getUndoCount("");
        for (Map<String, Object> map : list) {
            //创建人
            String name = (String) map.get("name");
            //条数
            long count = (long) map.get("count");
            webSocket.sendOneMessage(name,String.valueOf(count));
        }

        return userName;
    }

    /**
     * 获取超时未完成的任务
     * @return
     */
    @RequestMapping("unDoTask")
    public AjaxResult unDoTask(){

        startPage();
        List<Map<String, Object>> list = tbWorkTasksService.getUndoCount(SecurityUtils.getUsername());
        if(list.size() == 0){
            return AjaxResult.success();
        }
        long count = (long) list.get(0).get("count");
        return AjaxResult.success(count);
    }




}
