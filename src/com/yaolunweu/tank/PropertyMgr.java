package com.yaolunweu.tank;

import java.io.IOException;
import java.util.Properties;
/**
 * ��Ҫ�������ļ��Ĺ���
 * @author С��
 *
 */
public class PropertyMgr {
	/**
	 * Properties ���ʾ��һ���־õ����Լ���Properties �ɱ��������л�����м��ء������б���ÿ���������Ӧֵ����һ���ַ����� 
	 *һ�������б�ɰ�����һ�������б���Ϊ���ġ�Ĭ��ֵ�������δ����ԭ�е������б������������Լ����������ڶ��������б� 
	 *��Ϊ Properties �̳��� Hashtable�����Կɶ� Properties ����Ӧ�� put �� putAll ��������������ʹ����������������Ϊ������������߲��������ֵ���� String ���
	 *�෴��Ӧ��ʹ�� setProperty ����������ڡ�����ȫ���� Properties ���󣨼������� String �ļ���ֵ���ϵ��� store �� save ������
	 *��õ��ý�ʧ�ܡ����Ƶأ�����ڡ�����ȫ���� Properties ���󣨼������� String �ļ����ϵ��� propertyNames �� list ��������õ��ý�ʧ�ܡ�
	 *
	 *����Ϊ��Ա��������ÿ�ε��ö�ȥnew ��ʡ�ڴ档
	 */
	static Properties pro = new Properties();
	/**
	 * ��ȡ�����ļ���
	 */
	static {
		try {
			pro.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config/tank.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 *private ���ι��췽�����಻���ⲿ��ʵ������
	 */
	private PropertyMgr(){};
	
	/**
	 * ���ô˷������������ļ��ж�Ӧ��ֵ��
	 * @param key	�����ļ��еļ�ֵ��String���͡�
	 * @return	���ؼ�ֵ��Ӧ��ֵ��int���͡�
	 */
	public static int pros(String key){
		return Integer.parseInt(pro.getProperty(key));
	}
}
