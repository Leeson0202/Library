package cool.leeson.library.service.wx;

import com.alibaba.fastjson.JSONObject;
import cool.leeson.library.entity.WxUserInfo;
import cool.leeson.library.util.HttpClientUtil;
import cool.leeson.library.util.WxUtil;
import org.springframework.stereotype.Service;

@Service
public class WxServiceImpl {
    public WxUserInfo getLoginCertificate(String code) {
        //请求地址
        String requestUrl = WxUtil.getWxServerUrl(code);
        String response;
        // 发送请求
        try {
            response = HttpClientUtil.getRequest(requestUrl);
        } catch (Exception e) {
            System.out.println("login 请求失败");
            return null;
        }
        //格式化JSON数据
        WxUserInfo wxUserInfo = JSONObject.parseObject(response, WxUserInfo.class);
        //检查数据库中是否存在 OPENID
//        WxUserInfo wxUserInfo_ = this.wxUserMapper.selectById(wxUserInfo.getOpenId());
//        if (wxUserInfo_ == null) {
//            //数据库中没有用户的 OPENID，添加到数据库中
//            this.wxUserMapper.insert(wxUserInfo);
//        } else {
//            if (!wxUserInfo.getSessionKey().equals(wxUserInfo_.getSessionKey())) {
//                //如果数据库保存的session_key和最新的session_key 不相同，则更新
//                wxUserInfo_.setSessionKey(wxUserInfo.getSessionKey());
//                this.wxUserMapper.updateById(wxUserInfo_);
//            }
//        }
        return wxUserInfo;
    }

}
