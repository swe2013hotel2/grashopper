import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * 
 */

/**
 * @author Simon Anreiter, Andreas Kocman, Vicky Dorothy Moser
 *
 */

public class DataGenerator {
	
	public ArrayList<Object[]> generatePersons(int amountOfClients, int amountOfHotelliers, int amountOfTVB){
		int amountOfPersons=amountOfClients+amountOfHotelliers+amountOfTVB;
		ArrayList<Object[]> personList = new ArrayList<Object[]>();
		ArrayList<String> nameList = this.generateNames(1);
		ArrayList<String> streetList = this.generateNames(2);
		ArrayList<String> countryList = this.generateNames(3);
		ArrayList<String> cityList = this.generateNames(4);
		int maxNames= nameList.size()-1;
		int maxStreets=streetList.size()-1;
		int maxCountries= countryList.size()-1;
		int maxCities= cityList.size()-1;
		
		
		Object[] person=null;
		
		for (int i=0; i<amountOfPersons; i++){
			String firstName 	= nameList.get(DataGenerator.randomNumber(0,maxNames));
			String lastName 	= nameList.get(DataGenerator.randomNumber(0,maxNames));
			String userName 	= firstName+lastName;
			String eMail		= firstName + lastName + 
									(i%2==0? "@gmx.at" : "@gmail.com");
			String password 	= this.generatePassword(firstName + "1");
			String street		= streetList.get(DataGenerator.randomNumber(0,maxStreets)) + 
									" " + String.valueOf(DataGenerator.randomNumber(1, 150));
			int zipCode			= DataGenerator.randomNumber(1000, 9999);
			String city			= cityList.get(DataGenerator.randomNumber(0,maxCities));
			String country		= countryList.get(DataGenerator.randomNumber(0,maxCountries));;
			int sex				= i%2;
			int phone			= DataGenerator.randomNumber(10000000, 999999999);
			int userGroup		= 1;
			if (i >= (amountOfClients+amountOfHotelliers))
				userGroup=3;
			else if (i >= (amountOfClients))
				userGroup=2;

			person=new Object[13];
			person[0]=null;
			person[1]=userName;
			person[2]=password;
			person[3]=eMail;
			person[4]=firstName;
			person[5]=lastName;
			person[6]=street;
			person[7]=zipCode;
			person[8]=city;
			person[9]=country;
			person[10]=sex;
			person[11]=phone;
			person[12]=userGroup;
			personList.add(person);
		}
		return personList;
	}

	public ArrayList<Object[]> generateCities(int amountOfCities, int tvbOffset, int amountTVB){
		ArrayList<Object[]> cityList = new ArrayList<Object[]>();
		ArrayList<String> countryNameList = this.generateNames(3);
		ArrayList<String> cityNameList = this.generateNames(4);
		int maxCountries= countryNameList.size()-1;
		int maxCities= cityNameList.size()-1;
		
		
		Object[] city=null;
		
		for (int i=0; i<amountOfCities; i++){
			String cityName		= cityNameList.get(DataGenerator.randomNumber(0,maxCities));
			String country		= countryNameList.get(DataGenerator.randomNumber(0,maxCountries));;
			int stars			= 5;
			String review		= this.generateReview(1);
			
			city=new Object[5];
			city[0]=cityName;
			city[1]=country;
			city[2]=review;
			city[3]=stars;
			city[4]=tvbOffset + i%amountTVB;

			cityList.add(city);
		}
		return cityList;
	}
	
	
	public ArrayList<Object[]> generateHotels(int amountOfCities, int hotellierOffset, int amountHotelliers){
		ArrayList<Object[]> hotelList = new ArrayList<Object[]>();
		ArrayList<String> nameList = this.generateNames(1);
		ArrayList<String> countryNameList = this.generateNames(3);
		ArrayList<String> cityNameList = this.generateNames(4);
		int maxCountries= countryNameList.size()-1;
		int maxCities	= cityNameList.size()-1;
		int maxNames	= nameList.size()-1;
		
		
		Object[] hotel=null;
		
		for (int i=0; i<amountOfCities; i++){
			String hotelName	= (i%2==0?"Hotel " : "Pension ") + nameList.get(DataGenerator.randomNumber(0, maxNames));
			String cityName		= cityNameList.get(DataGenerator.randomNumber(0,maxCities));
			String countryName	= countryNameList.get(DataGenerator.randomNumber(0,maxCountries));;
			
			hotel=new Object[5];
			hotel[0]=null;
			hotel[1]=hotelName;
			hotel[2]=cityName;
			hotel[3]=countryName;
			hotel[4]=hotellierOffset + i%amountHotelliers;

			hotelList.add(hotel);
		}
		return hotelList;
	}
	
	
	public ArrayList<Object[]> generateRooms(int amountOfRooms, int amountOfHotels){
		ArrayList<Object[]> roomList = new ArrayList<Object[]>();
		
		Object[] room=null;
		
		for (int i=0; i<amountOfRooms; i++){
			int hotel	= i%amountOfHotels+1;
			int beds	= DataGenerator.randomNumber(1,3);
			int price	= DataGenerator.randomNumber(50,500);
			
			room=new Object[4];
			room[0]=null;
			room[1]=hotel;
			room[2]=beds;
			room[3]=price;

			roomList.add(room);
		}
		return roomList;
	}
	
	public ArrayList<Object[]> generateReviews(int amountOfReviews, int amountOfHotels, int amountOfClients){
		ArrayList<Object[]> reviewList = new ArrayList<Object[]>();
		
		Object[] review=null;
		
		for (int i=0; i<amountOfReviews; i++){
			int stars 		= DataGenerator.randomNumber(1,6);
			String text		= this.generateReview(2);
			int hotel		= i%amountOfHotels+1;
			int client		= i%amountOfClients+1;
			
			review=new Object[5];
			review[0]=null;
			review[1]=stars;
			review[2]=text;
			review[3]=client;
			review[4]=hotel;

			reviewList.add(review);
		}
		return reviewList;
	}
	
	public ArrayList<Object[]> generateBookings(int amountOfBookings, int amountOfHotels, int amountOfRooms, int amountOfClients){
		ArrayList<Object[]> bookingList = new ArrayList<Object[]>();
		
		Object[] booking=null;
		
		for (int i=0; i<amountOfBookings; i++){
			int hotel		= i%amountOfHotels+1;
			int client		= i%amountOfClients+1;
			int room		= i%amountOfRooms+1;
			Calendar calendar = new GregorianCalendar();
			calendar.add(Calendar.YEAR, DataGenerator.randomNumber(0, 1)*-1);
			calendar.add(Calendar.MONTH, DataGenerator.randomNumber(0, 13)*-1);
			calendar.add(Calendar.DAY_OF_MONTH, DataGenerator.randomNumber(0, 31)*-1);
			Date beginDate 	= calendar.getTime();
			calendar.add(Calendar.DAY_OF_MONTH, DataGenerator.randomNumber(0, 21));
			Date endDate 	= calendar.getTime();
			java.sql.Date sqlBeginDate 	= new java.sql.Date(beginDate.getTime());
			java.sql.Date sqlEndDate 	= new java.sql.Date(endDate.getTime());
			
			booking=new Object[5];
			booking[0]=client;
			booking[1]=hotel;
			booking[2]=room;
			booking[3]=sqlBeginDate;
			booking[4]=sqlEndDate;

			bookingList.add(booking);
		}
		return bookingList;
	}
	
	
	public ArrayList<String> generateNames(int typeOfNames){
		
		ArrayList<String> nameList=new ArrayList<String>(); 
		String fileName=null;
		switch (typeOfNames)
		{
		case 1:
			fileName="names.txt";
			break;
		case 2:
			fileName="streets.txt";
			break;
		case 3:
			fileName="countries.txt";
			break;
		case 4: 
			fileName="cities.txt";
			break;
		case 5: 
			fileName="reviewcity.txt";
			break;
		case 6: 
			fileName="reviewhotel.txt";
			break;
		default:
			return null;
		}
		try {	
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			String name = null;
			
			do{
				name=new String();
				name=in.readLine();
				if (name==null)
					break;
				nameList.add(name);
			}while(true);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nameList;
	}
	
	private final String generatePassword(String password) {
		
		//Convert String to MD5 Hash and save password
		MessageDigest digest=null;
		try {
			digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {}
		digest.update(password.getBytes(),0,password.length());
		password = new BigInteger(1,digest.digest()).toString(16).toString();
		return password;
	}

	private final String generateReview(int typeOfReview) {
		ArrayList<String> reviewLines = this.generateNames(4+typeOfReview);
		int maxLines=reviewLines.size();
		String review="";
		int reviewLength = DataGenerator.randomNumber(1, 5);
		for (int i=0; i<reviewLength; i++){
			int currentLine=DataGenerator.randomNumber(1, maxLines);
			review+=reviewLines.get(currentLine) + " ";
			reviewLines.remove(currentLine);
			maxLines--;
		}
		return review;
	}
	/*public void changeText (){
		try {	
			BufferedReader in = new BufferedReader(new FileReader("cities.txt"));
			BufferedWriter out = new BufferedWriter(new FileWriter("citiesn.txt"));
			String line = null;
			String name = null;
			do {
				name=new String();
				line=in.readLine();
				if(line==null)
					break;
				name = line.replace("\"", "");
				out.write(name + "\n");
				System.out.println(name);
			}while(true);
			in.close();
		}catch (Exception e){
		}
	}*/
	
	public static int randomNumber(int min, int max){
		return (int)((1 + Math.random() * (max-min)));
	}
	
}
