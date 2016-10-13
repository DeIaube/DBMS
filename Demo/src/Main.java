import java.util.Calendar;

public class Main {
	public static void main(String[] args) {
		String data = "20160718";
		for(int i = 0; i < 1000; i++){
			data = getLasterData(data);
			System.out.println(data);
		}
	}
	
	private static String getLasterData(String lasterData) {
		StringBuilder builder = new StringBuilder();
        int year = Integer.valueOf(lasterData.substring(0, 4));
        int month = Integer.valueOf(lasterData.substring(4, 6));
        int day = Integer.valueOf(lasterData.substring(6, 8));
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.add(Calendar.DATE, -1);
        builder.append(calendar.get(Calendar.YEAR));
        if(calendar.get(Calendar.MONTH) + 1 < 10){
        	builder.append(0);
        }
        builder.append(calendar.get(Calendar.MONTH) + 1);
        if(calendar.get(Calendar.DAY_OF_MONTH) < 10){
        	builder.append(0);
        }
        builder.append(calendar.get(Calendar.DAY_OF_MONTH));
        return builder.toString();
    }

}
