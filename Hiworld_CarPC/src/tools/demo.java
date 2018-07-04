package tools;
 
public class demo {

	public static void main(String[] args) {
		System.out.println("" + gettime(123));
		System.out.println("" + gettime(323));
		System.out.println("" + gettime(423));
		System.out.println("" + gettime(623)); 
	}

	private static String gettime(int val) { 
		String tString = "00:00";
		int h = 0, m, s;
		if (val > 60 * 60) {
			h = val / 3600;
		}
		m = (val - 3600) / 60;
		s = val % 60;
		if (h > 0) {
			tString = String.format("%02d:%02d:02d", h, m, s);
		} else {
			tString = String.format("%02d:02d", m, s);
		}
		return tString;
	}

}
