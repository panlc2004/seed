package com.czy.seed.mvc.sys.constance;

/**
 * 用户常量
 * Created by 003914[panlc] on 2017-08-10.
 */
public class UserConstants {

    /**
     * 超级管理员ID,数据库中要求超级管理员ID为1
     */
    private static long ADMIN_USER_ID = 1;

    /**
     * 系统ID——当定时任务进行时，如果对数据进行了修改或新增，
     * 则这部分数据操作人ID为0
     */
    private static long SEED_USER_ID = 0;

    /**
     * 系统管理员角色ID,数据库中要求超级管理员ID为1
     */
    private static long ADMIN_ROLE = 1;

}
