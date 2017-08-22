import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushMsgToSingleDeviceRequest;
import com.baidu.yun.push.model.PushMsgToSingleDeviceResponse;

public class Main {

    public static void main(String[] args) throws PushClientException, PushServerException{
        //创建PushKeyPair
        String apiKey = "YhsQ3b58jhut4wl2EjT7bhId";
        String secretKey = "HpUm671hmwCOqFVr347v83PQsZmoieXF";
        PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

        //创建BaiduPushClient,访问SDK接口，第二个参数是推送的域名
        BaiduPushClient pushClient = new BaiduPushClient(pair, BaiduPushConstants.CHANNEL_REST_URL);

        //注册YunLogHandler，获取本次请求的交互信息
        pushClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent yunLogEvent) {
                System.out.println(yunLogEvent.getMessage());
            }
        });

        //设置请求参数，创建请求实例
        PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest();
        request.addChannelId("3814050136204926937");
        request.addMsgExpires(new Integer(3600));   //设置消息的有效时间,单位秒,默认3600 x 5.
        request.addMessageType(1);  //设置消息类型,0表示消息,1表示通知,默认为0.
        request.addDeviceType(3);   //设置设备类型，3表示安卓，4表示IOS
        request.addMessage("{\"title\":\"TEST\",\"description\":\"Hello Baidu push!\"}");

        //执行HTTP请求，获取返回结果
        PushMsgToSingleDeviceResponse response = pushClient.pushMsgToSingleDevice(request);

        System.out.println("msgId: " + response.getMsgId() + ", sendTime: " + response.getSendTime());
    }


}
