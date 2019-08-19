package com.tps.demo.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import java.security.GeneralSecurityException;


import com.google.api.services.books.Books;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.books.Books.Volumes.List;
import com.google.api.services.books.model.Volume;
import com.google.api.services.books.model.Volumes;
import com.google.api.services.books.model.Volume.VolumeInfo;



import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;


import com.tps.demo.models.BookItem;

@RestController
public class BookInfoResource {

    @GetMapping("/books/{query}")
	public java.util.List<BookItem> volumenQuery(@PathVariable("query") String query)
			throws GeneralSecurityException, IOException, NullPointerException {

		java.util.List<BookItem> bookItems = new java.util.ArrayList<BookItem>();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
		final Books books = new Books(GoogleNetHttpTransport.newTrustedTransport(), jsonFactory, null);
		List volumesList = books.volumes().list(query);
		Volumes volumes = volumesList.execute();
		System.out.println(volumes.getTotalItems());
		String author;
		String descripcion;
		String id;
		for (Volume volume : volumes.getItems()) {
			id = volume.getId();
			Volume.VolumeInfo volumeInfo = volume.getVolumeInfo();
			String title = volumeInfo.getTitle();
			java.util.List<String> authors = volumeInfo.getAuthors();
			String description = volumeInfo.getDescription();
			if (authors != null && !authors.isEmpty()) {
				System.out.print("Author(s): ");
				author = authors.get(0);
				for (int i = 0; i < authors.size(); ++i) {
					System.out.print(authors.get(i));
					if (i < authors.size() - 1) {
						System.out.print(", ");
					}
				}
				System.out.println();
			} else {
				author = "Autor no Disponible";
			}
			if (description != null && description.length() > 0) {
				descripcion = description;
			} else {
				descripcion = "Descripcion No Disponible";
			}
			BookItem bookItem = new BookItem(id, title, descripcion, author);
			bookItems.add(bookItem);
		}
		return bookItems;
	}

    @RequestMapping("/singlebooks/{id}")
	public BookItem  getBookById(@PathVariable("id") String id) throws GeneralSecurityException, IOException {
		JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
		String author;
		String desc;
		String title;
		final Books books = new Books(GoogleNetHttpTransport.newTrustedTransport(), jsonFactory, null);
		Books.Volumes.Get volume = books.volumes().get(id);
		Volume volumeById = volume.execute();
		VolumeInfo volumeInfo = volumeById.getVolumeInfo();
		author = volumeInfo.getAuthors().get(0);
		desc = volumeInfo.getDescription();
		title = volumeInfo.getTitle();
		return new BookItem(id, title, desc, author);
	}

}
