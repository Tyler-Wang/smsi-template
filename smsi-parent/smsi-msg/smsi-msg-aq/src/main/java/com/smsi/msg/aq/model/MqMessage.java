package com.smsi.msg.aq.model;

import java.io.Serializable;
/**
 * MQ消息
 * @author wangj
 *
 */
public abstract class MqMessage implements Serializable {
	
	private String msgId; //消息Id

	public abstract MqQueueName getQueueName();
	
	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	
}
