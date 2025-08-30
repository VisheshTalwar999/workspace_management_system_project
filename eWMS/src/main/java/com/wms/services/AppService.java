package com.wms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.wms.models.Book;
import com.wms.models.BookProjection;
import com.wms.repo.AdminRepo;
import com.wms.repo.BookRepo;

@Service
public class AppService {
	@Autowired
	private BookRepo bookRepo;
	@Autowired
	private AdminRepo adminRepo;
	
	public List<BookProjection> getBooks(String name) {
		return bookRepo.getBooks(name);
	}
	public Book getBook(String name) {
		return bookRepo.getBook(name);
	}
//	public String login(Long id,String password) {
//		return adminRepo.login(id, password);
//	}
	public boolean addBook(Book book) {
		System.out.println("inside service layer");
		return bookRepo.addBook(book);
	}
	public boolean updateBookImage(String name,byte[] image) {
		return bookRepo.updateBookImage(name,image);
	}
	public boolean updateBook(Book book) {
		return bookRepo.updateBook(book);
	}
	public boolean updateBookByName(String name,Book book) {
		return bookRepo.updateBookByName(name,book);
	}
	public boolean deleteBook(String name) {
		return bookRepo.deleteBook(name);
	}
	public List<String> getBookNames() {
		return bookRepo.getBookNames();
	}
	public List<BookProjection> getBooks() {
		return bookRepo.getBooks();
	}
//	public String updatePassword(String opass,String npass,String rpass,String id) {
//		return adminRepo.updatePassword(opass,npass,rpass,id);
//	}
//	public String forgetPassword(String email) {
//		return adminRepo.forgetPassword(email);
//	}
}
