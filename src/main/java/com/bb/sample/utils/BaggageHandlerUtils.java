package com.bb.sample.utils;

import com.bb.sample.model.BaggageConnection;
import com.bb.sample.model.BaggageNode;

public class BaggageHandlerUtils {


	public static void printTransitInfo(String baggageTagNum , BaggageConnection transitPath)
	{
		System.out.println(baggageTagNum+"->"+transitPath.getStartNode());
		
		for(BaggageNode transitNode : transitPath.getIntermediateNodes())
		{
			System.out.println("->"+transitNode);
		}
		
		System.out.println("->"+transitPath.getEndNode());
		System.out.println(": TIme "+transitPath.getTotalTransitTime());
	}
}
