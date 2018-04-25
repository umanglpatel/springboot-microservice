package com.demo.product.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.demo.domain.data.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "product-test")
public class ProductControllerTest {

	private MockMvc mvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {
		// mvc = MockMvcBuilders.standaloneSetup(new ProductController()).build();
		mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void test() throws Exception {
		mvc.perform(get("/v1.0/products/test")).andExpect(status().isOk()).andExpect(content().string("hello world!"));
	}

	@Test
	public void testAddProduct() throws Exception {
		Product p = new Product("shoes", "nike", "footwear");

		ResultActions result = insertProduct(p);

		result.andExpect(status().isCreated()).andExpect(jsonPath("$.name").value("shoes"))
				.andExpect(jsonPath("$.brand", is("nike")));
	}

	@Test
	public void testUpdateProduct() throws Exception {
		Product p = new Product("shoes", "nike", "footwear");
		insertProduct(p);
		p.setBrand("adidas");
		mvc.perform(put("/v1.0/products/1").contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(p))
				.accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isCreated())
				.andExpect(jsonPath("$.name").value("shoes")).andExpect(jsonPath("$.brand", is("adidas")));
	}

	private ResultActions insertProduct(Product p) throws Exception {
		return mvc.perform(post("/v1.0/products/").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(asJsonString(p)).accept(MediaType.APPLICATION_JSON_VALUE));
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
