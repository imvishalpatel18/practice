/*
 * Created on Jul 2, 2019
 *
 * $Id: CodeTemplates.xml,v 1.1 2012/11/29 19:54:11 adets Exp $
 * 
 * Copyright 2019 InfoDesk, Inc. All rights reserved.
 */
package test;

import java.util.ArrayList;
import java.util.List;

public class Network {

	private int size;
	// adjacency list
	private ArrayList<Integer>[] adjList;

	public Network(int size) {
		this.size = size;
		adjList = new ArrayList[this.size];

		for (int i = 0; i < this.size; i++) {
			adjList[i] = new ArrayList<>();
		}
	}

	public void connect(int source_, int dest_) {

		validate(source_, dest_);

		int source = source_ - 1;
		int dest = dest_ - 1;

		adjList[source].add(dest);
		adjList[dest].add(source);
	}

	public boolean query(int source_, int dest_) {
		validate(source_, dest_);

		int source = source_ - 1;
		int dest = dest_ - 1;

		boolean[] isVisited = new boolean[size];
		ArrayList<Integer> pathList = new ArrayList<>();

		// add source to path[]
		pathList.add(source);

		Boolean found = false;
		// Call recursive utility
		found = checkpath(source, dest, isVisited, pathList);

		return found;
	}

	private boolean checkpath(Integer u, Integer d, boolean[] isVisited, List<Integer> localPathList) {

		// Mark the current node
		isVisited[u] = true;

		if (u.equals(d)) {
			System.out.println(localPathList);
			// if match found then no need to traverse more till depth
			isVisited[u] = false;
			return true;
		}

		// Recur for all the vertices adjacent to current vertex
		for (Integer i : adjList[u]) {
			if (!isVisited[i]) {
				// store current node in path[]
				localPathList.add(i);
				boolean found = checkpath(i, d, isVisited, localPathList);

				// remove current node in path[]
				localPathList.remove(i);

				if (found) {
					return true;
				}
			}
		}

		// Mark the current node
		isVisited[u] = false;
		return false;
	}

	private void validate(int source, int dest) {
		if (source > size || dest > size) {
			throw new IllegalStateException("Invalid number");
		}
	}
}
