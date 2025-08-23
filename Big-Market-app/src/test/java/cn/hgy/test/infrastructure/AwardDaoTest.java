package cn.hgy.test.infrastructure;

import cn.hgy.infrastructure.persistent.dao.IAwardDao;
import cn.hgy.infrastructure.persistent.po.Award;
import com.alibaba.fastjson2.JSON;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-08-21
 * @Description: 奖品持久化单元测试
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
//@RequiredArgsConstructor
// 测试类因使用 @RequiredArgsConstructor 生成了带参数的构造函数，违反了 JUnit 要求测试类必须有且仅有一个公共无参构造函数的规则，导致报错。
public class AwardDaoTest {

    @Resource
    private IAwardDao awardDao;

    @Test
    public void test_queryAwardList() {
        List<Award> awards = awardDao.queryAwardList();
        log.info("测试结果：{}", JSON.toJSONString(awards));
    }
}
