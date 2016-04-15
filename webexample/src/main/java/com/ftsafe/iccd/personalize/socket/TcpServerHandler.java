package com.ftsafe.iccd.personalize.socket;

import com.axis.common.Log;
import com.axis.common.log.LogWrapper;
import com.ftsafe.iccd.personalize.MessageHandler;
import com.ftsafe.iccd.personalize.MessageHandler.Callback;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TcpServerHandler extends ChannelInboundHandlerAdapter {
	private static final LogWrapper LOG = Log.get();
	
	@Override
	public void channelRead(final ChannelHandlerContext ctx, Object msg) {
		ByteBuf data = (ByteBuf) msg;
		byte[] bData = new byte[data.readableBytes()];
		// msg中存储的是ByteBuf类型的数据，把数据读取到byte[]
		data.readBytes(bData);
		String resultStr = new String(bData);
		LOG.debug("From client:{}", resultStr);
		// 释放资源，这行很关键
		data.release();
		
		// 数据处理入口
		final byte bid = bData[0];	// 服务码
		String params = resultStr.substring(1);	// 请求的业务数据
		new MessageHandler(bid, params, new Callback() {
			// 返回数据给客户端
			@Override
			public void success(String response) {
				LOG.debug("To client:{}", response);
				// 发送的数据必须转换成ByteBuf数组
				ByteBuf encoded = ctx.alloc().buffer(4 * response.length());
				encoded.writeBytes(response.getBytes());
				ctx.write(encoded);
			}

			// 结束连接
			@Override
			public void fail() {
				LOG.debug("连接断开");
				// 断开连接
				ctx.disconnect();
			}
		});
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		ctx.flush();
		// short connection
		ctx.disconnect();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		// Close the connection when an exception is raised.
		cause.printStackTrace();
		ctx.close();
	}
	
}
