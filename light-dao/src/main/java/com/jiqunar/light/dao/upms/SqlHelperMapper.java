package com.jiqunar.light.dao.upms;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author jieguang.wang
 * @date 2020/9/8 10:38
 */
@Repository
public interface SqlHelperMapper {
    /**
     * 查询
     *
     * @param sql
     * @return
     */
    List<LinkedHashMap<String, Object>> select(@Param("sql") String sql);

    /**
     * 更新
     *
     * @param sql
     * @return
     */
    Integer update(@Param("sql") String sql);

    /**
     * 插入
     *
     * @param sql
     */
    Integer insert(@Param("sql") String sql);

    /**
     * 删除
     *
     * @param sql
     * @return
     */
    Integer delete(@Param("sql") String sql);
}
