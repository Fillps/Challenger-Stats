package model.riot_api.constants;

/*
 * Utilizando o tempo de inicio e o tempo de fim de um patch
 * determinamos o periodo de tempo do determinado patch
 * usando timestamps em milisegundos. 
 *
 */


public enum Patches {
	V6_17("V6_17", "1472025600000", "1473321600000"), // 24/08/2016 @ 8:00am, 08/09/2016 @ 8:00am
	V6_16("V6_16", "1470816000000", "1472025600000"), // 10/08/2016 @ 8:00am, 24/08/2016 @ 8:00am
	V6_15("V6_15", "1469520000000", "1470816000000"), // 26/07/2016 @ 8:00am, 10/08/2016 @ 8:00am
	V6_14("V6_14", "1468396800000", "1469520000000"), // 13/07/2016 @ 8:00am, 26/07/2016 @ 8:00am
	V6_13("V6_13", "1467187200000", "1468396800000"), // 29/06/2016 @ 8:00am, 13/07/2016 @ 8:00am
	V6_12("V6_12", "1465977600000", "1467187200000"); // 15/06/2016 @ 8:00am, 29/06/2016 @ 8:00am
	
	private String name;
	private String beginTime;
	private String endTime;
	
	Patches(String name, String beginTime, String endTime){
		this.name = name;
		this.beginTime = beginTime;
		this.endTime = endTime;
	}
	public String getName(){
		return this.name;
	}
	public long getBeginTime(){
		return Long.valueOf(beginTime);
	}
	public long getEndTime(){
		return Long.valueOf(endTime);
	}
	
	
}
