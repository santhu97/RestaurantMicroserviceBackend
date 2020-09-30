package com.mindtree.user.service;

import org.springframework.security.oauth2.core.user.OAuth2User;

import com.mindtree.user.dto.CartDto;
import com.mindtree.user.dto.PaymentDto;
import com.mindtree.user.dto.UserDto;
import com.mindtree.user.exception.UserAppException;

public interface UserService {

	String updateProfile(UserDto userDto, OAuth2User oAuth2User);

	Object getUserByEmail(String email) throws UserAppException;

	Object addToCart(CartDto cart, OAuth2User oAuth2User) throws UserAppException;

	Object confirmOrder(PaymentDto paymentDto, OAuth2User oAuth2User) throws UserAppException;

	Object confirmOrder(OAuth2User oAuth2User) throws UserAppException;

	Object addToCart(CartDto cart, String phoneNumber) throws UserAppException;

	Object confirmOrder(PaymentDto paymentDto, String phoneNumber, String name, String email) throws UserAppException;

	Object confirmOrder(String phoneNumber, String name, String email) throws UserAppException;

}
