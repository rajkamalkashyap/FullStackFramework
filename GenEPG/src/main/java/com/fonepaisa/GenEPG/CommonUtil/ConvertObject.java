package com.fonepaisa.GenEPG.CommonUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;


public class ConvertObject {
	// converting SimpleExample object to byte[].
	private static Logger log = Logger.getLogger(ConvertObject.class.getName());

	public static byte[] getByteArrayObject(Object simpleExample) {

		byte[] byteArrayObject = null;
		try {

			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(simpleExample);

			oos.close();
			bos.close();
			byteArrayObject = bos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			return byteArrayObject;
		}
		return byteArrayObject;
	}

	// converting byte[] to SimpleExample
	public static Object getJavaObject(byte[] convertObject) {
		Object objSimpleExample = null;

		ByteArrayInputStream bais;
		ObjectInputStream ins;
		try {

			bais = new ByteArrayInputStream(convertObject);

			ins = new ObjectInputStream(bais);
			objSimpleExample = (Object) ins.readObject();

			ins.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return objSimpleExample;
	}

}
