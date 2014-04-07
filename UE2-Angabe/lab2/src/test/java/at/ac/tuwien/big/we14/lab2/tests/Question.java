/**
 * <copyright>
 *
 * Copyright (c) 2014 http://www.big.tuwien.ac.at All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 *
 * </copyright>
 */
package at.ac.tuwien.big.we14.lab2.tests;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple implementation for a question in the test cases
 * 
 * DO NOT MODIFY ANYTHING IN THIS FILE
 */
public class Question {

	private int id;

	private String text;

	private long maxTime;

	private List<Choice> wrongChoices;

	private List<Choice> correctChoices;

	private Category category;

	public Question() {
		this.id = 0;
		this.text = "";
		this.maxTime = 60;
		this.wrongChoices = new ArrayList<>();
		this.correctChoices = new ArrayList<>();
		this.category = null;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the maxTime
	 */
	public long getMaxTime() {
		return maxTime;
	}

	/**
	 * @param maxTime
	 *            the maxTime to set
	 */
	public void setMaxTime(long maxTime) {
		this.maxTime = maxTime;
	}

	/**
	 * @return the wrongChoices
	 */
	public List<Choice> getWrongChoices() {
		return wrongChoices;
	}

	/**
	 * @param wrongChoices
	 *            the wrong choices to set
	 */
	public void setWrongChoices(List<Choice> wrongChoices) {
		this.wrongChoices = wrongChoices;
	}

	/**
	 * @return the correct choices
	 */
	public List<Choice> getCorrectChoices() {
		return correctChoices;
	}

	/**
	 * @param correctChoices
	 *            the correct choices to set
	 */
	public void setCorrectChoices(List<Choice> correctChoices) {
		this.correctChoices = correctChoices;
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((correctChoices == null) ? 0 : correctChoices.hashCode());
		result = prime * result + id;
		result = prime * result + (int) (maxTime ^ (maxTime >>> 32));
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result
				+ ((wrongChoices == null) ? 0 : wrongChoices.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (correctChoices == null) {
			if (other.correctChoices != null)
				return false;
		} else if (!correctChoices.equals(other.correctChoices))
			return false;
		if (id != other.id)
			return false;
		if (maxTime != other.maxTime)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (wrongChoices == null) {
			if (other.wrongChoices != null)
				return false;
		} else if (!wrongChoices.equals(other.wrongChoices))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Question [id=" + id + ", text=" + text + ", maxTime=" + maxTime
				+ ", wrongChoices=" + wrongChoices + ", correctChoices="
				+ correctChoices + "]";
	}

}
