package com.eran.test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

import org.junit.Test;

/**
 * 测试double类型保留两位小数的方法
 * @author eranw
 *
 */
public class TestFormat {
	
	double f = 11121321231.3423;
//	@Test
	public void m1() {
		BigDecimal bg = new BigDecimal(f);
		double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		System.out.println(f1);
	}
	
	/**
	 * DecimalFormat转换最简便
	 */
//	@Test 
	public void m2() {
		DecimalFormat df = new DecimalFormat("#.00");
		System.out.println(df.format(f));
	}
	
	/**
	 * String.format打印最简便
	 */
//	@Test
	public void m3() {
		System.out.println(String.format("%.2f", f));
	}
	
//	@Test
	public void m4() {
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(2);
		System.out.println(nf.format(f));
	}
	
	@Test
	public void random() {
		Random r = new Random(10);
		int a = r.nextInt();
		System.out.println(a);
	}
	
	@Test
	public void applet() {
		
	}
	
	
	

}
