package com.shopbook.service;

import com.shopbook.entity.*;
import com.shopbook.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
class OrderServiceTest {

    @Mock
    private OrderRepository mockOrderRepository;

    @Mock
    private CartItemService mockCartItemService;

    @Test
    public void testCreateOrder() {
        // Create mock data
        ShoppingCart shoppingCart = new ShoppingCart();
        ShippingAddress shippingAddress = new ShippingAddress();
        BillingAddress billingAddress = new BillingAddress();
        Payment payment = new Payment();
        String shippingMethod = "Standard";
        User user = new User();

        List<CartItem> cartItemList = new ArrayList<>();
        Book book = new Book();
        book.setId(1L);
        book.setOurPrice(30.01);
        CartItem cartItem = new CartItem();
        cartItem.setBook(book);
        cartItem.setQty(2);
        cartItemList.add(cartItem);

        shoppingCart.setGrandTotal(BigDecimal.valueOf(60.02));

        when(mockCartItemService.findByShoppingCart(shoppingCart)).thenReturn(cartItemList);
        when(mockOrderRepository.save(any(Order.class))).thenAnswer(invocation -> {
            Order savedOrder = invocation.getArgument(0);
            savedOrder.setId(1L);
            return savedOrder;
        });

        OrderService orderService = new OrderService(mockOrderRepository, mockCartItemService);

        Order order = orderService.createOrder(shoppingCart, shippingAddress, billingAddress, payment, shippingMethod, user);

        assertEquals("created", order.getOrderStatus());
        assertEquals(shippingAddress, order.getShippingAddress());
        assertEquals(billingAddress, order.getBillingAddress());
        assertEquals(payment, order.getPayment());
        assertEquals(shippingMethod, order.getShippingMethod());
        assertEquals(cartItemList, order.getCartItemList());
        assertEquals(user, order.getUser());
        assertEquals(BigDecimal.valueOf(60.02), order.getOrderTotal());
    }
}