package com;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tia102g3.food.model.FoodRepository;
import com.tia102g3.foodlist.model.FoodListRepository;
import com.tia102g3.healthstatus.model.HealthStatusRepository;
import com.tia102g3.likefood.model.LikeFoodRepository;
import com.tia102g3.likefood.model.LikeFoodVO;
import com.tia102g3.membermenulist.model.MemberMenuListRepository;
import com.tia102g3.menu.model.MenuRepository;

@SpringBootApplication
public class Test_Application_CommandLineRunnerMark implements CommandLineRunner {
    
	@Autowired
	FoodRepository foodRepository;
	
	@Autowired
	FoodListRepository foodListRepository;
	
	@Autowired
	HealthStatusRepository healthStatusRepository;
	
	@Autowired
	LikeFoodRepository likeFoodRepository;
	
	@Autowired
	MenuRepository menuRepository;
	
	@Autowired
	MemberMenuListRepository memberMenuListRepository;
	
//	@Autowired
//    private SessionFactory sessionFactory;
	
	public static void main(String[] args) {
        SpringApplication.run(Test_Application_CommandLineRunnerMark.class);
    }

    @Override
    public void run(String...args) throws Exception {
        
        //● 查詢-findByPrimaryKey (多方emp2.hbm.xml必須設為lazy="false")(優!)
//      Optional<FoodVO> optional = foodRepository.findById(44);
//      FoodVO foodVO4 = optional.get();
//      System.out.println(foodVO4.getFoodName());
        
        
        
        // insert
//        FoodVO foodVO1 = new FoodVO();
//        foodVO1.setFoodName("地瓜39");
//        foodVO1.setFoodTypeNumber(1);
//        foodVO1.setFoodCalories(3939);
//        foodRepository.save(foodVO1);
        
        // update
//        FoodVO foodVO2 = new FoodVO();
//        foodVO2.setFoodNumber(44);
//        foodVO2.setFoodName("芋頭44");
//        foodVO2.setFoodTypeNumber(1);
//        foodVO2.setFoodCalories(4444);
//        foodRepository.save(foodVO2);
        
        // delete
//        FoodVO foodVO3 = new FoodVO();
//        foodRepository.deleteById(39);
        

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
		
		//----------------Food List  getAll--------------------
        
//		List<FoodListVO> listFoodList = foodListRepository.findAll();
//		System.out.println("===== Test get all food list =====");
//        for (FoodListVO foodListVO : listFoodList) {
//            
//            System.out.print(foodListVO.getFoodListSN() + ",");
//            System.out.print(foodListVO.getMenuVO().getMenuNumber() + ",");
//            System.out.print(foodListVO.getMenuVO().getImageNumber() + ",");
//            System.out.print(foodListVO.getMenuVO().getMenuImage() + ",");
////            System.out.print(foodListVO.getMenuNumber() + ",");
////            System.out.print(foodListVO.getFoodVO().getFoodName() + ",");
//            System.out.print(foodListVO.getFoodVO().getFoodNumber() + ",");
//            System.out.print(foodListVO.getFoodVO().getFoodName() + ",");
//            System.out.print(foodListVO.getFoodWeight() + ",");
//            System.out.print(foodListVO.getFoodVO().getFoodCalories() + ",");
//            System.out.println();
//        }

        // insert
//      FoodVO foodVO1 = new FoodVO();
//      foodVO1.setFoodName("地瓜39");
//      foodVO1.setFoodTypeNumber(1);
//      foodVO1.setFoodCalories(3939);
//      foodRepository.save(foodVO1);
        
      
//        List<FoodListVO> listFoodList = foodListRepository.findByMenuNum(2);
//        System.out.println("===== Test Find food list by MenuNumber =====");
//        for (FoodListVO foodListVO : listFoodList) {
//            
//            System.out.print(foodListVO.getMenuVO().getMenuNumber() + ",");
//            System.out.print(foodListVO.getMenuVO().getImageNumber() + ",");
//            System.out.print(foodListVO.getMenuVO().getMenuImage() + ",");
//            System.out.print(foodListVO.getFoodVO().getFoodNumber() + ",");
//            System.out.print(foodListVO.getFoodVO().getFoodName() + ",");
//            System.out.print(foodListVO.getFoodWeight() + ",");
//            System.out.print(foodListVO.getFoodVO().getFoodCalories() + ",");
//            System.out.println();
//        }
        
        
        
        
		
        //-----------------Health Status  getAll-------------------
//        List<HealthStatusVO> healthStatusList = healthStatusRepository.findAll();
//        for (HealthStatusVO healthStatusVOList : healthStatusList) {
//            
//            System.out.print(healthStatusVOList.getHealthSN() + ",");
//            System.out.print(healthStatusVOList.getMemberID() + ",");
//            System.out.print(healthStatusVOList.getHeight() + ",");
//            System.out.print(healthStatusVOList.getWeight() + ",");
//            System.out.print(healthStatusVOList.getBmi() + ",");
//            System.out.print(healthStatusVOList.getBmr() + ",");
//            System.out.print(healthStatusVOList.getTdee() + ",");
//            System.out.print(healthStatusVOList.getIntensity() + ",");
//            System.out.print(healthStatusVOList.getCreate_dt() + ",");
//            System.out.println();
//        }
        
        
//        LikeFoodVO likeFoodVO = new LikeFoodVO();
//        likeFoodVO.setMemberID(2);
//        likeFoodVO.setFoodNumber(5);
//        likeFoodVO.setFoodPreference(true);
//        likeFoodRepository.save(likeFoodVO);
        
        
        
        //-----------------Like food getAll-------------------
        List<LikeFoodVO> likeFoodList = likeFoodRepository.findAll();
        for (LikeFoodVO likefood : likeFoodList) {
            
            System.out.print(likefood.getMemberID() + ",");
            System.out.print(likefood.getFoodNumber() + ",");
            System.out.print(likefood.getFoodPreference() + ",");
            System.out.println();
        }
        
        
      //-----------------Menu getAll-------------------
//        List<MenuVO> menuList = menuRepository.findAll();
//        for (MenuVO menu : menuList) {
//            
//            System.out.print(menu.getMenuNumber() + ",");
//            System.out.print(menu.getImageNumber() + ",");
//            System.out.print(menu.getMenuImage() + ",");
//            System.out.println();
//        }
        
		
        //-----------------MemberMenuList getAll-------------------
//        List<MemberMenuListVO> memberMenuList = memberMenuListRepository.findAll();
//        for (MemberMenuListVO memberMenu : memberMenuList) {
//            
//            System.out.print(memberMenu.getMenuListSN() + ",");
//            System.out.print(memberMenu.getHealthSN() + ",");
//            System.out.print(memberMenu.getMenuNumber() + ",");
//            System.out.print(memberMenu.getMenuDate() + ",");
//            System.out.print(memberMenu.getMemberID() + ",");
//            System.out.println();
//        }
        
        
    }
}