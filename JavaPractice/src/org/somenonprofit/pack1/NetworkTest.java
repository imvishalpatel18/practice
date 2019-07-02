/*
 * Created on Jul 2, 2019
 *
 * $Id: CodeTemplates.xml,v 1.1 2012/11/29 19:54:11 adets Exp $
 * 
 * Copyright 2019 InfoDesk, Inc. All rights reserved.
 */
package test;

// https://www.geeksforgeeks.org/find-paths-given-source-destination

public class NetworkTest {

	public static void main(String[] args) {

		Network network = new Network(8);
		network.connect(1, 2);
		network.connect(3, 4);
		network.connect(6, 4);
		network.connect(2, 6);
		network.connect(5, 7);
		network.connect(5, 8);
		boolean path = network.query(2, 3);
		System.out.println(path);
		path = network.query(7, 2);
		System.out.println(path);
		path = network.query(8, 1);
		System.out.println(path);
		path = network.query(3, 1);
		System.out.println(path);
		path = network.query(3, 8);
		System.out.println(path);
	}
}
