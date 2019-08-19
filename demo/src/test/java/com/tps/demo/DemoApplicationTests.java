package com.tps.demo;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import com.tps.demo.resources.BookInfoResource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.junit.Before;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	protected MockMvc mockMvc;

	@Before
	public void setUp(){
		final BookInfoResource bookController = new BookInfoResource();
		mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
	}
	@Test
	public void volumenQueryTest() throws Exception {
		String uri = "/books/Principios";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaTypes.ALPS_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertTrue(content.length() >0);
	}	
	@Test
	public void singlebookRequestifExistTest() throws Exception{
		String uri = "/singlebooks/RkX_fETNFnQC";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaTypes.ALPS_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	/*@Test
	public void singlebookRequestifNotExistTest() throws Exception {
		String uri= "/singlebooks/notexits";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaTypes.ALPS_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(500, status);
	}*/
	@Test
	public void contextLoads() {
	}

}
