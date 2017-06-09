package com.czy.seed.mvc.wbm.math.formula.util;

import com.czy.seed.mvc.wbm.config.entity.type.FlightTypeConfig;
import com.czy.seed.mvc.wbm.config.entity.type.IndexConfig;
import com.czy.seed.mvc.wbm.math.formula.IllegalParamException;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

/**
 * Created by panlc on 2017-04-06.
 */
public class MathTool {

    /**
     * 差值计算
     *
     * @param indexConfigList
     * @param param
     * @return
     */
    public static BigDecimal linearInterpolation(List<IndexConfig> indexConfigList, BigDecimal param) {
        LinearParams linearParams = findLinearParams(indexConfigList, param);
        BigDecimal x1 = linearParams.getPre().getWeight();
        BigDecimal y1 = linearParams.getPre().getIndes();
        BigDecimal x2 = linearParams.getAft().getWeight();
        BigDecimal y2 = linearParams.getAft().getIndes();

        return linearInterpolation(x1, y1, x2, y2, param);
    }

    /**
     * 线性插值公式
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param x
     * @return
     */
    public static BigDecimal linearInterpolation(BigDecimal x1, BigDecimal y1, BigDecimal x2, BigDecimal y2, BigDecimal x) {
        if (x2.compareTo(x1) == 0) {
            throw new IllegalArgumentException("差值运算时参数错误，x1不能等于x2");
        }
        return y2.subtract(y1)
                .divide(x2.subtract(x1), 2, BigDecimal.ROUND_HALF_UP)
                .multiply(x.subtract(x1))
                .add(y1);
    }

    /**
     * 计算重心
     *
     * @param i
     * @param w
     * @param flightTypeConfig
     * @return
     */
    public static BigDecimal buildCg(BigDecimal i, BigDecimal w, FlightTypeConfig flightTypeConfig) {
        return i.subtract(flightTypeConfig.getK())
                .multiply(flightTypeConfig.getC())
                .divide(w, 3, BigDecimal.ROUND_HALF_UP)
                .add(flightTypeConfig.getDatum())
                .subtract(flightTypeConfig.getLemac())
                .multiply(new BigDecimal(100))
                .divide(flightTypeConfig.getMac(), 3, BigDecimal.ROUND_HALF_UP)
                .divide(BigDecimal.ONE, 2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 计算配值
     *
     * @param cg
     * @return
     */
    public static BigDecimal trimBuildCg(BigDecimal cg) {
        if (cg.compareTo(new BigDecimal(17)) > 0 && cg.compareTo(new BigDecimal(40)) < 0) {
            return linearInterpolation(new BigDecimal(17), new BigDecimal(2.5),
                    new BigDecimal(40), new BigDecimal(-2.5), cg);
        } else if (cg.compareTo(new BigDecimal(10.5)) >= 0 && cg.compareTo(new BigDecimal(17)) <= 0) {
            return new BigDecimal(2.5);
        } else if (cg.compareTo(new BigDecimal(40)) >= 0 && cg.compareTo(new BigDecimal(43)) <= 0) {
            return new BigDecimal(-2.5);
        } else {
            return cg;
            //throw new IllegalArgumentException("重心超出范围");
        }
    }

    /**
     * 求最小值
     *
     * @param x 参数1
     * @param y 参数2
     * @param z 参数3
     * @return
     */
    public static BigDecimal min(double x, double y, double z) throws IllegalParamException {
        BigDecimal bdx = new BigDecimal(x);
        BigDecimal bdy = new BigDecimal(y);
        BigDecimal bdz = new BigDecimal(z);
        return bdx.min(bdy).min(bdz);
    }

    /**
     * 判断param是否在区间[floor, top)中
     *
     * @param param 要判断的参数
     * @param floor 上限（包含）
     * @param top   下限（不包含）
     * @return
     */
    public static boolean between(BigDecimal param, double floor, double top) {
        if (param.compareTo(new BigDecimal(floor)) >= 0 && param.compareTo(new BigDecimal(top)) <= 0) {
            return true;
        }
        return false;
    }

    /**
     * 查找指定参数param指定参数区间
     *
     * @param indexConfigList 配置列表
     * @param param           计算值
     * @return LinearParams
     */
    private static LinearParams findLinearParams(List<IndexConfig> indexConfigList, BigDecimal param) {
        if (indexConfigList == null || indexConfigList.size() < 1) {
            throw new IllegalArgumentException("差值法计算时，指数配置参数不能为空");
        }
        LinearParams linearParams = new LinearParams();
        //插入一个重量（人数）、指数均为0的配置项，参与差值计算
        indexConfigList.add(buildZeroIndexConfig());
        //插入当前重量(人数),参与排序运算
        IndexConfig currentConfig = new IndexConfig();
        currentConfig.setWeight(param);
        currentConfig.setIndes(BigDecimal.ZERO);
        indexConfigList.add(currentConfig);
        indexConfigList.sort(Comparator.comparing(IndexConfig::getWeight));
        //参数校验：如果发现重量（人数）为负值的配置，报错
        IndexConfig headIndexConfig = indexConfigList.get(0);
        if (headIndexConfig.getWeight().compareTo(BigDecimal.ZERO) != 0) {
            throw new IllegalArgumentException("差值法计算时，发现配置重量小于0的参数：" + headIndexConfig.getWeight());
        }
        //开始计算，获取当前重量所在范围的数据值的前一个和后一个元素
        for (int i = 0; i < indexConfigList.size(); i++) {
            IndexConfig config = indexConfigList.get(i);
            if (currentConfig.equals(config)) {
                if (i != 0 || i != indexConfigList.size()) {
                    linearParams.setPre(indexConfigList.get(i - 1));
                    linearParams.setAft(indexConfigList.get(i + 1));
                    return linearParams;
                }
            }
        }

        throw new IllegalArgumentException("实际重量/人数：" + param + "超出最大限制:" + indexConfigList.get(indexConfigList.size() - 1));
    }

    /**
     * 创建0值计算参数
     *
     * @return IndexConfig
     */
    private static IndexConfig buildZeroIndexConfig() {
        IndexConfig indexConfig = new IndexConfig();
        indexConfig.setWeight(BigDecimal.ZERO);
        indexConfig.setIndes(BigDecimal.ZERO);
        return indexConfig;
    }

    static class LinearParams implements Serializable {
        private static final long serialVersionUID = -1984369841319728818L;
        private IndexConfig pre;    //前限
        private IndexConfig aft;    //后限

        public IndexConfig getPre() {
            return pre;
        }

        public void setPre(IndexConfig pre) {
            this.pre = pre;
        }

        public IndexConfig getAft() {
            return aft;
        }

        public void setAft(IndexConfig aft) {
            this.aft = aft;
        }
    }

}
