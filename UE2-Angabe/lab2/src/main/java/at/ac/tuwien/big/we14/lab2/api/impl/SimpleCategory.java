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
package at.ac.tuwien.big.we14.lab2.api.impl;

import java.util.ArrayList;
import java.util.List;

import at.ac.tuwien.big.we14.lab2.api.Category;
import at.ac.tuwien.big.we14.lab2.api.Question;

/**
 *
 */
public class SimpleCategory implements Category {

	private String name;

	private List<Question> questions;

	public SimpleCategory() {
		name = "";
		questions = new ArrayList<>();
	}

	public SimpleCategory(String name) {
		super();
		this.name = name;
	}

	public SimpleCategory(String name, List<Question> questions) {
		super();
		this.name = name;
		this.questions = questions;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<Question> getQuestions() {
		return questions;
	}

	@Override
	public void addQuestion(Question question) {
		if (!questions.contains(question)) {
			questions.add(question);
			if (question.getCategory() != this) {
				question.setCategory(this);
			}
		}
	}

	@Override
	public void removeQuestion(Question question) {
		if (questions.contains(question)) {
			questions.remove(question);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((questions == null) ? 0 : questions.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimpleCategory other = (SimpleCategory) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (questions == null) {
			if (other.questions != null)
				return false;
		} else if (!questions.equals(other.questions))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SimpleCategory [name=" + name + ", questions=" + questions
				+ "]";
	}

}
