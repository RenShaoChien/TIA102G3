package com;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tia102g3.order.model.OrderRepository;
import com.tia102g3.order.model.OrderService;
import com.tia102g3.orderdetails.model.OrderDetailsRepository;
import com.tia102g3.orderdetails.model.OrderDetailsVO;
import com.tia102g3.product.model.ProductRepository;


@SpringBootApplication
public class Test_Application_CommandLineRunner_ShoppingCart implements CommandLineRunner {
    
//	@Autowired
//	EmpRepository repository ;
//	
//	@Autowired
//	FoodRepository foodRepository;
	
//	@Autowired
//	ShoppingCartRepository shoppingCartRepository;
	
	@Autowired
	ProductRepository productRepository;
	
//	@Autowired
//	Product_PICRepository product_PICRepository;
//	
//	@Autowired
//	ShoppingCartService shoppingCartService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderDetailsRepository orderDetailsRepository;
	
	
	@Autowired
    private SessionFactory sessionFactory;
	
	public static void main(String[] args) {
        SpringApplication.run(Test_Application_CommandLineRunner_ShoppingCart.class);
    }

    @Override
    public void run(String...args) throws Exception {
    	
    	//● 新增
//    	OrderDetailsVO odv = new OrderDetailsVO();
//    	odv.setProductID(1);
//    	odv.setQuantity(1);
//    	odv.setOrderID(1);		
//    	orderDetailsRepository.save(odv); 
    	
    	
		//● 查詢-getAll (多方emp2.hbm.xml必須設為lazy="false")(優!)
//    	List<OrderDetailsVO> list = orderDetailsRepository.findAll();
//		for (OrderDetailsVO aOrderD : list) {
//			System.out.print(aOrderD.getOrdDtIID() + ",");
//			System.out.print(aOrderD.getProductID() + ",");
//			System.out.print(aOrderD.getQuantity() + ",");
//			System.out.print(aOrderD.getOrderID() + ",");
//			// 注意以下三行的寫法 (優!)
//			System.out.print(aEmp.getDeptVO().getDeptno() + ",");
//			System.out.print(aEmp.getDeptVO().getDname() + ",");
//			System.out.print(aEmp.getDeptVO().getLoc());
//			System.out.println();
//		}
//		System.out.print("成功!!");
    	
    	
    	//● 新增
//    	Member member = new Member();
//    	member.setMemberID(1);
//    	OrderVO orderVO1 = new OrderVO();
//    	orderVO1.setMember(member);
//    	orderVO1.setOrderDate(java.sql.Timestamp.valueOf("2024-08-15 00:00:01"));
//    	orderVO1.setStatus("300");
//    	orderVO1.setTotalPrice(300);		
//    	orderRepository.save(orderVO1); 
    	
		//● 查詢-getAll (多方emp2.hbm.xml必須設為lazy="false")(優!)
//    	List<OrderVO> list = orderRepository.findAll();
//		for (OrderVO aOrder : list) {
//			System.out.print(aOrder.getOrderID() + ",");
//			System.out.print(aOrder.getMember().getMemberID() + ",");
//			System.out.print(aOrder.getOrderDate() + ",");
//			System.out.print(aOrder.getStatus() + ",");
//			System.out.print(aOrder.getTotalPrice() + ",");
//			// 注意以下三行的寫法 (優!)
//			System.out.print(aEmp.getDeptVO().getDeptno() + ",");
//			System.out.print(aEmp.getDeptVO().getDname() + ",");
//			System.out.print(aEmp.getDeptVO().getLoc());
//			System.out.println();
//		}
//		System.out.print("成功!!");
    	

    	//● 新增
//		DeptVO deptVO = new DeptVO(); // 部門POJO
//		deptVO.setDeptno(1);

//		EmpVO empVO1 = new EmpVO();
//		empVO1.setEname("吳永志1");
//		empVO1.setJob("MANAGER");
//		empVO1.setHiredate(java.sql.Date.valueOf("2005-01-01"));
//		empVO1.setSal(new Double(50000));
//		empVO1.setComm(new Double(500));
//		empVO1.setDeptVO(deptVO);
//		repository.save(empVO1);

		//● 修改
//		EmpVO empVO2 = new EmpVO();
//		empVO2.setEmpno(7001);
//		empVO2.setEname("吳永志2");
//		empVO2.setJob("MANAGER2");
//		empVO2.setHiredate(java.sql.Date.valueOf("2002-01-01"));
//		empVO2.setSal(new Double(20000));
//		empVO2.setComm(new Double(200));
//		empVO2.setDeptVO(deptVO);
//		repository.save(empVO2);
		
		//● 刪除   //●●● --> EmployeeRepository.java 內自訂的刪除方法
//		repository.deleteByEmpno(7014);
		
		//● 刪除   //XXX --> Repository內建的刪除方法目前無法使用，因為有@ManyToOne
//		System.out.println("--------------------------------");
//		repository.deleteById(7001);      
//		System.out.println("--------------------------------");

    	//● 查詢-findByPrimaryKey (多方emp2.hbm.xml必須設為lazy="false")(優!)
//    	Optional<EmpVO> optional = repository.findById(7001);
//		EmpVO empVO3 = optional.get();
//		System.out.print(empVO3.getEmpno() + ",");
//		System.out.print(empVO3.getEname() + ",");
//		System.out.print(empVO3.getJob() + ",");
//		System.out.print(empVO3.getHiredate() + ",");
//		System.out.print(empVO3.getSal() + ",");
//		System.out.print(empVO3.getComm() + ",");
//		// 注意以下三行的寫法 (優!)
//		System.out.print(empVO3.getDeptVO().getDeptno() + ",");
//		System.out.print(empVO3.getDeptVO().getDname() + ",");
//		System.out.print(empVO3.getDeptVO().getLoc());
//		System.out.println("\n---------------------");
      
    	
		//● 查詢-getAll (多方emp2.hbm.xml必須設為lazy="false")(優!)
//    	List<EmpVO> list = repository.findAll();
//		for (EmpVO aEmp : list) {
//			System.out.print(aEmp.getEmpno() + ",");
//			System.out.print(aEmp.getEname() + ",");
//			System.out.print(aEmp.getJob() + ",");
//			System.out.print(aEmp.getHiredate() + ",");
//			System.out.print(aEmp.getSal() + ",");
//			System.out.print(aEmp.getComm() + ",");
//			// 注意以下三行的寫法 (優!)
//			System.out.print(aEmp.getDeptVO().getDeptno() + ",");
//			System.out.print(aEmp.getDeptVO().getDname() + ",");
//			System.out.print(aEmp.getDeptVO().getLoc());
//			System.out.println();
//		}


		//● 複合查詢-getAll(map) 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
//		Map<String, String[]> map = new TreeMap<String, String[]>();
//		map.put("empno", new String[] { "7001" });
//		map.put("ename", new String[] { "KING" });
//		map.put("job", new String[] { "PRESIDENT" });
//		map.put("hiredate", new String[] { "1981-11-17" });
//		map.put("sal", new String[] { "5000.5" });
//		map.put("comm", new String[] { "0.0" });
//		map.put("deptno", new String[] { "1" });
//		
//		List<EmpVO> list2 = HibernateUtil_CompositeQuery_Emp3.getAllC(map,sessionFactory.openSession());
//		for (EmpVO aEmp : list2) {
//			System.out.print(aEmp.getEmpno() + ",");
//			System.out.print(aEmp.getEname() + ",");
//			System.out.print(aEmp.getJob() + ",");
//			System.out.print(aEmp.getHiredate() + ",");
//			System.out.print(aEmp.getSal() + ",");
//			System.out.print(aEmp.getComm() + ",");
//			// 注意以下三行的寫法 (優!)
//			System.out.print(aEmp.getDeptVO().getDeptno() + ",");
//			System.out.print(aEmp.getDeptVO().getDname() + ",");
//			System.out.print(aEmp.getDeptVO().getLoc());
//			System.out.println();
//		}
		

		//● (自訂)條件查詢
//		List<EmpVO> list3 = repository.findByOthers(7001,"%K%",java.sql.Date.valueOf("1981-11-17"));
//		if(!list3.isEmpty()) {
//			for (EmpVO aEmp : list3) {
//				System.out.print(aEmp.getEmpno() + ",");
//				System.out.print(aEmp.getEname() + ",");
//				System.out.print(aEmp.getJob() + ",");
//				System.out.print(aEmp.getHiredate() + ",");
//				System.out.print(aEmp.getSal() + ",");
//				System.out.print(aEmp.getComm() + ",");
//				// 注意以下三行的寫法 (優!)
//				System.out.print(aEmp.getDeptVO().getDeptno() + ",");
//				System.out.print(aEmp.getDeptVO().getDname() + ",");
//				System.out.print(aEmp.getDeptVO().getLoc());
//				System.out.println();
//			}
//		} else System.out.print("查無資料\n");
//		System.out.println("--------------------------------");
		
		//● 查詢-getAll (多方emp2.hbm.xml必須設為lazy="false")(優!)
//    	List<FoodVO> listfood = foodRepository.findAll();
//		for (FoodVO foodVO : listfood) {
//			
//			System.out.print(foodVO.getFoodNumber() + ",");
//			System.out.print(foodVO.getFoodTypeNumber() + ",");
//			System.out.print(foodVO.getFoodName() + ",");
//			System.out.print(foodVO.getFoodCalories() + ",");
//			System.out.println();
//		}

//==================================以下為商品圖片================================================//    	
		//● 查詢-getAll (多方emp2.hbm.xml必須設為lazy="false")(優!)
//    	List<Product_PICVO> listProductPic = product_PICRepository.findAll();
//		for (Product_PICVO product_PICVO : listProductPic) {
//			
//			System.out.print(product_PICVO.getProductPicID() + ",");
//			System.out.print(product_PICVO.getProductID() + ",");
//			System.out.print(product_PICVO.getPic());
//			System.out.println();
//		}
		
    	//● 新增
//		Product_PICVO product_PICVO1 = new Product_PICVO();
//		product_PICVO1.setProductID(1);
//		product_PICVO1.setPic(null);
//		byte[] pic = getPictureByteArray("src/main/resources/static/images/protein.png");
//		product_PICRepository.save(product_PICVO1);
//    	System.out.println("成功!!!!");

		//● 修改
//		Product_PICVO product_PICVO2 = new Product_PICVO();
//		product_PICVO2.setProductPicID(4);
//		product_PICVO2.setProductID(1);
//		product_PICVO2.setPic(null);
//		byte[] pic = getPictureByteArray("src/main/resources/static/images/protein.png");
//		product_PICRepository.save(product_PICVO2);
//    	System.out.println("成功!!!!");
    	
    	//● 查詢-findByPrimaryKey (多方emp2.hbm.xml必須設為lazy="false")(優!)
//    	Optional<Product_PICVO> optional = product_PICRepository.findById(7);
//		Product_PICVO product_PICVO3 = optional.get();
//		System.out.print(product_PICVO3.getProductPicID() + ",");
//		System.out.print(product_PICVO3.getProductID() + ",");
//		System.out.print(product_PICVO3.getPic() + ",");
//		System.out.println("\n---------------------");
    	
		//● 刪除   //●●● --> EmployeeRepository.java 內自訂的刪除方法
//		product_PICRepository.deleteById(1);
//    	System.out.println("成功!!!!");
//==================================以上為商品圖片================================================//     
    	
//==================================以下為商品================================================//    	
    			//● 查詢-getAll (多方emp2.hbm.xml必須設為lazy="false")(優!)
//    	    	List<ProductVO> listProduct = productRepository.findAll();
//    			for (ProductVO productVO : listProduct) {
//    				
//    				System.out.print(productVO.getProductID() + ",");
//    				System.out.print(productVO.getProdName() + ",");
//    				System.out.print(productVO.getPrice() + ",");
//    				System.out.print(productVO.getProductQuantity() + ",");
//    				System.out.print(productVO.getIntro());
//    				System.out.println();
//    			}
    			
    	    	//● 新增
//    			ProductVO productVO1 = new ProductVO();
//    			productVO1.setProdName("好吃能量棒");
//    			productVO1.setPrice(100);
//    			productVO1.setProductQuantity(300);
//    			productVO1.setIntro("給你一天的能量");
//    			productRepository.save(productVO1);
//    	    	System.out.println("成功!!!!");
    	
    			//● 修改
//    			ProductVO productVO2 = new ProductVO();
//    			productVO2.setProductID(22);
//    			productVO2.setProdName("好吃能量棒2");
//    			productVO2.setPrice(100);
//    			productVO2.setProductQuantity(300);
//				productVO2.setIntro("給你一天的能量2");
//    			productRepository.save(productVO2);
//    	    	System.out.println("成功!!!!");
    	    	
    	    	//● 查詢-findByPrimaryKey (多方emp2.hbm.xml必須設為lazy="false")(優!)
//    	    	Optional<ProductVO> optional = productRepository.findById(22);
//    			ProductVO productVO3 = optional.get();
//    			System.out.print(productVO3.getProductID() + ",");
//    			System.out.print(productVO3.getProdName() + ",");
//    			System.out.print(productVO3.getPrice() + ",");
//    			System.out.print(productVO3.getProductQuantity());
//    			System.out.print(productVO3.getIntro());
//    			System.out.println("\n---------------------");
    	    	
    			//● 刪除   //●●● --> EmployeeRepository.java 內自訂的刪除方法
//    			productRepository.deleteById(1);
//    	    	System.out.println("成功!!!!");
//==================================以上為商品================================================//     	
    	
    	
    	
//==================================以下為購物車================================================//    	
		//● 查詢-getAll (多方emp2.hbm.xml必須設為lazy="false")(優!)
//    	List<ShoppingCartVO> listShoppingCart = shoppingCartRepository.findAll();
//		for (ShoppingCartVO shoppingCartVO : listShoppingCart) {
//			
//			System.out.print(shoppingCartVO.getShoppingCartID() + ",");
//			System.out.print(shoppingCartVO.getMemberID() + ",");
//			System.out.print(shoppingCartVO.getProductID() + ",");
//			System.out.print(shoppingCartVO.getQuantity() + ",");
//			System.out.println();
//		}
		
    	//● 新增
//		ShoppingCartVO shoppingCartVO1 = new ShoppingCartVO();
//		shoppingCartVO1.setMemberID(1);
//		shoppingCartVO1.setProductID(4);
//		shoppingCartVO1.setQuantity(300);
//		shoppingCartRepository.save(shoppingCartVO1);
    	
		//● 修改
//		ShoppingCartVO shoppingCartVO2 = new ShoppingCartVO();
//		shoppingCartVO2.setShoppingCartID(6);
//		shoppingCartVO2.setMemberID(2);
//		shoppingCartVO2.setProductID(2);
//		shoppingCartVO2.setQuantity(2);
//		shoppingCartRepository.save(shoppingCartVO2);
    	
    	//● 查詢-findByPrimaryKey (多方emp2.hbm.xml必須設為lazy="false")(優!)
//    	Optional<ShoppingCartVO> optional = shoppingCartRepository.findById(1);
//		ShoppingCartVO shoppingCartVO3 = optional.get();
//		System.out.print(shoppingCartVO3.getShoppingCartID() + ",");
//		System.out.print(shoppingCartVO3.getMemberID() + ",");
//		System.out.print(shoppingCartVO3.getProductID() + ",");
//		System.out.print(shoppingCartVO3.getQuantity());
//		System.out.println("\n---------------------");
    	
		//● 刪除   //●●● --> EmployeeRepository.java 內自訂的刪除方法
//		shoppingCartRepository.deleteById(1);
//==================================以上為購物車================================================//   	
    	
		
    }

//	public static byte[] getPictureByteArray(String path)throws IOException {
//
//		FileInputStream fis = new FileInputStream(path);
//		byte[] buffer = new byte[fis.available()];
//		fis.read(buffer);
//		fis.close();
//		return buffer;
//
//	}
    
//==================================以下為訂單================================================// 
    
    
}


