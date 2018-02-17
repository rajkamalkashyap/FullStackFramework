package com.fonepaisa.GenEPG.DB;

import java.math.BigInteger;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fonepaisa.GenEPG.CommonUtil.Constants;

@Transactional
@Service("CommonDBOperations")
public class CommonDBOperations {

	static Logger log = Logger.getLogger(CommonDBOperations.class.getName());
	@Autowired
	private DBConnector dbConnector;
	public static Boolean insertOrUpdateRecord(Object obj,String status,DBConnector dbcon) throws Exception {
		if(status.equals(Constants.INSERT_FLAG)){
			 dbcon.getCurrentSession().save(obj);
			return true;
		}else if(status.equals(Constants.UPDTAE_FLAG)){
			dbcon.getCurrentSession().update(obj);
			return true;
		}else{
			return false;
		}
	}
	public Boolean insertOrUpdateRecordWithInstantCommit(Object obj,String status) throws Exception {
		if(status.equals(Constants.INSERT_FLAG)){
			Session create_sesion = dbConnector.createSession();
			Transaction transcation = create_sesion.beginTransaction();
			create_sesion.save(obj);
			transcation.commit();
			dbConnector.closeSession(create_sesion);
			return true;
		}else if(status.equals(Constants.UPDTAE_FLAG)){
			dbConnector.getCurrentSession().update(obj);
			return true;
		}else{
			return false;
		}
	}
	
	public static String fetchSeqNum(String sequence_name,DBConnector dbCon){
			Query query = dbCon.getCurrentSession().createSQLQuery(
					"select "+sequence_name+".nextval as num from dual").addScalar("num",
							StandardBasicTypes.BIG_INTEGER);
			BigInteger nextVal = (BigInteger) query.uniqueResult();
			log.info("seq_num fetched "+nextVal);
			return nextVal.toString();
	}

}
