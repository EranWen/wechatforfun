package com.eran.weixin.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author eranw
 *
 */
public class RedPacketUtil {
	//最小红包额度
	private static final double MINMONEY = 0.01;
	//最大红包额度
	private static final double MAXMONEY = 200.00;
	//每个红包最大是平均值的倍数
	private static final double TIMES = 2.1;
	/**
	 * 拆分红包
	 * @param money
	 * @param count
	 * @return
	 */
	public List<String> splitRedPackets(double money, int count) {
		if (!isRight(money, count)) {
			return null;
		}
		List<String> list = new ArrayList<String>();
		//红包最大金额为平均金额的TIMES倍
		double max = (int) (money * TIMES / count);
		max = max > MAXMONEY ? MAXMONEY : max;
		for (int i = 0; i < count; i ++) {
			double one = random(money, MINMONEY, max, count - i);
			DecimalFormat df = new DecimalFormat("#.00");
			list.add(df.format(one));
			money -= one;
		}
		/*DecimalFormat df = new DecimalFormat("#.00");
		df.format(list);*/
		return list;
	}
	
	/**
	 * 随机红包额度
	 * @param money
	 * @param minS
	 * @param maxS
	 * @param count
	 * @return
	 */
	private double random(double money, double minS, double maxS, int count) {
		//红包数量为1，直接返回金额
		if (count == 1) {
			return money;
		}
		//如果最大金额和最小金额相等，直接返回金额
		if (minS == maxS) {
			return money;
		}
		double max = maxS > money ? money : maxS;
		//随机产生一个红包
		double one = ((double)(Math.random() * (max - minS) + minS)) % max + 1;
		double money1 = money - one;
		//判断这种分配方案是否正确
		if (isRight(money1, count - 1)) {
			return one;
		} else {
			double avg = money1 / (count - 1);
			if (avg < MINMONEY) {
				//递归调用，修改红包最大金额
				return random(money, minS, one, count);
			} else if (avg > MAXMONEY) {
				//递归调用，修改红包最小金额
				return random(money, one, maxS, count);
			}
		}
 		return one;
	}
	
	/**
	 * 此种红包是否合法
	 * @param money
	 * @param count
	 * @return
	 */
	private boolean isRight(double money, int count) {
		double avg = money / count;
		if (avg < MINMONEY) {
			System.out.println("红包数额太小，请重新设置");
			return false;
		}
		if (avg > MAXMONEY) {
			System.out.println("红包数额太大，请重新设置");
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		RedPacketUtil util = new RedPacketUtil();
		double redPacket;
		int person;
		Scanner in = new Scanner(System.in);
		do {
			System.out.println("输入红包金额：");
			redPacket = in.nextDouble();
		} while(redPacket <= 0.01);
		
		do {
			System.out.println("请输入人数：");
			person = in.nextInt();
		} while(person < 1);
		System.out.println(util.splitRedPackets(redPacket, person));
		in.close();
	}

}
