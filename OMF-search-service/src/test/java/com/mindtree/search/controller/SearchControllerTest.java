package com.mindtree.search.controller;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.mindtree.search.dto.FoodInCartDto;
import com.mindtree.search.dto.ResturantDetailsDto;
import com.mindtree.search.exception.SearchServiceAppException;
import com.mindtree.search.service.SearchService;

import rx.exceptions.OnCompletedFailedException;

@SpringBootTest
class SearchControllerTest {
	
	@InjectMocks
	private SearchController searchController;
	
	@Mock
	private SearchService searchService;

	@Test
	void testSearchBasedOnFoodTypes() throws SearchServiceAppException {
		when(searchService.searchBasedOnFoodTypes(ArgumentMatchers.anyString())).thenReturn(new Object());
		assertEquals(searchController.searchBasedOnFoodTypes(ArgumentMatchers.anyString()).getStatusCode(), HttpStatus.OK);
		
	}

	@Test
	void testSearchByBudget() throws SearchServiceAppException {
		when(searchService.searchByBudget(ArgumentMatchers.anyDouble())).thenReturn(new Object());
		assertEquals(searchController.searchByBudget(ArgumentMatchers.anyDouble()).getStatusCode(), HttpStatus.OK);
	}

	@Test
	void testSearchByRating() throws SearchServiceAppException {
		when(searchService.searchByRating(ArgumentMatchers.anyDouble())).thenReturn(new Object());
		assertEquals(searchController.searchByRating(ArgumentMatchers.anyDouble()).getStatusCode(), HttpStatus.OK);
	
	}
	
	@Test
	void testSearchByDistance() throws SearchServiceAppException {
		when(searchService.searchByDistance(ArgumentMatchers.anyDouble(),ArgumentMatchers.anyDouble() ,ArgumentMatchers.anyDouble())).thenReturn(new Object());
		assertEquals(searchController.searchByDistance(ArgumentMatchers.anyDouble(),ArgumentMatchers.anyDouble() ,ArgumentMatchers.anyDouble()).getStatusCode(), HttpStatus.OK);
	}

	@Test
	void testSearchBar() throws SearchServiceAppException {
		when(searchService.searchBar(ArgumentMatchers.anyString())).thenReturn(new Object());
		assertEquals(searchController.searchBar(ArgumentMatchers.anyString()).getStatusCode(), HttpStatus.OK);
	}

//	@Test
//	void testGetrestaurantById() throws SearchServiceAppException {
//		when(searchService.getrestaurantById(ArgumentMatchers.anyString())).thenReturn(ArgumentMatchers.any(ResturantDetailsDto.class));
//		assertEquals(searchController.getrestaurantById(ArgumentMatchers.anyString()).getStatusCode(), HttpStatus.OK);
//	}

//	@Test
//	void testGetTotalCost() {
//	}
//
//	@Test
//	void testVarifyFoodAndRestaurant() {
//		fail("Not yet implemented");
//	}

}
