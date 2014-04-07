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
 * represents the type of a answer given in a test case
 * 
 * DO NOT MODIFY ANYTHING IN THIS FILE
 */
public enum AnswerType {
	ALL_CORRECT, ONE_CORRECT, ONE_CORRECT_ONE_WRONG, ALL_CORRECT_ONE_WRONG, ONE_CORRECT_ALL_WRONG, ONE_WRONG, ALL_WRONG, NONE;

	public boolean isCorrect() {
		return this == ALL_CORRECT;
	}

	public List<Choice> createChoices(List<Choice> wrongChoices,
			List<Choice> correctChoices) {
		List<Choice> choices = new ArrayList<>();

		switch (this) {
		case ALL_CORRECT:
			choices.addAll(correctChoices);
			break;
		case ALL_CORRECT_ONE_WRONG:
			choices.addAll(correctChoices);
			choices.add(wrongChoices.get((int) Math.random()
					* wrongChoices.size()));
			break;
		case ALL_WRONG:
			choices.addAll(wrongChoices);
			break;
		case ONE_CORRECT:
			choices.add(correctChoices.get((int) Math.random()
					* correctChoices.size()));
			break;
		case ONE_CORRECT_ALL_WRONG:
			choices.add(correctChoices.get((int) Math.random()
					* correctChoices.size()));
			choices.addAll(wrongChoices);
			break;
		case ONE_CORRECT_ONE_WRONG:
			choices.add(correctChoices.get((int) Math.random()
					* correctChoices.size()));
			choices.add(wrongChoices.get((int) Math.random()
					* wrongChoices.size()));
			break;
		case ONE_WRONG:
			choices.add(wrongChoices.get((int) Math.random()
					* wrongChoices.size()));
			break;
		case NONE:
		default:
		}

		return choices;
	}
}
