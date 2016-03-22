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
	//��С������
	private static final double MINMONEY = 0.01;
	//��������
	private static final double MAXMONEY = 200.00;
	//ÿ����������ƽ��ֵ�ı���
	private static final double TIMES = 2.1;
	/**
	 * ��ֺ��
	 * @param money
	 * @param count
	 * @return
	 */
	public List<String> splitRedPackets(double money, int count) {
		if (!isRight(money, count)) {
			return null;
		}
		List<String> list = new ArrayList<String>();
		//��������Ϊƽ������TIMES��
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
	 * ���������
	 * @param money
	 * @param minS
	 * @param maxS
	 * @param count
	 * @return
	 */
	private double random(double money, double minS, double maxS, int count) {
		//�������Ϊ1��ֱ�ӷ��ؽ��
		if (count == 1) {
			return money;
		}
		//�����������С�����ȣ�ֱ�ӷ��ؽ��
		if (minS == maxS) {
			return money;
		}
		double max = maxS > money ? money : maxS;
		//�������һ�����
		double one = ((double)(Math.random() * (max - minS) + minS)) % max + 1;
		double money1 = money - one;
		//�ж����ַ��䷽���Ƿ���ȷ
		if (isRight(money1, count - 1)) {
			return one;
		} else {
			double avg = money1 / (count - 1);
			if (avg < MINMONEY) {
				//�ݹ���ã��޸ĺ�������
				return random(money, minS, one, count);
			} else if (avg > MAXMONEY) {
				//�ݹ���ã��޸ĺ����С���
				return random(money, one, maxS, count);
			}
		}
 		return one;
	}
	
	/**
	 * ���ֺ���Ƿ�Ϸ�
	 * @param money
	 * @param count
	 * @return
	 */
	private boolean isRight(double money, int count) {
		double avg = money / count;
		if (avg < MINMONEY) {
			System.out.println("�������̫С������������");
			return false;
		}
		if (avg > MAXMONEY) {
			System.out.println("�������̫������������");
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
			System.out.println("��������");
			redPacket = in.nextDouble();
		} while(redPacket <= 0.01);
		
		do {
			System.out.println("������������");
			person = in.nextInt();
		} while(person < 1);
		System.out.println(util.splitRedPackets(redPacket, person));
		in.close();
	}

}
