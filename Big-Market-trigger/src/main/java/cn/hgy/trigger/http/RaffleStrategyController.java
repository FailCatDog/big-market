package cn.hgy.trigger.http;

import cn.hgy.domain.strategy.IRaffleAward;
import cn.hgy.domain.strategy.model.entity.RaffleAwardEntity;
import cn.hgy.domain.strategy.model.entity.RaffleFactorEntity;
import cn.hgy.domain.strategy.model.entity.StrategyAwardEntity;
import cn.hgy.domain.strategy.service.IRaffleStrategy;
import cn.hgy.domain.strategy.service.armory.IStrategyArmory;
import cn.hgy.trigger.api.IRaffleStrategyService;
import cn.hgy.trigger.api.dto.RaffleAwardListRequestDTO;
import cn.hgy.trigger.api.dto.RaffleAwardListResponseDTO;
import cn.hgy.trigger.api.dto.RaffleStrategyRequestDTO;
import cn.hgy.trigger.api.dto.RaffleStrategyResponseDTO;
import cn.hgy.types.enums.ResponseCode;
import cn.hgy.types.exception.AppException;
import cn.hgy.types.model.Response;
import com.alibaba.fastjson2.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 黄光宇
 * @CreateTime: 2025-09-02
 * @Description: 抽奖服务
 */
@Slf4j
@RestController()
@CrossOrigin("${app.config.cross-origin}")
@RequestMapping("/api/${app.config.api-version}/raffle/")
@RequiredArgsConstructor
public class RaffleStrategyController implements IRaffleStrategyService {

    private final IStrategyArmory strategyArmory;
    private final IRaffleAward raffleAward;
    private final IRaffleStrategy raffleStrategy;

    /**
     * 策略装配，将策略信息装配到缓存中
     * @param strategyId 策略Id
     * @return 装配结果
     */
    @GetMapping(value = "strategy_armory")
    @Override
    public Response<Boolean> strategyArmory(@RequestParam Long strategyId) {
        try {
            log.info("抽奖策略装配开始 strategyId：{}", strategyId);
            boolean armoryStatus = strategyArmory.assembleLotteryStrategy(strategyId);
             Response<Boolean> response = Response.<Boolean>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(armoryStatus)
                    .build();
            log.info("抽奖策略装配完成 strategyId：{}", strategyId);
            return response;
        } catch (Exception e) {
            log.error("抽奖策略装配失败 strategyId={}", strategyId);
            return Response.<Boolean>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }

    /**
     * 查询奖品列表
     * @param requestDTO 抽奖奖品列表请求参数
     * @return 奖品列表
     */
    @PostMapping(value = "query_raffle_award_list")
    @Override
    public Response<List<RaffleAwardListResponseDTO>> queryRaffleAwardList(@RequestBody RaffleAwardListRequestDTO requestDTO) {
        try {
            log.info("查询抽奖奖品列表开始 strategyId：{}", requestDTO.getStrategyId());
            // 查询奖品配置
            List<StrategyAwardEntity> strategyAwardEntities = raffleAward.queryRaffleStrategyAwardList(requestDTO.getStrategyId());
            List<RaffleAwardListResponseDTO> raffleAwardListResponseDTOS = new ArrayList<>(strategyAwardEntities.size());
            for (StrategyAwardEntity strategyAward : strategyAwardEntities) {
                raffleAwardListResponseDTOS.add(RaffleAwardListResponseDTO.builder()
                        .awardId(strategyAward.getAwardId())
                        .awardTitle(strategyAward.getAwardTitle())
                        .awardSubTitle(strategyAward.getAwardSubTitle())
                        .sort(strategyAward.getSort())
                        .build());
            }
            Response<List<RaffleAwardListResponseDTO>> response = Response.<List<RaffleAwardListResponseDTO>>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(raffleAwardListResponseDTOS)
                    .build();
            log.info("查询抽奖奖品列表配置完成 strategyId：{} response: {}", requestDTO.getStrategyId(), JSON.toJSONString(response));
            // 返回结果
            return response;
        } catch (Exception e) {
            log.error("查询抽奖奖品列表失败 strategyId={}", requestDTO.getStrategyId());
            return Response.<List<RaffleAwardListResponseDTO>>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }

    /**
     * 随机抽奖接口
     * @param requestDTO 抽奖请求参数
     * @return 抽奖结果
     */
    @PostMapping(value = "random_raffle")
    @Override
    public Response<RaffleStrategyResponseDTO> randomRaffle(@RequestBody RaffleStrategyRequestDTO requestDTO) {
        try {
            log.info("随机抽奖开始 strategyId: {}", requestDTO.getStrategyId());
            // 调用抽奖接口
            RaffleAwardEntity raffleAwardEntity = raffleStrategy.performRaffle(RaffleFactorEntity.builder()
                    .userId("system")
                    .strategyId(requestDTO.getStrategyId())
                    .build());
            // 封装返回结果
            Response<RaffleStrategyResponseDTO> response = Response.<RaffleStrategyResponseDTO>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(RaffleStrategyResponseDTO.builder()
                            .awardId(raffleAwardEntity.getAwardId())
                            .awardIndex(raffleAwardEntity.getSort())
                            .build())
                    .build();
            log.info("随机抽奖完成 strategyId: {} response: {}", requestDTO.getStrategyId(), JSON.toJSONString(response));
            return response;
        } catch (AppException e) {
            log.error("随机抽奖失败 strategyId：{} {}", requestDTO.getStrategyId(), e.getInfo());
            return Response.<RaffleStrategyResponseDTO>builder()
                    .code(e.getCode())
                    .info(e.getInfo())
                    .build();
        } catch (Exception e) {
            log.error("随机抽奖失败 strategyId：{}", requestDTO.getStrategyId(), e);
            return Response.<RaffleStrategyResponseDTO>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }
}
