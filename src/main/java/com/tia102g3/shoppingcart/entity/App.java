package com.tia102g3.shoppingcart.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
    			.configure()
    			.build();
    	
    	SessionFactory factory = new MetadataSources(registry)
    			.buildMetadata()
    			.buildSessionFactory();
    	
    	Session session = factory.openSession();
    	Transaction tx = session.beginTransaction();
    	
    	///////////////////交易區間，對資料庫增刪改查的動作///////////////////////
    	
    	ShoppingCartVO shoppingCart = new ShoppingCartVO(1,1,2,10);
    	session.save(shoppingCart);
    	
    	///////////////////交易區間，對資料庫增刪改查的動作///////////////////////
    	
    	tx.commit(); //交易完成後呼叫commit，結束交易
    	session.close(); //關閉session
    	factory.close(); //關閉factory
    	
        
    }
}
