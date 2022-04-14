package com.model2.mvc.web.product;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;

@Controller
public class ProductController {

	// field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;


	public ProductController() {
		System.out.println(this.getClass());
	}


	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;

	@Value("#{commonProperties['pageSize']}")
	int pageSize;

	@RequestMapping("/addProductView.do")
	public String addProductView() {

		System.out.println("/addProductView.do");

		return	"redirect:/product/addProductView.jsp";	

	}

	@RequestMapping(value="/addProduct.do", method=RequestMethod.POST)
	public String addProduct(HttpSession session, @ModelAttribute("product") Product product, Model model, RedirectAttributes redirectAttributes) throws Exception {

		System.out.println("/addProduct.do");

		User user = (User) session.getAttribute("user");

		product.setSellerId(user.getUserId());

		productService.addProduct(product);
		
		redirectAttributes.addFlashAttribute("product", product);

		return "redirect:/addProductSuccess.do";		
	}
	
	
	@RequestMapping(value="/addProductSuccess.do")
	public String addProductSuccess() {
		
		System.out.println("/addProductSuccess.do");
		
		return "forward:/product/addProduct.jsp";
	}


	@RequestMapping("/getProduct.do")
	public String getProduct( @RequestParam("prodNo") int prodNo, @RequestParam("menu") String menu, Model model ) throws Exception {

		System.out.println("/getProduct.do");

		Product product = productService.getProduct(prodNo);

		model.addAttribute("product", product);
		model.addAttribute("menu", menu);

		return "forward:/product/getProduct.jsp";
	}

	@RequestMapping("/listProduct.do")
	public String listProduct(@ModelAttribute("search") Search search, @RequestParam("menu") String menu, Model model) throws Exception {

		System.out.println("/listProduct.do");

		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);

		// Business logic 수행
		Map<String , Object> map = productService.getProductList(search);

		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);

		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		model.addAttribute("menu", menu);

		return "forward:/product/listProduct.jsp";

	}

	@RequestMapping("/updateProductView.do")
	public String updateProductView( @RequestParam("prodNo") int prodNo, @RequestParam("menu") String menu, Model model) throws Exception {
		
		System.out.println("/updateProductView.do");
		//Business Logic
		Product product = productService.getProduct(prodNo);
		// Model 과 View 연결
		model.addAttribute("product", product);
		model.addAttribute("menu", menu);
		System.out.println(menu);
		
		return "forward:/product/updateProduct.jsp";
	}

	@RequestMapping("/updateProduct.do")
	public String updateProduct( @ModelAttribute("product") Product product, @RequestParam("menu") String menu, Model model) throws Exception {
		
		System.out.println("/updateProduct.do");
		
		productService.updateProduct(product);

		product = productService.getProduct(product.getProdNo());

		return  "redirect:/getProduct.do?prodNo="+product.getProdNo()+"&menu="+menu;

	}

}
