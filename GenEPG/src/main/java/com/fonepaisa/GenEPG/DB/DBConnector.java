package com.fonepaisa.GenEPG.DB;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("DBConnector")
public class DBConnector {
		@Autowired
		@Qualifier("sessionFactory")
		private SessionFactory sessionFactory;
		static Logger log = Logger.getLogger(DBConnector.class.getName());
		public Session getCurrentSession() {
			log.info("OPENCONNECTIONS = " + sessionFactory.getStatistics().getConnectCount()
					+ " ," + sessionFactory.toString());
			return sessionFactory.getCurrentSession();
		}
		public Session createSession() {
			log.info("OPENCONNECTIONS = " + sessionFactory.getStatistics().getConnectCount()
					+ " ," + sessionFactory.toString());
			return sessionFactory.openSession();
		}
		public void  closeSession(Session session) {
			log.info("OPENCONNECTIONS = " + sessionFactory.getStatistics().getConnectCount()
					+ " ," + sessionFactory.toString());
			session.close();
		}

		
}
