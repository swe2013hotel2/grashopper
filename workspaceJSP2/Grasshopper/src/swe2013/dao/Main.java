package swe2013.dao;

import java.util.Calendar;
import java.util.Date;

import swe2013.user.Hotellier;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String password="12345678";
		Hotellier h = new Hotellier("Franz", "hdfhdhdh","isdhfiudsh", "fdsghfiusd@gmx.at", "HHDHDHDH", 4545, "sfdjsdo", "dfjhfodisjf", "fdsiufhiudsh", true, password);
		h.encodePassword(password);
		//System.out.print(password);
		
		
		Calendar cal = Calendar.getInstance();
		cal.set(2013, 12, 12 );
		Date bd = cal.getTime();
		
		cal.set(2013, 12, 20 );
		Date ed = cal.getTime();
		
		LocationDAO locDAO = new SqlLocationDAO();
		long[] roomids =  locDAO.hotelsWithFreeRooms( bd, ed, 1, 10000);
		
		for(int i=0;i<roomids.length;i++)
			System.out.println(roomids[i]);
	}

}
