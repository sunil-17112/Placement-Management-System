import java.util.ArrayList;

class Student{
	private ArrayList<Company> company;  // class level reference of company class
	final private int roll_no; //it should be final because we can't update roll_no of particular student after initialize
	final private float CGPA; //it should be final because we can't update cgpa of particular student after initialize
	final private String branch; //it should be final because we can't update branch of particular student after initialize
	private String status;
	private boolean selected;
	
	public Student(int roll_no,float a,String b,ArrayList<Company> c){
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
	
	public String find_placed_company() {
		int f=0;
		for(int i=0;i<company.size();i++) {
			if(company.get(i).select_student.size()!=0) {
				for(int j=0;j<company.get(i).select_student.size();j++) {
					if(company.get(i).select_student.get(j).get_roll_no()==this.roll_no&&company.get(i).get_status().toUpperCase().equals("CLOSED")) {
						f=i;
						break;
					}
				}
			}
			
		}
		return company.get(f).get_name();
	}
	
	public void find_eligible_company() {
		int f=0;
		int ind=0;
		for(int i=0;i<company.size();i++) {
			if(company.get(i).eligible_student.size()!=0) {
				for(int j=0;j<company.get(i).eligible_student.size();j++) {
					if(company.get(i).eligible_student.get(j).get_roll_no()==this.roll_no) {
						f=i;
						ind=j;
						System.out.println(company.get(f).get_name() + " " +  Integer.toString(company.get(f).tech_marks.get(ind)));
					}
				}
			}
			
		}
		
	}
	
	
}
