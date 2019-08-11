import java.util.ArrayList;

class Company{
	public ArrayList<Student>select_student;
	public ArrayList<Student> eligible_student;
	public ArrayList<Integer> tech_marks;
	public boolean[] temp_select;
	private ArrayList<Student> student;  //class level reference of student class
	final private String name;
	private String[] course;
	final private int req;
	private String status;
	public Company(String name,String[] a,int req,ArrayList<Student> s) {
		this.select_student=new ArrayList<Student>();
		this.eligible_student=new ArrayList<Student>();
		this.tech_marks=new ArrayList<Integer>();
		this.name=name;
		this.course=a;
		this.req=req;
		this.status="OPEN";
		this.student=s;  // association relation
		
	}
	
	public String get_name() {
		return this.name;
	}
	
	public String get_status() {
		return this.status;
	}
	
	public void set_status(String s) {
		this.status=s;
	}
	public int get_req() {
		return this.req;
	}
	
	public String[] get_course() {
		return this.course;
	}
	public void setter(ArrayList<Student> a) { // association relation
		this.student=a;
	}
	
	
	
	
}

