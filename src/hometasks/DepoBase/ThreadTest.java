package hometasks.DepoBase;

public class ThreadTest implements Runnable {
	DepoList dList;
	int index = -1;
	
	public ThreadTest() {
		dList = new DepoList();
	}
	
	public ThreadTest(DepoList dList) {
		this.dList = dList;
	}
	
	public DepoList getdList() {
		return dList;
	}

	public void setdList(DepoList dList) {
		this.dList = dList;
	}

	public void run() {
		this.dList.add100(1);
	}

	public static void main(String[] args) {
		DepoList depoList1 = new DepoList();
		DepoList depoList2 = new DepoList();
		depoList1.init();
		depoList2.init();
		
		

	}

}
