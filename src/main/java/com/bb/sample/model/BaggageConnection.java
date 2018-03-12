package com.bb.sample.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class BaggageConnection {

	BaggageNode startNode;
	
	BaggageNode endNode;
	
	int totalTransitTime;
	
	List<BaggageNode> intermediateNodes = new LinkedList<>();

	public BaggageConnection()
	{
		
	}
	
	public BaggageConnection(BaggageNode startNodeInp,BaggageNode endNodeInp,int transitTime)
	{
		startNode = startNodeInp;
		endNode = endNodeInp;
		totalTransitTime = transitTime;
	}
	
	public BaggageConnection(BaggageNode startNodeInp,BaggageNode endNodeInp)
	{
		startNode = startNodeInp;
		endNode = endNodeInp;
	}
	
	public BaggageNode getStartNode() {
		return startNode;
	}

	public void setStartNode(BaggageNode startNode) {
		this.startNode = startNode;
	}

	public BaggageNode getEndNode() {
		return endNode;
	}

	public void setEndNode(BaggageNode endNode) {
		this.endNode = endNode;
	}

	public int getTotalTransitTime() {
		return totalTransitTime;
	}

	public void setTotalTransitTime(int totalTransitTime) {
		this.totalTransitTime = totalTransitTime;
	}

	public List<BaggageNode> getIntermediateNodes() {
		return intermediateNodes;
	}

	public void setIntermediateNodes(List<BaggageNode> intermediateNodes) {
		this.intermediateNodes = intermediateNodes;
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(startNode,endNode);
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(((BaggageConnection)o).getStartNode().getNodeNumber().equals(startNode.getNodeNumber())&&
				((BaggageConnection)o).getEndNode().getNodeNumber().equals(endNode.getNodeNumber()))
			return true;
		else
			return false;
	}

	public String toString()
	{
		return "Start "+startNode.nodeNumber+" -> end Node" + "transit time is " + totalTransitTime+" and nodes are "+intermediateNodes;
	}
}

