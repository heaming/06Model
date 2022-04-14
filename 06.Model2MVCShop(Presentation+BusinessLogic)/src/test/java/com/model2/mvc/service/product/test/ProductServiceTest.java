package com.model2.mvc.service.product.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })
public class ProductServiceTest {

	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;


	//@Test
	public void testAddProduct() throws Exception{

		Product product = new Product();
		product.setProdCode(102);
		product.setSellerId("user13");
		product.setProdName("≈ıΩÊ æ∆¿ÃΩ∫π⁄Ω∫");
		product.setProdDetail("Ω≈∏ﬁ¥∫ ¿Ã∏ß ππ¥ı∂Û");
		product.setDueDate("20221010");
		product.setCost(6300);
		product.setPrice(3000);
		product.setFileName("cake.jpg");

		productService.addProduct(product);

		product = productService.getProduct(10011);

		System.out.println("[add Product] :: "+product);
		Assert.assertEquals("≈ıΩÊ æ∆¿ÃΩ∫π⁄Ω∫", product.getProdName());
	}


	//@Test
	public void testGetProduct() throws Exception{

		Product product = new Product();
		product = productService.getProduct(10000);

		Assert.assertEquals("user15", product.getSellerId());

		System.out.println("[getProduct] :: "+product);
	}


	//@Test
	public void testUpdateProduct() throws Exception{

		Product product = productService.getProduct(10004);
		Assert.assertNotNull(product);

		product.setProdName("≈ıΩÊ µ˛±‚ƒ…¿Ã≈©");

		productService.updateProduct(product);

		product = productService.getProduct(10004);

		System.out.println("[updateProduct] :: "+product);
		Assert.assertEquals("≈ıΩÊ µ˛±‚ƒ…¿Ã≈©", product.getProdName());
	}

	//@Test
	public void testGetProductListAll() throws Exception{

		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		Map<String, Object> map = productService.getProductList(search);

		List<Object> list = (List<Object>) map.get("list");
		Assert.assertEquals(3, list.size());

		System.out.println("[getProductListALL] :: "+list);

		Integer totalCount = (Integer) map.get("totalCount");
		System.out.println(totalCount);

		System.out.println("=======================================");

		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("0");
		search.setSearchKeyword("");
		map = productService.getProductList(search);

		list = (List<Object>) map.get("list");
		Assert.assertEquals(3, list.size());

		System.out.println(list);

		totalCount = (Integer) map.get("totalCount");
		System.out.println(totalCount);
	}


	//@Test
	public void testGetProductListByProdName() throws Exception{

		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("0");
		search.setSearchKeyword("≈ıΩÊ");
		Map<String,Object> map = productService.getProductList(search);

		List<Object> list = (List<Object>) map.get("list");
		Assert.assertEquals(3, list.size());

		System.out.println("[getProductListByProdName] :: "+list);

		Integer totalCount = (Integer) map.get("totalCount");
		System.out.println(totalCount);

		System.out.println("=======================================");

		search.setSearchCondition("0");
		search.setSearchKeyword(""+System.currentTimeMillis());
		map = productService.getProductList(search);

		list = (List<Object>) map.get("list");
		Assert.assertEquals(0, list.size());
		System.out.println(list);

		totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);

	}


	//@Test
	public void testGetProductListByProdCode() throws Exception{

		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("101");
		Map<String,Object> map = productService.getProductList(search);

		List<Object> list = (List<Object>) map.get("list");
		Assert.assertEquals(2, list.size());

		System.out.println("[getProductListByProdName] :: "+list);

		Integer totalCount = (Integer) map.get("totalCount");
		System.out.println(totalCount);

		System.out.println("=======================================");

		search.setSearchCondition("0");
		search.setSearchKeyword(""+System.currentTimeMillis());
		map = productService.getProductList(search);

		list = (List<Object>) map.get("list");
		Assert.assertEquals(0, list.size());
		System.out.println(list);

		totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);

	}


	//@Test
	public void testGetProductListByProdNo() throws Exception{

		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("1");
		search.setSearchKeyword("04");
		Map<String,Object> map = productService.getProductList(search);

		List<Object> list = (List<Object>) map.get("list");
		Assert.assertEquals(1, list.size());

		System.out.println("[getProductListByProdNo] :: "+list);

		Integer totalCount = (Integer) map.get("totalCount");
		System.out.println(totalCount);

		System.out.println("=======================================");

		search.setSearchCondition("0");
		search.setSearchKeyword(""+System.currentTimeMillis());
		map = productService.getProductList(search);

		list = (List<Object>) map.get("list");
		Assert.assertEquals(0, list.size());
		System.out.println(list);

		totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);

	}

}
