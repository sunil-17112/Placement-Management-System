import java.util.ArrayList;

class Student{
	private ArrayList<String> Get_Company_offer;
	private  ArrayList<String> eligible_company;
	private ArrayList<Integer> Tech_Score;
	private ArrayList<Company> company;  // class level reference of company class
	final private int roll_no; //it should be final because we can't update roll_no of particular student after initialize
	final private float CGPA; //it should be final because we can't update cgpa of particular student after initialize
	final private String branch; //it should be final because we can't update branch of particular student after initialize
	private String status;
	private boolean selected;
	
	public Student(int roll_no,float a,String b,ArrayList<Company> c){
		this.Get_Company_offer=new ArrayList<String>();
		this.eligible_company=new ArrayList<String>();
		this.Tech_Score=new ArrayList<Integer>();
		this.roll_no=roll_no;
		this.CGPA=a;
		this.branch=b;
		this.status="Not Placed";
		this.selected=false;
		this.company=c; // association relation
	}
	
	public float get_cgpa() {
		return this.CGPA;
	}
	
	public String get_branch() {
		return this.branch;
	}
	
	public int get_roll_no() {
		return this.roll_no;
	}
	
	public String get_status() {
		return this.status;
	}
	
	public boolean Is_selected() {
		return this.selected;
	}
	
	public void set_selected(boolean s) {
		this.selected=s;
	}
	
	public void set_status(String s) {
		this.status=s;
	}
	public ArrayList<String> get_company_offer() {
		return this.Get_Company_offer;
	}
	public ArrayList<String> get_eligible_company() {
		return this.eligible_company;
	}
	public ArrayList<Integer> get_tech_score() {
		return this.Tech_Score;
	}
	
}
