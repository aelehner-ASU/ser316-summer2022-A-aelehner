package test.java;

import main.java.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WhiteBoxGiven {

    Cart cart, cartP2, cartP3, cartP5, cartAO, cartAU, cartA1F1, cartA2F3, cartRM;

    @Before
    public void setUp() throws Exception {
    	cart = new Cart(45);
    	cart.addItem(new Dairy());
    	
    	//Produce
        cartP2 =  new Cart(21);
        for(int i = 0; i < 2; i++) {
            cartP2.addItem(new Produce());
        }
        cartP3 =  new Cart(21);
        for(int i = 0; i < 3; i++) {
            cartP3.addItem(new Produce());
        }
        cartP5 =  new Cart(21);
        for(int i = 0; i < 5; i++) {
            cartP5.addItem(new Produce());
        }
        
        //Alcohol
        cartAO = new Cart(21);
        cartAO.addItem(new Alcohol());
        
        cartAU = new Cart(20);
        cartAU.addItem(new Alcohol());
        
        //Alcohol and Frozen Food
        cartA1F1 = new Cart(21);
        cartA1F1.addItem(new Alcohol());
        cartA1F1.addItem(new FrozenFood());
        
        cartA2F3 = new Cart(21);
        for(int i = 0; i < 2; i++) {
        	cartA2F3.addItem(new Alcohol());
        }
        for(int i = 0; i < 3; i++) {
        	cartA2F3.addItem(new FrozenFood());
        }
    }

    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void addItem() {
    	assertEquals(1, cart.cart.size(), .01);
    }

    @Test
    public void getTaxAZ() {
        assertEquals(4.0, cart.getTax(50, "AZ"), .01);
    }
    
    @Test
    public void getTaxCA() {
        assertEquals(4.5, cart.getTax(50, "CA"), .01);
    }
    
    @Test
    public void getTaxNY() {
        assertEquals(5.0, cart.getTax(50, "NY"), .01);
    }
    
    @Test
    public void getTaxCO() {
        assertEquals(3.5, cart.getTax(50, "CO"), .01);
    }
    
    @Test
    public void getTaxOther() {
        assertEquals(0.0, cart.getTax(50, "XQ"), .01);
    }
    
    @Test
    public void Amount_savedP2() throws UnderAgeException {
        assertEquals(4, cartP2.Amount_saved(), .01);
    }
    
    @Test
    public void Amount_savedP3() throws UnderAgeException {
        assertEquals(5, cartP3.Amount_saved(), .01);
    }
    
    @Test
    public void Amount_savedP5() throws UnderAgeException {
        assertEquals(9, cartP5.Amount_saved(), .01);
    }
    
    @Test
    public void Amount_savedAO() throws UnderAgeException {
        assertEquals(8, cartAO.Amount_saved(), .01);
    }
    
    @Test
    public void Amount_savedAU() throws UnderAgeException {
    	Exception exception = assertThrows(UnderAgeException.class, () -> cartAU.Amount_saved());
    	assertEquals("The User is not of age to purchase alcohol!", exception.getMessage());
    }
    
    @Test
    public void Amount_savedA1F1() throws UnderAgeException {
    	assertEquals(10, cartA1F1.Amount_saved(), .01);
    }
    
    @Test
    public void Amount_savedA2F3() throws UnderAgeException {
    	assertEquals(25, cartA2F3.Amount_saved(), .01);
    }
    

}