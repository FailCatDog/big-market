package cn.hgy.domain.strategy.repository;

import cn.hgy.domain.strategy.model.entity.StrategyAwardEntity;
import cn.hgy.domain.strategy.model.entity.StrategyEntity;
import cn.hgy.domain.strategy.model.entity.StrategyRuleEntity;
import cn.hgy.domain.strategy.model.valobj.RuleTreeVO;
import cn.hgy.domain.strategy.model.valobj.StrategyAwardRuleModelVO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-08-22
 * @Description: 策略仓储接口
 */
public interface IStrategyRepository {


    List<StrategyAwardEntity> queryStrategyAwardList(Long strategyId);

    void storeStrategyAwardSearchRateTable(String key, BigDecimal rateRange, HashMap<Integer, Integer> shuffledStrategyAwardSearchRateTables);

    int getRateRange(Long strategyId);

    int getRateRange(String key);

    Integer getStrategyAwardAssemble(String key, int rateKey);

    StrategyEntity queryStrategyEntityByStrategyId(Long strategyId);

    StrategyRuleEntity queryStrategyRule(Long strategyId, String ruleModel);

    String queryStrategyRuleValue(Long strategyId, Integer awardId, String ruleModel);

    String queryStrategyRuleValue(Long strategyId, String ruleModel);

    StrategyAwardRuleModelVO queryStrategyAwardRuleModelVO(Long strategyId, Integer awardId);

    /**
     * 根据规则树ID，查询树结构信息
     *
     * @param treeId 规则树ID
     * @return 树结构信息
     */
    RuleTreeVO queryRuleTreeVOByTreeId(String treeId);
}
