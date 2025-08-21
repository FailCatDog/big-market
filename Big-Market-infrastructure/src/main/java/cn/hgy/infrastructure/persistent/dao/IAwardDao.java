package cn.hgy.infrastructure.persistent.dao;

import cn.hgy.infrastructure.persistent.po.Award;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-08-21
 * @Description: 奖品 DAO
 */
@Mapper
public interface IAwardDao {

    List<Award> queryAwardList();
}
