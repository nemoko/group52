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
 * Represents a provider used to retrieve categries questions and choices for
 * the quiz.
 * 
 */
public interface QuestionDataProvider {

	/**
	 * loads the question categories and its questions and choices from a
	 * specific service.
	 * 
	 * @return a list of question categories provided by the implementing class.
	 *         the list may be empty but should be never null
	 */
	public List<Category> loadCategoryData();
}
