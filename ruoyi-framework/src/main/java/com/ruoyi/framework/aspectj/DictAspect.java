

package com.ruoyi.framework.aspectj;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.ruoyi.common.annotation.DictValue;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.redis.RedisCache;
import org.apache.commons.lang3.ObjectUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 系统日志，切面处理类
 *
 * @author Mark sunlightcs@gmail.com
 */
@Aspect
@Component
public class DictAspect {

	@Autowired
	private RedisCache redisCache;

	@Pointcut("@annotation(com.ruoyi.common.annotation.DictValue)")
	public void logPointCut() {

	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		//获取执行结果
		Object result = point.proceed();
        Map<String, Object> beanToMap = BeanUtil.beanToMap(result);
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        DictValue dict = method.getAnnotation(DictValue.class);
        String sj =dict.value();
        if(StrUtil.isNotEmpty(sj)){
            String[] strs=dict.value().trim().split(",");
            //列表
            if(beanToMap.containsKey("rows")){
                TableDataInfo tableDataInfo = (TableDataInfo) result;
                //成功的请求
                if(200 == tableDataInfo.getCode()){
                    List<Object> list = (List<Object>) tableDataInfo.getRows();
                    List<Object> newListObject = new ArrayList<>();
                    for (Object object: list) {
                        Map<String,Object> stringObjectMap = BeanUtil.beanToMap(object);
                        for (String st: strs) {
                            String value=(String) stringObjectMap.get(st);
                            if(StrUtil.isNotEmpty(value)){
                                if(value.contains(",")){
                                    List<String> strList = new ArrayList<>();
                                    String[] strings = value.trim().split(",");
                                    for (String string:strings) {
                                        String valueName=redisCache.getCacheObject(string);
                                        strList.add(valueName);
                                    }
                                    String newName = ArrayUtil.join(strList.toArray(), ",");
                                    stringObjectMap.put(st+"Name",newName);
                                }else{
                                    String valueName=redisCache.getCacheObject(value);
                                    stringObjectMap.put(st+"Name",valueName);
                                }

                            }
                        }
                        newListObject.add(stringObjectMap);
                    }
                    tableDataInfo.setRows(newListObject);
                    return tableDataInfo;
                }
            }else if(beanToMap.containsKey("data")){
                //通用请求
                AjaxResult ajaxResult= (AjaxResult) result;
                Map<String,Object> ajaxResultMap=BeanUtil.beanToMap(ajaxResult);
                //成功的请求
                if(StrUtil.equals("200",(String) ajaxResult.get("code"))){
                    Object object = ajaxResultMap.get("data");
                    Map<String,Object> objectMap = BeanUtil.beanToMap(object);
                    for (String st: strs) {
                        String value = (String) objectMap.get(st);
                        if(StrUtil.isNotEmpty(value)){
                            if(value.contains(",")){
                                List<String> strList = new ArrayList<>();
                                String[] strings = value.trim().split(",");
                                for (String string:strings) {
                                    String valueName=redisCache.getCacheObject(string);
                                    strList.add(valueName);
                                }
                                String newName = ArrayUtil.join(strList.toArray(), ",");
                                objectMap.put(st+"Name",newName);
                            }else{
                                String valueName=redisCache.getCacheObject(value);
                                objectMap.put(st+"Name",valueName);
                            }
                        }
                    }
                    return AjaxResult.success().put("data",objectMap);
                }

            }
        }
        return AjaxResult.success();
	}


}
