package tt;

/**
 * @author liujiao
 * @date 2024/7/16 11:24
 */
public abstract class AbstractControlActionHandler implements ControlActionHandler {

    @Override
    public void execute(){
        execute0(null, null);
    }

    public void execute0(OrderMessage message, ContrrolAction action){

    }
}
