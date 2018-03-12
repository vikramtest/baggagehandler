package com.bb.sample.processor;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.bb.sample.model.BaggageNode;
import com.bb.sample.model.DepartureNode;

import org.junit.Assert;

@RunWith(MockitoJUnitRunner.class)
public class BaggageTransitProcessorTest {

	@Mock
	BaggageTransitProcessor transitProcessor;
	
	@Test
	public void getDepartureBaggageNodeNotNullTest()
	{
		DepartureNode endNode = new DepartureNode();
		
		endNode.setFlightNumber("111");
		when(transitProcessor.getDepartureBaggageNode(endNode)).thenReturn(new BaggageNode(null));
		
		BaggageNode node = transitProcessor.getDepartureBaggageNode(endNode);
		Assert.assertNotNull(node);
	}
	
	@Test
	public void getDepartureBaggageNodeNullTest()
	{
		DepartureNode endNode = new DepartureNode();
		
		endNode.setFlightNumber("111");
		BaggageNode node = transitProcessor.getDepartureBaggageNode(endNode);
		Assert.assertNull(node);
		
	}
	
}
