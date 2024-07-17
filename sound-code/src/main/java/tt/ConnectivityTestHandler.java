package tt;


import java.util.List;

/**
 * @author liujiao
 * @date 2024/7/16 10:59
 */
public class ConnectivityTestHandler extends AbstractControlActionHandler {

    /**
     * 仿真配置
     */
    private SimulationConfig simulationConfig;

    /**
     * 默认的连通性测试响应超时时间
     */
    private int defaultConnecitivityTestOvertimeSecond;

    /**
     * 构造函数 - 对变量初始化赋值
     */
    public ConnectivityTestHandler(OrderMessageHandlerBuilder handlerBuilder, SimulationConfig simulationConfig,
                                   @Value("${……overtime-seconds}") int defaultConnecitivityTestOvertimeSecond) {
        super(handlerBuilder);
        this.simulationConfig = simulationConfig;
        this.defaultConnecitivityTestOvertimeSecond = defaultConnecitivityTestOvertimeSecond;
    }

    /**
     * 执行连通性测试指令
     */
    @Override
    public void execute0(OrderMessage message, ContrrolAction action) {
        // 获取仿真指令工具
        OrderMessageHandler handler = getHandler(message.getProgrammeId());

        // 校验指令是否执行中
        if (handler.checkIfOrderExcuting(message)) {
            return;
        }

        // 获取该2接口需要发送的目标 idHex
        String targetIdHex = simulationConfig.getXXX(XXX);
        // 获取本次训练中所有的目标对象code
        List<String> targetCodeListInDuplicate = handler.getAllTargetCodesInDuplicate();
        // 获取连通性测试响应接口关注的idHex列表
        simulationConfig.getIdHexForReceivingmessage(XXX);

        // 发送连通性测试指令
        handler.sendMessageTosimulateAndwaitResponse(message,FieldTypeEnum,receiveMessageIdHexList,targetIdHex,
                dataField,overtimeseconds)
    }
}
