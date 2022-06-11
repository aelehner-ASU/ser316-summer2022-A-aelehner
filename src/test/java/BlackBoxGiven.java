package test.java;

import main.java.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;

import main.java.Cart;
import main.java.Cart0;
import main.java.Cart1;
import main.java.Cart2;
import main.java.Cart3;
import main.java.Cart4;
import main.java.Cart5;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class BlackBoxGiven {

    private Class<Cart> classUnderTest;

    @SuppressWarnings("unchecked")
    public BlackBoxGiven(Object classUnderTest) {
        this.classUnderTest = (Class<Cart>) classUnderTest;
    }

    // Define all classes to be tested
    @Parameterized.Parameters
    public static Collection<Object[]> cartClassUnderTest() {
        Object[][] classes = {
            {Cart0.class},
            {Cart1.class},
            {Cart2.class},
            {Cart3.class},
            {Cart4.class},
            {Cart5.class}
        };
        return Arrays.asList(classes);
    }

    private Cart createCart(int age) throws Exception {
        Constructor<Cart> constructor = classUnderTest.getConstructor(Integer.TYPE);
        return constructor.newInstance(age);
    }

    // Carts
    Cart cart1, cartAO, cartAU, cartD, cartM, cartF, cartPS, cartPT, cartAFO;
    double cart1Expected, cart1ex, cartAOex, cartDex, 
    	cartMex, cartFex, cartPSex, cartPTex, cartAFOex;
    
    @org.junit.Before
    public void setUp() throws Exception {
        // cart created with an age 40 shopper
        cart1 = createCart(40);
        for (int i = 0; i < 2; i++) {
            cart1.addItem(new Alcohol());
        }
        for(int i = 0; i < 3; i++) {
            cart1.addItem(new Dairy());
        }
        for(int i = 0; i < 4; i++) {
            cart1.addItem(new Meat());
        }
        cart1Expected = 70.2;
        
        //Alcohol Over Age
        cartAO = createCart(21);
        cartAO.addItem(new Alcohol());
        cartAOex = 8.64;
        
        //Alcohol Under Age
        cartAU = createCart(20);
        cartAU.addItem(new Alcohol());
        
        //Dairy
        cartD = createCart(21);
        cartD.addItem(new Dairy());
        cartDex = 3.24;
        
        //Meat
        cartM = createCart(21);
        cartM.addItem(new Meat());
        cartMex = 10.8;
        
        //Frozen Food
        cartF = createCart(21);
        cartF.addItem(new FrozenFood());
        cartFex = 5.4;
        
        //Produce Single
        cartPS = createCart(21);
        cartPS.addItem(new Produce());
        cartPSex = 2.16;
        
        //Produce Triple
        cartPT = createCart(21);
        for(int i = 0; i < 3; i++) {
            cartPT.addItem(new Produce());
        }
        cartPTex = 5.4;
        
        //Alcohol Over Age + Frozen Food
        cartAFO = createCart(21);
        cartAFO.addItem(new Alcohol());
        cartAFO.addItem(new FrozenFood());
        cartAFOex = 10.8;
    }  

    // Tests
    @Test
    public void calcCostCart1() throws UnderAgeException {
        double amount = cart1.calcCost();
        assertEquals(cart1Expected, amount, .01);
    }
    
    @Test
    public void calcCostCartAO() throws UnderAgeException {
        double amount = cartAO.calcCost();
        assertEquals(cartAOex, amount, .01);
    }
    
    @Test
    public void calcCostCartAU() throws UnderAgeException {
    	Exception exception = assertThrows(UnderAgeException.class, () -> cartAU.calcCost());
    	assertEquals("The User is not of age to purchase alcohol!", exception.getMessage());
    }
    
    @Test
    public void calcCostCartD() throws UnderAgeException {
        double amount = cartD.calcCost();
        assertEquals(cartDex, amount, .01);
    }
    
    @Test
    public void calcCostCartM() throws UnderAgeException {
        double amount = cartM.calcCost();
        assertEquals(cartMex, amount, .01);
    }
    
    @Test
    public void calcCostCartF() throws UnderAgeException {
        double amount = cartF.calcCost();
        assertEquals(cartFex, amount, .01);
    }
    
    @Test
    public void calcCostCartPS() throws UnderAgeException {
        double amount = cartPS.calcCost();
        assertEquals(cartPSex, amount, .01);
    }
    
    @Test
    public void calcCostCartPT() throws UnderAgeException {
        double amount = cartPT.calcCost();
        assertEquals(cartPTex, amount, .01);
    }
    
    @Test
    public void calcCostCartAFO() throws UnderAgeException {
        double amount = cartAFO.calcCost();
        assertEquals(cartAFOex, amount, .01);
    }   
}