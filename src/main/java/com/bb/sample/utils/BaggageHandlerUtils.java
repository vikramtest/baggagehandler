package com.bb.sample.utils;

import com.bb.sample.model.BaggageConnection;
import com.bb.sample.model.BaggageNode;

public class BaggageHandlerUtils {


	public static void printTransitInfo(String baggageTagNum , BaggageConnection transitPath)
	{
		System.out.print(baggageTagNum+"->"+transitPath.getStartNode());
		
		for(BaggageNode transitNode : transitPath.getIntermediateNodes())
		{
			System.out.print("->"+transitNode);
		}
		
		System.out.print("->"+transitPath.getEndNode());
		System.out.println(": Time "+transitPath.getTotalTransitTime());
	}
}
