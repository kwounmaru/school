package Database;

public class Data {
	/* �����ͺ��̽��� �Ӽ��κ��� ������ ����� �ݴϴ�. */
	int no;
	String name;

	/* �������Դϴ�. */
	public Data() {
	}

	public Data(int no, String name) {
		super();
		this.no = no;
		this.name = name;
	}

	/* �� ������ getter�� setter�Դϴ�. */
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public static void main(String[] args) {
		
	}
}
