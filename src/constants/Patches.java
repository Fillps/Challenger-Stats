package constants;

public enum Patches {
	//V6_17("");
	V6_16("1470816000000", "1472025600000"), // 10/08/2016 @ 8:00am, 24/08/2016 @ 8:00am
	V6_15("1469520000000", "1470816000000"), // 26/07/2016 @ 8:00am, 10/08/2016 @ 8:00am
	V6_14("1468396800000", "1469520000000"), // 13/07/2016 @ 8:00am, 26/07/2016 @ 8:00am
	V6_13("1467187200000", "1468396800000"), // 29/06/2016 @ 8:00am, 13/07/2016 @ 8:00am
	V6_12("1465977600000", "1467187200000"); // 15/06/2016 @ 8:00am, 29/06/2016 @ 8:00am
	
	private String beginTime;
	private String endTime;
	
	Patches(String beginTime, String endTime){
		this.beginTime = beginTime;
		this.endTime = endTime;
	}
	public long getBeginTime(){
		return Long.valueOf(beginTime);
	}
	public long getEndTime(){
		return Long.valueOf(endTime);
	}
	
	
}
