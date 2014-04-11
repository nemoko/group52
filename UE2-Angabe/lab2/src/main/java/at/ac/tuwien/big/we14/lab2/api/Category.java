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
 * Represents a Category for questions
 */
public interface Category {

	/**
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name);

	/**
	 * 
	 * @return the category name
	 */
	public String getName();

	/**
	 * 
	 * @return a list of all questions
	 */
	public List<Question> getQuestions();

	/**
	 * adds a new question
	 * 
	 * @param question
	 *            the question to add
	 */
	public void addQuestion(Question question);

	/**
	 * removes the question
	 * 
	 * @param question
	 *            the question to remove
	 */
	public void removeQuestion(Question question);

}
