package com.bb.sample.model;

import java.util.Objects;

public class BaggageNode {

	String nodeNumber;

	public BaggageNode(String nodeNumberInp)
	{
		nodeNumber = nodeNumberInp;
	}
	
	public String getNodeNumber() {
		return nodeNumber;
	}

	public void setNodeNumber(String nodeNumber) {
		this.nodeNumber = nodeNumber;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(((BaggageNode)o).nodeNumber.equals(nodeNumber))
			return true;
		else
			return false;
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(nodeNumber);
	}
	
	@Override 
	public String toString()
	{
		return nodeNumber;
	}
}
