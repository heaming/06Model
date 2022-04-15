package com.model2.mvc.service.purchase.test;

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
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })
public class PurchaseServiceTest {

	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;


	//@Test
	public void testAddPurchase() throws Exception {
		
		User user = new User();
		user.setUserId("user01");
		user.setUserName("user01");
		user.setPassword("testPasswd");
		user.setSsn("1111112222222");
		user.setPhone("111-2222-3333");
		user.setAddr("경기도");
		user.setEmail("test@test.com");
		
		Product product = new Product();
		product.setProdCode(102);
		product.setSellerId("user05");
		product.setProdName("투썸 아메리카노");
		product.setProdDetail("투썸 아메리카노 왜 스벅 따라해? 왜 비싸졌냐구ㅜㅜ");
		product.setDueDate("20220505");
		product.setCost(4500);
		product.setPrice(3000);
		product.setFileName("test.jpg");
		product.setProdNo(10004);

		Purchase purchase = new Purchase();
		purchase.setBuyer(user);
		purchase.setDivyAddr("서울");
		purchase.setDivyMessage("빨리 연락줘");
		purchase.setPaymentOption("1");
		purchase.setPurchaseProd(product);
		purchase.setReceiverName("혜미");
		purchase.setReceiverPhone("0101111");
		purchase.setTranCode("001");
		
		purchaseService.addPurchase(purchase);
		
		purchase = purchaseService.getPurchase(10008);
		
		System.out.println("[addPruchase] :: "+purchase);
		Assert.assertEquals("user01", purchase.getBuyer().getUserId());
	}

	
	//@Test
	public void testGetPurchase() throws Exception {
		
		Purchase purchase = new Purchase();
		
		purchase = purchaseService.getPurchase(10004);
		
		Assert.assertEquals("혜미", purchase.getReceiverName());
		
		System.out.println("[getProduct] :: "+purchase);
	}
	
	
	//@Test
	public void testUpdatePurchase() throws Exception {
		
		Purchase purchase = purchaseService.getPurchase(10002);		
		Assert.assertEquals("user01", purchase.getBuyer().getUserId());
		
		purchase.setDivyMessage("changeMsg");
		
		purchaseService.updatePurchase(purchase);
		
		purchase = purchaseService.getPurchase(10002);
		
		System.out.println("[updatePurchase] :: "+purchase);
		Assert.assertEquals("changeMsg", purchase.getDivyMessage());			
	}
	
	
	//@Test
	public void testUpdateTranCode() throws Exception {
		
		Purchase purchase = purchaseService.getPurchase(10002);		
		Assert.assertEquals("user01", purchase.getBuyer().getUserId());
		
		purchase.setTranCode("003");
		
		purchaseService.updateTranCode(purchase);
		
		Assert.assertEquals("003", purchaseService.getPurchase(10002).getTranCode());
		
		System.out.println(purchase);
		
	}

	
	//@Test
	public void testGetSaleList() throws Exception {
		
		Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);	 	
	 	//TODO :search condition을 ""으로 해야함
	 	search.setSearchCondition("");
	 	search.setSearchKeyword("");
	 	Map<String,Object> map = purchaseService.getSaleList(search);
	 	
	 	List<Object> list = (List<Object>) map.get("list");	 	
	 	Assert.assertEquals(3, list.size());
	 	System.out.println(list);
	 	
	 	System.out.println("[getSaleList] :: "+list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("");
	 	map = purchaseService.getSaleList(search);
	 	
	 	list = (List<Object>) map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
	 	System.out.println(list);
	 	
	 	totalCount = (Integer) map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	}
	
	
	//@Test
	public void testGetPurchaseList() throws Exception {
		
		Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = purchaseService.getPurchaseList(search, "testUserId");
	 	
	 	List<Object> list = (List<Object>) map.get("list");
	 	Assert.assertEquals(2, list.size());
	 	
	 	System.out.println("[getPurchaseList] :: "+list);
		
	 	Integer totalCount = (Integer) map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = purchaseService.getPurchaseList(search, "");
	 	
	 	list = (List<Object>) map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	}
	
	
	//@Test
	public void testGetUserSaleList() throws Exception {
		
		Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = purchaseService.getUserSaleList(search, "user15");
	 	
	 	List<Object> list = (List<Object>) map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
	 	System.out.println("[getUserSaleList] :: "+list);
		
	 	Integer totalCount = (Integer) map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = purchaseService.getUserSaleList(search, "");
	 	
	 	list = (List<Object>) map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
		
		
	}
	
}


