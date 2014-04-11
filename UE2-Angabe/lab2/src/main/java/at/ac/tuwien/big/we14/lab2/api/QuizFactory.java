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
 * Factory interface for the quiz model
 *
 */
public abstract class QuizFactory {
	
	public static QuizFactory INSTANCE = null;
	
	public abstract Category createCategory();
	
	public abstract Question createQuestion();
	
	public abstract Choice createChoice();

	public abstract QuestionDataProvider createQuestionDataProvider();

}
