package com.ideas.interview.entity;

import com.ideas.interview.enums.CoffeeAdditionals;
import com.ideas.interview.enums.CoffeeType;
import com.ideas.interview.enums.Coin;
import java.util.List;

public class Response<T, V> {
	private T first;
	private V second;

	public Response(T first, V second){
		this.first = first;
		this.second = second;
	}

	public T getFirst(){
		return first;
	}

	public V getSecond(){
		return second;
	}
}
