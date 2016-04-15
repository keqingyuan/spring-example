package com.ftsafe.iccd.personalize;

import com.axis.common.Log;
import com.axis.common.log.LogWrapper;
import com.ftsafe.iccd.personalize.business.BusinessHandler.Bussiness;
import com.ftsafe.iccd.personalize.business.ChaChongBusiness;
import com.ftsafe.iccd.personalize.business.HuiPanBusiness;

public class MessageHandler {

	private static final LogWrapper LOG = Log.get();
	
	/**
	 * 报文协议处理
	 * @param bid 报文头：服务码
	 * @param msg 报文体：业务数据
	 * @param callback 回调
	 */
	public MessageHandler(byte bid, String msg, Callback callback) {
		String result = null;
		LOG.debug("data: {}", msg);
		Bussiness bi = null;
		// 业务分支
		switch (bid) {
		case (byte) 0x90: // 回盘标识码
			bi = Bussiness.huipan;
			result = new HuiPanBusiness(bi).response(msg);
			break;
		case (byte) 0x91: // 查重
			bi = Bussiness.chachong;
			result = new ChaChongBusiness(bi).response(msg);
			break;
		default:
			callback.fail();
			return;
		}

		// 结果返回不是NULL，把结果返回给客户端
		if (null != result){
			
			callback.success(result);
		}
		// 结果返回NULL，断开客户端连接
		else{
			callback.fail();
		}
	};

	public interface Callback {
		void fail();

		void success(String response);
	}

}
