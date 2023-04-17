package com.nkhurshid.util;

import com.nkhurshid.daos.BookStoreDao;
import com.nkhurshid.daos.BookStoreDaoImpl;
import com.nkhurshid.services.BookStoreService;
import com.nkhurshid.services.BookStoreServiceImpl;

/**
 * Factory class for BookStore
 * 
 * @author Nabeeha
 *
 */
public class BookStoreFactory {
	
	public static BookStoreService getBookStoreService() {
		return new BookStoreServiceImpl(getBookStoreDao());
	}

	public static BookStoreDao getBookStoreDao() {
		return new BookStoreDaoImpl();
	}
}
