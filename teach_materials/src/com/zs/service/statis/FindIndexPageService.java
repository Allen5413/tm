package com.zs.service.statis;

import com.feinno.framework.common.service.EntityService;
import com.zs.domain.basic.User;


/**
 * Created by Allen on 2016/11/1.
 */
public interface FindIndexPageService extends EntityService<User> {
    /**
     * 查询还没有审核的交费记录条数
     * @return
     */
    public int findWaitAuditPayCount();

    /**
     * 统计学生订单发中心的还没有打印的记录条数
     * @param semesterId
     * @return
     */
    public int findNotPrintSpotOrderCount(long semesterId);

    /**
     * 统计学生订单发学生的还没有打印的记录条数
     * @param semesterId
     * @return
     */
    public int findNotPrintStudentOrderCount(long semesterId);

    /**
     * 统计学生订单发中心的还没有打包的记录条数
     * @param semesterId
     * @return
     */
    public int findNotPackageSpotOrderCount(long semesterId);

    /**
     * 统计学生订单发学生的还没有打包的记录条数
     * @param semesterId
     * @return
     */
    public int findNotPackageStudentOrderCount(long semesterId);

    /**
     * 统计学生一次性订单发中心的还没有打印的记录条数
     * @param semesterId
     * @return
     */
    public int findNotPrintSpotOnceOrderCount(long semesterId);

    /**
     * 统计学生一次性订单发学生的还没有打印的记录条数
     * @param semesterId
     * @return
     */
    public int findNotPrintStudentOnceOrderCount(long semesterId);

    /**
     * 统计学生一次性订单发中心的还没有打包的记录条数
     * @param semesterId
     * @return
     */
    public int findNotPackageSpotOnceOrderCount(long semesterId);

    /**
     * 统计学生一次性订单发学生的还没有打包的记录条数
     * @param semesterId
     * @return
     */
    public int findNotPackageStudentOnceOrderCount(long semesterId);

    /**
     * 统计学生订单还没有打印的记录条数
     * @param semesterId
     * @return
     */
    public int findNotPrintSpotPlaceOrderCount(long semesterId);

    /**
     * 统计学生订单还没有打包的记录条数
     * @param semesterId
     * @return
     */
    public int findNotPackageSpotPlaceOrderCount(long semesterId);
}
