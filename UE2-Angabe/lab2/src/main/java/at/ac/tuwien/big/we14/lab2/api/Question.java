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

import java.util.List;

/**
 * Represents a quiz question
 */
public interface Question {

	/**
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(int id);

	/**
	 * 
	 * @return the unique identifier
	 */
	public int getId();

	/**
	 * 
	 * @return the question text (may contain xhtml5 markup)
	 */
	public String getText();

	/**
	 * sets the question text
	 * 
	 * @param text
	 *            the question text
	 */
	public void setText(String text);

	/**
	 * 
	 * @return the maximum available time for this question
	 */
	public long getMaxTime();

	/**
	 * sets the maximum available time
	 * 
	 * @param maxTime
	 *            the maximum time to set
	 */
	public void setMaxTime(long maxTime);

	/**
	 * 
	 * @return temporary shuffled list of all choices
	 */
	public List<Choice> getAllChoices();

	/**
	 * 
	 * @return a list of all correct choices
	 */
	public List<Choice> getCorrectChoices();

	/**
	 * Adds a new choice to this question
	 * 
	 * @param choice
	 *            the choice to add
	 * @param isCorrect
	 *            a flag indicating if the choice is correct or not
	 */
	public void addChoice(Choice choice, boolean isCorrect);

	/**
	 * Removes the choice from the question.
	 * 
	 * @param choice
	 *            the choice to remove
	 */
	public void removeChoice(Choice choice);

	/**
	 * 
	 * @return the category of the question
	 */
	public Category getCategory();

	/**
	 * 
	 * @param category
	 *            the category to set
	 */
	public void setCategory(Category category);
}
