package com.shopbook.service;

import com.shopbook.entity.*;
import com.shopbook.repository.BookToCartItemRepository;
import com.shopbook.repository.CartItemRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class CartItemServiceTest {

    @Mock
    private CartItemRepository mockCartItemRepository;

    @Mock
    private BookToCartItemRepository mockBookToCartItemRepository;

    @Test
    public void testFindByShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(new CartItem());
        cartItems.add(new CartItem());

        Mockito.when(mockCartItemRepository.findByShoppingCart(shoppingCart)).thenReturn(cartItems);

        CartItemService cartItemService = new CartItemService(mockCartItemRepository, mockBookToCartItemRepository);

        List<CartItem> foundCartItems = cartItemService.findByShoppingCart(shoppingCart);

        assertEquals(2, foundCartItems.size());
    }

    @Test
    public void testUpdateCartItem() {
        CartItem cartItem = new CartItem();
        Book book = new Book();
        book.setOurPrice(20.01);
        cartItem.setBook(book);
        cartItem.setQty(2);

        CartItemService cartItemService = new CartItemService(mockCartItemRepository, mockBookToCartItemRepository);

        cartItemService.updateCartItem(cartItem);

        BigDecimal expectedSubtotal = BigDecimal.valueOf(40.02);
        assertEquals(expectedSubtotal, cartItem.getSubtotal());
    }

    @Test
    public void testAddBookToCartItem_BookExistsInCart() {
        Book book = new Book();
        book.setId(1L);
        book.setOurPrice(30.00);
        User user = new User();
        user.setShoppingCart(new ShoppingCart());

        List<CartItem> cartItems = new ArrayList<>();
        CartItem existingCartItem = new CartItem();
        existingCartItem.setBook(book);
        existingCartItem.setQty(1);
        cartItems.add(existingCartItem);

        Mockito.when(mockCartItemRepository.findByShoppingCart(user.getShoppingCart())).thenReturn(cartItems);

        CartItemService cartItemService = new CartItemService(mockCartItemRepository, mockBookToCartItemRepository);

        CartItem cartItem = cartItemService.addBookToCartItem(book, user, 2);

        assertNotNull(cartItem);
        assertEquals(book, cartItem.getBook());
        assertEquals(3, cartItem.getQty()); // 1 (existing) + 2 (new)
        BigDecimal expectedSubtotal = BigDecimal.valueOf(60);;
        BigDecimal actualSubtotal = cartItem.getSubtotal();;

        assertEquals(expectedSubtotal,actualSubtotal);
    }

    @Test
    public void testAddBookToCartItem_BookDoesNotExistInCart() {
        Book book = new Book();
        book.setId(1L);
        book.setOurPrice(30);
        User user = new User();
        user.setShoppingCart(new ShoppingCart());

        List<CartItem> cartItems = new ArrayList<>();
        Mockito.when(mockCartItemRepository.findByShoppingCart(user.getShoppingCart())).thenReturn(cartItems);

        Mockito.when(mockCartItemRepository.save(any(CartItem.class))).thenAnswer(invocation -> {
            CartItem savedCartItem = invocation.getArgument(0);
            savedCartItem.setId(1L); // Simulating save action
            return savedCartItem;
        });

        CartItemService cartItemService = new CartItemService(mockCartItemRepository, mockBookToCartItemRepository);

        CartItem cartItem = cartItemService.addBookToCartItem(book, user, 2);

        assertNotNull(cartItem);
        assertEquals(book, cartItem.getBook());
        assertEquals(2, cartItem.getQty());
        BigDecimal expectedSubtotal = BigDecimal.valueOf(60);
        assertEquals(expectedSubtotal, cartItem.getSubtotal());
    }

    @Test
    public void testAddBookToCartItem_EmptyCartItemList() {
        Book book = new Book();
        book.setId(1L);
        book.setOurPrice(30);
        User user = new User();
        user.setShoppingCart(new ShoppingCart());

        List<CartItem> cartItems = new ArrayList<>();
        Mockito.when(mockCartItemRepository.findByShoppingCart(user.getShoppingCart())).thenReturn(cartItems);

        Mockito.when(mockCartItemRepository.save(any(CartItem.class))).thenAnswer(invocation -> {
            CartItem savedCartItem = invocation.getArgument(0);
            savedCartItem.setId(1L); // Simulating save action
            return savedCartItem;
        });

        CartItemService cartItemService = new CartItemService(mockCartItemRepository, mockBookToCartItemRepository);

        CartItem cartItem = cartItemService.addBookToCartItem(book, user, 2);

        assertNotNull(cartItem);
        assertEquals(book, cartItem.getBook());
        assertEquals(2, cartItem.getQty());
        BigDecimal expectedSubtotal = BigDecimal.valueOf(60);
        assertEquals(expectedSubtotal, cartItem.getSubtotal());
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        CartItem cartItem = new CartItem();
        cartItem.setId(id);

        Mockito.when(mockCartItemRepository.findById(id)).thenReturn(Optional.of(cartItem));

        CartItemService cartItemService = new CartItemService(mockCartItemRepository, mockBookToCartItemRepository);

        CartItem foundCartItem = cartItemService.findById(id);

        assertNotNull(foundCartItem);
        assertEquals(id, foundCartItem.getId());
    }

    @Test
    public void testRemoveCartItem() {
        CartItem cartItem = new CartItem();

        CartItemService cartItemService = new CartItemService(mockCartItemRepository, mockBookToCartItemRepository);

        cartItemService.removeCartItem(cartItem);

        Mockito.verify(mockBookToCartItemRepository, Mockito.times(1)).deleteByCartItem(cartItem);
        Mockito.verify(mockCartItemRepository, Mockito.times(1)).delete(cartItem);
    }

    @Test
    public void testSave() {
        CartItem cartItem = new CartItem();

        Mockito.when(mockCartItemRepository.save(cartItem)).thenReturn(cartItem);

        CartItemService cartItemService = new CartItemService(mockCartItemRepository, mockBookToCartItemRepository);

        CartItem savedCartItem = cartItemService.save(cartItem);

        assertEquals(cartItem, savedCartItem);
    }

    @Test
    public void testFindByOrder() {
        Order order = new Order();
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(new CartItem());
        cartItems.add(new CartItem());

        Mockito.when(mockCartItemRepository.findByOrder(order)).thenReturn(cartItems);

        CartItemService cartItemService = new CartItemService(mockCartItemRepository, mockBookToCartItemRepository);

        List<CartItem> foundCartItems = cartItemService.findByOrder(order);

        assertEquals(2, foundCartItems.size());
    }

}