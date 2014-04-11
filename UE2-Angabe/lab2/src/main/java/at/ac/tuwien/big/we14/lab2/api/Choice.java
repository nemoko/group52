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
package at.ac.tuwien.big.we14.lab2.api;

/**
 * Represents a choice
 */
public interface Choice {

	/**
	 * 
	 * @return the choice text (may contain XHTML5 markup)
	 */
	public String getText();

	/**
	 * 
	 * @param text
	 *            the choice text (may contain XHTML% markup)
	 */
	public void setText(String text);

	/**
	 * @param question
	 *            the question to set
	 */
	public void setQuestion(Question question);

	/**
	 * 
	 * @return the corresponding question
	 */
	public Question getQuestion();

	/**
	 * sets the unique identifier of this choice
	 * 
	 * @param ID
	 *            the identifier to set
	 */
	public void setId(int ID);

	/**
	 * returns the unique identifier of this choice
	 * 
	 * @return the Identifier of this choice
	 */
	public int getId();
}
