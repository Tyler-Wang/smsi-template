package com.smsi.msg.aq.model.producer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.alibaba.fastjson.JSONObject;
import com.smsi.msg.aq.model.MqMessage;

/**
 * @author wangj
 * @date 2016年6月30日
 */
public class AqMessageProducer implements IMqMessageProducer {

	private static final Logger logger = LoggerFactory.getLogger(AqMessageProducer.class);
	/**
	 * 队列Queue消息
	 */
	private JmsTemplate jmsQueueTemplate;
	
	/**
	 * 主题Topic消息
	 */
	private JmsTemplate jmsTopicTemplate;
	
	
	public void sendQueueMessage(final MqMessage mqMessage) {
		
		logger.debug("准备向队列 {}, 发送消息 {}", mqMessage.getQueue().getQueueName(), JSONObject.toJSONString(mqMessage));
		this.jmsQueueTemplate.send(mqMessage.getQueue().getQueueName(), new MessageCreator() {
			
			public Message createMessage(Session session) throws JMSException {
				// TODO Auto-generated method stub
				return session.createObjectMessage(mqMessage);
			}
		});
		logger.debug("向队列 {}, 发送消息 {} 完成", mqMessage.getQueue().getQueueName(), JSONObject.toJSONString(mqMessage));
	}

	public void sendTopicQueueMessage(final MqMessage mqMessage) {
		logger.debug("准备向Topic {}, 发送消息 {}", mqMessage.getQueue().getQueueName(), JSONObject.toJSONString(mqMessage));
		this.jmsTopicTemplate.send(mqMessage.getQueue().getQueueName(), new MessageCreator() {
			
			public Message createMessage(Session session) throws JMSException {
				// TODO Auto-generated method stub
				return session.createObjectMessage(mqMessage);
			}
		});
		logger.debug("向Topic {}, 发送消息 {} 完成", mqMessage.getQueue().getQueueName(), JSONObject.toJSONString(mqMessage));

	}

	public JmsTemplate getJmsQueueTemplate() {
		return jmsQueueTemplate;
	}

	public void setJmsQueueTemplate(JmsTemplate jmsQueueTemplate) {
		this.jmsQueueTemplate = jmsQueueTemplate;
	}

	public JmsTemplate getJmsTopicTemplate() {
		return jmsTopicTemplate;
	}

	public void setJmsTopicTemplate(JmsTemplate jmsTopicTemplate) {
		this.jmsTopicTemplate = jmsTopicTemplate;
	}
	
}
