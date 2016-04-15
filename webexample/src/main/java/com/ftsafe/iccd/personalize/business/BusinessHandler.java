package com.ftsafe.iccd.personalize.business;

public interface BusinessHandler {

	/**
	 * 根据客户端上传的数据响应客户端
	 * @param data 客户端上传的数据
	 * @return String 服务器响应
	 */
	public String response(String data);
	
	public enum Bussiness {
		huipan(0x90),
		chachong(0x91);
		
		private final byte id;

		private Bussiness(int id) {
			this.id = (byte) id;
		}

		public byte getId() {
			return this.id;
		}
	}
}
