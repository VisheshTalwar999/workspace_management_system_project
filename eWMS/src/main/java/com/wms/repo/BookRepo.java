package com.wms.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wms.models.Book;
import com.wms.models.BookProjection;

@Repository
public class BookRepo {
	@Autowired
	private EntityManager entityManager;
	
//	public List<Book> getBooks(String name) {
//		Session session= entityManager.unwrap(Session.class);
//		List<Book> list=session.createQuery("select b from Book b where b.name like :name",Book.class)
//		.setParameter("name", "%"+name+"%")
//		.list();
//		return list;
//	}
	
	//JPA Projection or a DTO (Data Transfer Object) instead of fetching entire Book entities
	public List<BookProjection> getBooks(String name) {
		Session session= entityManager.unwrap(Session.class);
		List<BookProjection> list = session.createQuery(
		        "select new com.incapp.models.BookProjection(b.name, b.price, b.aname, b.pname) from Book b where b.name like :name", BookProjection.class)
		    .setParameter("name", "%" + name + "%")
		    .list();
		return list;
	}
	public Book getBook(String name) {
		Session session = entityManager.unwrap(Session.class);
        Book book = (Book) session.createQuery("FROM Book WHERE name = :name")
                                  .setParameter("name", name)
                                  .uniqueResult();
        return book;
	}
	public List<String> getBookNames() {
		Session session= entityManager.unwrap(Session.class);
		List<String> list=session.createQuery("select b.name from Book b ",String.class)
		.list();
		
		return list;
	}
	public List<BookProjection> getBooks() {
		Session session= entityManager.unwrap(Session.class);
		List<BookProjection> list=session.createQuery("select new com.incapp.models.BookProjection(b.name, b.price, b.aname, b.pname) from Book b ",BookProjection.class)
		.list();
		return list;
	}
	@Transactional
	public boolean addBook(Book book) {
		Session session= entityManager.unwrap(Session.class);
		session.persist(book);
		return true;
//		Book b=session.get(Book.class, book.getId());
//		if(b==null) {
//			session.persist(book);
//			return true;
//		}else {
//			return false;
//		}
		
	}
	@Transactional
	public boolean updateBookImage(String name,byte[] image) {
		Session session= entityManager.unwrap(Session.class);
		Book book=session.get(Book.class, name);
		if(book==null) {
			return false;
		}else {
			session.persist(book);
			return true;
		}
	}
	@Transactional
	public boolean updateBook(Book book){
		Session session= entityManager.unwrap(Session.class);
		Book b=session.get(Book.class, book.getId());
		if(b==null) {
			return false;
		}else {
			b.setPrice(book.getPrice());
			b.setAname(book.getAname());
			b.setPname(book.getPname());
			session.persist(b);
			return true;
		}
	}
	@Transactional
	public boolean updateBookByName(String name, Book book) {

		Session session = entityManager.unwrap(Session.class);
        Book existingBook = (Book) session.createQuery("FROM Book WHERE name = :name")
                                          .setParameter("name", name)
                                          .uniqueResult();
        if (existingBook == null) {
            return false;
        } else {
            existingBook.setPrice(book.getPrice());
            existingBook.setAname(book.getAname());
            existingBook.setPname(book.getPname());
            session.merge(existingBook);
            return true;
        }
	}
	@Transactional
	public boolean deleteBook(String name){
		Session session= entityManager.unwrap(Session.class);
		Book book=session.get(Book.class, name);
		if(book==null) {
			return false;
		}else {
			session.remove(book);
			return true;
		}
	}
	
}
