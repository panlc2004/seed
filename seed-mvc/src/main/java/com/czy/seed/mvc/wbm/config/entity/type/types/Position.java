package com.czy.seed.mvc.wbm.config.entity.type.types;

/**
 * 仓位信息
 * Created by panlc on 2017-04-28.
 */
public enum Position {
    /**
     * 驾驶舱
     */
    COCKPIT {
        @Override
        public String getName() {
            return "驾驶舱";
        }
    },

    /**
     * 前舱(空乘人员)
     */
    FWD {
        @Override
        public String getName() {
            return "前舱";
        }
    },

    /**
     * 中舱(旅客)
     */
    MID {
        @Override
        public String getName() {
            return "中舱";
        }
    },

    /**
     * 后舱(空乘人员)
     */
    AFT {
        @Override
        public String getName() {
            return "后舱";
        }
    };

    public abstract String getName();
}
