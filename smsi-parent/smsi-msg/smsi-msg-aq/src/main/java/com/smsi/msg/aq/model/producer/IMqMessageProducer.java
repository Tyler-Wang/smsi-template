package com.smsi.msg.aq.model.producer;

import com.smsi.msg.aq.model.MqMessage;

/**
 * @author wangj
 * @date 2016年6月30日
 */
public interface IMqMessageProducer {
	
	/**
	 * 发送队列消息
	 * @param mqMessage
	 */
	void sendQueueMessage(MqMessage mqMessage);
	
	/**
	 * 发送主题Topic消息
	 * @param mqMessage
	 */
	void sendTopicQueueMessage(MqMessage mqMessage);
}
