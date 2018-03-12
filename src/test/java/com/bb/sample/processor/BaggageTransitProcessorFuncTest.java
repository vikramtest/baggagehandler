package com.bb.sample.processor;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.bb.sample.launch.BaggageHandlerMaster;
import com.bb.sample.model.BaggageConnection;
import com.bb.sample.model.BaggageNode;
import com.sun.javafx.collections.MappingChange.Map;

@RunWith(MockitoJUnitRunner.class)
public class BaggageTransitProcessorFuncTest {

	@InjectMocks
	BaggageTransitProcessor transitProcessor;
	
	@Mock
	BaggageHandlerMaster handlerFramework;
	
	@Test
	public void retrieveBaggageInfoNotNullTest()
	{
		Map<BaggageConnection , BaggageConnection> baggageInfo = new HashMap<>();
		
		BaggageConnection tempDeptNode = new BaggageConnection();
		tempDeptNode.setStartNode(new BaggageNode("1"));
		tempDeptNode.setEndNode(new BaggageNode("2"));
		tempDeptNode.setTotalTransitTime(1);
		((HashMap<BaggageConnection, BaggageConnection>) baggageInfo).put(tempDeptNode ,  tempDeptNode);

		when(handlerFramework.getCollectionLink()).thenReturn((baggageInfo);
		
		assertNotNull(transitProcessor.getRouteBetweenNodes("001", tempDeptNode));	
		
	}
	
	@Test
	public void retrieveBaggageInfoNullTest()
	{
		
		Map<BaggageConnection,BaggageConnection> baggageInfo =  new HashMap<>();
		
		BaggageConnection tempDeptNode = new BaggageConnection();
		
		tempDeptNode.setStartNode(new BaggageNode("1"));
		tempDeptNode.setEndNode(new BaggageNode("2"));
		tempDeptNode.setTotalTransitTime(1);
		
		((HashMap<BaggageConnection, BaggageConnection>) baggageInfo).put(tempDeptNode,tempDeptNode);
		
		when(handlerFramework.getCollectionLink()).thenReturn((java.util.Map<BaggageConnection, BaggageConnection>) baggageInfo);
		
		BaggageConnection tempDeptNode1 = new BaggageConnection();
		tempDeptNode1.setStartNode(new BaggageNode("33"));
		tempDeptNode1.setEndNode(new BaggageNode("44"));
		
		assertNull(transitProcessor.getRouteBetweenNodes("002", tempDeptNode1));
	}
}
