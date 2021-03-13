package domaci_zadaci.z02_p01.time;

public class Time implements Comparable<Time> {
		
	int h, m;
	
	public Time(int h, int m) {
		this.h = h;
		this.m = m;
	}
	
	public int h() { return h; }
	public int m() { return m; }
	
	public String toString() {
		return String.format("%02d:%02d", h, m);
	}

	@Override
	public int compareTo(Time o) {
		return (h() * 60 + m()) - (o.h() * 60 + m());
	}
}
