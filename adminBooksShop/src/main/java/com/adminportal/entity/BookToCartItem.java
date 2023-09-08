package com.adminportal.entity;


import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class BookToCartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Book book;
	
	@ManyToOne
	private CartItem cartItem;

	public void setId(Long id) {
		this.id = id;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public void setCartItem(CartItem cartItem) {
		this.cartItem = cartItem;
	}
	
	
}
