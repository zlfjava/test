package com.dsz.util;


import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

/**
 * 验证码工具类
 */
public class SendMsg {
	private static String accessKeyId = "LTAI4GBn3KJk8mUtb6uFHNg7";
	private static String accessSecret = "ood804yJgqyOEs95xllCUitpnMqsRC";
	private static String signName = "学习交流论坛";
	private static String templateCode = "SMS_189710999";

	/**
	 * 发送短信的方法
	 * @param phoneNumbers
	 * @param code
	 */
	public static String sendMessage(String phoneNumbers, String code) {
		String msg = "";
		DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessSecret);
		IAcsClient client = new DefaultAcsClient(profile);
		CommonRequest request = new CommonRequest();
		request.setMethod(MethodType.POST);
		request.setDomain("dysmsapi.aliyuncs.com");
		request.setVersion("2017-05-25");
		request.setAction("SendSms");
		request.putQueryParameter("RegionId", "cn-hangzhou");
		request.putQueryParameter("PhoneNumbers", phoneNumbers);
		request.putQueryParameter("SignName", signName);
		request.putQueryParameter("TemplateCode", templateCode);
		request.putQueryParameter("TemplateParam", "{\"code\":\"" + code + "\"}");
		try {
			CommonResponse response = client.getCommonResponse(request);
			msg = response.getData();
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return msg;
	}

	/**
	 * 六位验证码  随机生成
	 * @return	验证码
	 */
	public static String getCode() {
		return ((int) ((Math.random() * 9 + 1) * 100000)) + "";
	}

}
