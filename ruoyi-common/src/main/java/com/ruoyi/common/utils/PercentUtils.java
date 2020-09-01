package com.ruoyi.common.utils;

import java.text.DecimalFormat;

/**
 * @author Administrator
 * @create 2020/8/17
 * @Description: 计算百分数工具类
 * @since 1.0.0
 */
public class PercentUtils {
    public static String myPercent(int y, int z) {
        // 接受百分比的值
        String percentage = "";
        double molecular = y * 1.0;
        double denominator = z * 1.0;
        double fen = molecular / denominator;
        // ##.00%
        DecimalFormat df1 = new DecimalFormat("##.00%");
        // 百分比格式，后面不足2位的用0补齐
        percentage = df1.format(fen);
        return percentage;
    }
}
