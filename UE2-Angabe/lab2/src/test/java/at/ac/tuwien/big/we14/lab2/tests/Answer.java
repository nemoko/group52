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

import java.util.List;

/**
 * Simple implementation for a answer in the test cases
 * 
 * DO NOT MODIFY ANYTHING IN THIS FILE
 */
public class Answer {
	Question question;
	AnswerType type;
	List<Choice> choices;

	public Answer(Question question, AnswerType type, List<Choice> choices) {
		super();
		this.question = question;
		this.type = type;
		this.choices = choices;
	}

	public boolean isCorrect() {
		if(question == null){
			return false;
		}else if (type == AnswerType.ALL_CORRECT){
			return true;
		}
		List<Choice> correctChoices = question.getCorrectChoices();
		return choices.size() == correctChoices.size()
				&& correctChoices.containsAll(choices);
	}

	/**
	 * @return the question
	 */
	public Question getQuestion() {
		return question;
	}

	/**
	 * @param question
	 *            the question to set
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}

	/**
	 * @return the type
	 */
	public AnswerType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(AnswerType type) {
		this.type = type;
	}

	/**
	 * @return the choices
	 */
	public List<Choice> getChoices() {
		return choices;
	}

	/**
	 * @param choices
	 *            the choices to set
	 */
	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((choices == null) ? 0 : choices.hashCode());
		result = prime * result
				+ ((question == null) ? 0 : question.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	/* (non-Javadoc)
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
		Answer other = (Answer) obj;
		if (choices == null) {
			if (other.choices != null)
				return false;
		} else if (!choices.equals(other.choices))
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Answer [question=" + question + ", type=" + type + ", choices="
				+ choices + "]";
	}
	
	

}
