/**
 * 
 */
package com.mindtree.order.service;

import java.io.IOException;

import javax.mail.MessagingException;

import com.mindtree.order.dto.CartDto;
import com.mindtree.order.dto.PaymentDto;
import com.mindtree.order.entity.Order;
import com.mindtree.order.exception.OrderServiceAppException;
import com.mindtree.order.exception.SearchServiceAppException;

/**
 * @author M1056182
 *
 */
public interface OrderService {

	Object addToCart(CartDto cart) throws SearchServiceAppException, OrderServiceAppException, Exception;

	Object confirmOrder(PaymentDto paymentDto, String userId, String userName,String phone) throws OrderServiceAppException,MessagingException,IOException;

	Object confirmOrder(String userId, String userName, String phone) throws OrderServiceAppException;

}
