import java.util.*;
public class Placement_Cell {
	private int exit;
	private int prog_count;
	private ArrayList<Company> company ;
	private ArrayList<Student> student ;
	
	public Placement_Cell(int n) {
		this.exit=n;
		this.company=new ArrayList<Company>();
		this.student=new ArrayList<Student>();
		this.prog_count=2017000;
	}
	
	public int get_exit() {
		return this.exit;
	}
	
	public void student_registration(float cgpa,String branch) {
		student.add(new Student(++prog_count, cgpa, branch,company)); // composition relation 
		System.out.println("your roll is : " + prog_count);
	}
	
	private void go_to_tech_round(Company c) {
		System.out.println("tech round result is declared");
		for(int i=0;i<student.size();i++) {
			for(int j=0;j<c.get_course().length;j++) {
				if(c.get_course()[j].toUpperCase().equals(student.get(i).get_branch().toUpperCase())&&(student.get(i).get_status().toUpperCase().equals("NOT PLACED"))) {
					c.eligible_student.add(student.get(i));
					
					break;
				}
			}
		}
		c.temp_select=new boolean[c.eligible_student.size()];
		for(int i=0;i<c.eligible_student.size();i++) {
			System.out.print("Enter the tech marks for roll no  " + c.eligible_student.get(i).get_roll_no() + " : ");
			Scanner pc=new Scanner(System.in);
			int t=pc.nextInt();
			c.tech_marks.add(t);
			c.temp_select[i]=false;
			System.out.println("");
			
		}
		for(int i=0;i<c.eligible_student.size();i++) {
			c.eligible_student.get(i).eligible_company.add(c.get_name());
			c.eligible_student.get(i).Tech_Score.add(c.tech_marks.get(i));
		}
		
		int i=c.eligible_student.size();
		if(c.get_req()>=i) 
		{
			for(int j=0;j<c.eligible_student.size();j++) {
				c.select_student.add(c.eligible_student.get(j));
				c.temp_select[j]=true;
				
			}
		}
		else 
		{
			int j=0;
			while(j!=c.get_req()) {
				int max=-1;
				for(int k=0;k<i;k++) {
					if(max==-1) 
					{
						if(c.temp_select[k]==false)
						{
							max=k;
						}
					}
					else 
					{
						if(c.tech_marks.get(k)>c.tech_marks.get(max)) 
						{
							if(c.temp_select[k]==false) 
							{
								max=k;
							}
							
						}
						else if(c.tech_marks.get(k)==c.tech_marks.get(max))
						{
							if(c.temp_select[k]==false&&c.eligible_student.get(k).get_cgpa()>c.eligible_student.get(max).get_cgpa()) 
							{
								max=k;
							}
						}
					}
					
				}
				c.select_student.add(c.eligible_student.get(max));
				c.temp_select[max]=true;
				j++;
				
				
			}
		}
	}
	
	
	//correct method
	public void add_company(String name,String[] a,int req) {
		Company c=new Company(name, a, req,student);
		company.add(c); // composition relation
		System.out.println("----Company added successfully----");
		System.out.println("Company name : " + c.get_name());
		System.out.println("Company eligible courses");
		for(int i=0;i<a.length;i++) {
			System.out.println(c.get_course()[i]);
		}
		System.out.println("number of required student : " + c.get_req());
		System.out.println("Application status = OPEN");
		
		go_to_tech_round(c);
		
		
		
	
	}
	
	
	//correct method
	public void remove_student() {
		int f=0;
		for(int i=0;i<student.size();i++) {
			if(student.get(i).get_status().toUpperCase().equals("PLACED")) {
				System.out.println("Account removed for student : " + student.get(i).get_roll_no());
				student.remove(i--);
				//System.out.println(exit);
				exit--;
				f=1;
			}
			else {
				continue;
			}
		}
		if(f==0) {
			System.out.println("NO ONE PLACED UNTIL NOW");
		}
		else {
			System.out.println(exit);
			System.out.println("----removed successfully----");
		}
		
	}
	
	
	//correct method
	public void remove_company() {
		int f=0;
		for(int i=0;i<company.size();i++) {
			if(company.get(i).get_status().toUpperCase().equals("CLOSED")) {
				System.out.println("Account removed for company : " + company.get(i).get_name());
				company.remove(i);
				f=1;
			}
			else {
				continue;
			}
		}
		if(f==0) {
			System.out.println("NO ONE CLOSED UNTIL NOW");
		}
		else {
			System.out.println("----removed successfully----");
		}
	}
	
	
	//correct method
	public void display_unplaced_students() {
		int stud=0;
		for(int i=0;i<student.size();i++) {
			if(student.get(i).get_status().toUpperCase().equals("NOT PLACED")) {
				stud++;
			}
		}
		System.out.println(stud+  " students left");
	}
	
	
	//correct method
	public void display_active_companies() {
		for(int i=0;i<company.size();i++) {
			if(company.get(i).get_status().toUpperCase().equals("OPEN")) {
				System.out.println(company.get(i).get_name());
			}
		}
	}
	
	
	//correct method
	public void selected_student(String com) {
		int n=company.size();
		for(int i=0;i<n;i++) {
			if(company.get(i).get_name().toUpperCase().equals(com.toUpperCase())) {
				if(company.get(i).select_student.size()!=0) {
					System.out.println("Roll_no of selected students");
					for(int j=0;j<company.get(i).select_student.size();j++) {
						company.get(i).select_student.get(j).set_status("Placed");
						company.get(i).select_student.get(j).Get_Company_offer.add(company.get(i).get_name());
						System.out.println(company.get(i).select_student.get(j).get_roll_no());
					}
					company.get(i).set_status("CLOSED");
					System.out.println("Congratulation to all Selected Student");
					break;
				}
				else {
					company.get(i).set_status("CLOSED");
					System.out.println("No Remaining Student is eligible for this Company");
				}
			}
		}
		
	}
	
	
	//correct method
	public void display_company_detail(String com) {
		for(int i=0;i<company.size();i++) {
			if(company.get(i).get_name().toUpperCase().equals(com.toUpperCase())) {
				System.out.println("Company name : " + company.get(i).get_name());
				System.out.println("course eligiblity");
				for(int j=0;j<company.get(i).get_course().length;j++) {
					System.out.println(company.get(i).get_course()[j]);
				}
				System.out.println("no of required student : " + company.get(i).get_req());
				System.out.println("Application status = " + company.get(i).get_status());
			}
		}
	}
	
	
	//correct method
	public void display_student_detail(int roll) {
		for(int i=0;i<student.size();i++) {
			if(student.get(i).get_roll_no()==roll) {
				System.out.println("Roll_no : " + student.get(i).get_roll_no());
				System.out.println("CGPA :  " + student.get(i).get_cgpa());
				System.out.println("Course : " + student.get(i).get_branch());
				if(student.get(i).get_status().toUpperCase().equals("PLACED")) {
					System.out.println("PLACED IN ");
					for(int j=0;j<student.get(i).Get_Company_offer.size();j++)
					{
						System.out.println(student.get(i).Get_Company_offer.get(j));
					}
					
				}
				else {
					System.out.println("Placemet status :  " + "Not Placed");
				}
				
			}
		}
	}
	
	
	
	//correct method
	public void display_student_applied_companies(int roll) {
		for(int i=0;i<student.size();i++) {
			if(student.get(i).get_roll_no()==roll) {
				
					for(int j=0;j<student.get(i).eligible_company.size();j++) {
						System.out.println(student.get(i).eligible_company.get(j) + "  " + student.get(i).Tech_Score.get(j));
					}
			}
		}
	}
	
	public boolean search_company(String str) {
		if(company.size()!=0) {
			int f=0;
			for(int i=0;i<company.size();i++) {
				if(str.toUpperCase().equals(company.get(i).get_name().toUpperCase())) {
					f=1;
					break;
				}
				else {
					continue;
				}
			}
			if(f==0) {
				return false;
			}
			else {
				return true;
			}
		}
		else {
			return false;
		}
	}
	
	public boolean search_student(int roll_no) {
		if(student.size()!=0) {
			int f=0;
			for(int i=0;i<student.size();i++) {
				if(roll_no==student.get(i).get_roll_no()) {
					f=1;
					break;
				}
				else {
					continue;
				}
			}
			if(f==0) {
				return false;
			}
			else {
				return true;
			}
		}
		else {
			return false;
		}
	}
	public void Print() {
		System.out.println("------ 1 For Add Company -----------------------------------------------------------------------");
		System.out.println("------ 2 For Remove Account Placed Student -----------------------------------------------------");
		System.out.println("------ 3 For Remove Account of Company whose Application id Closed -----------------------------");
		System.out.println("------ 4 For Display Number of Placed Student --------------------------------------------------");
		System.out.println("------ 5 For Display Name of Company whose Application is Open ---------------------------------");
		System.out.println("------ 6 For Selected Student Company wise -----------------------------------------------------");
		System.out.println("------ 7 For Display Detail of Particular Company ----------------------------------------------");
		System.out.println("-------8 For Display Detail of Particular Student  ---------------------------------------------");
		System.out.println("------ 9 For Display Name of Company And Technical round Score for Particular Student  ---------");
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		System.out.println("--------Placement Cell Application is active---------");
		System.out.print("Enter the No of Students : ");
		int n=sc.nextInt();
		Placement_Cell main=new Placement_Cell(n);
		System.out.println("");
		// start the application
		
		System.out.println("Enter the cgpa of integer type and branch of string type");
		for(int i=1;i<=n;i++) {
			
			if(i==1)
			{
				System.out.println("Enter the credential of " + (i) + "st Student");
				main.student_registration(sc.nextFloat(), sc.next());
			}
			else if(i==2) {
				System.out.println("Enter the credential of " + (i) + "nd Student");
				main.student_registration(sc.nextFloat(), sc.next());
			}
			else if(i==3) {
				System.out.println("Enter the credential of " + (i) + "rd Student");
				main.student_registration(sc.nextFloat(), sc.next());
			}
			else {
				System.out.println("Enter the credential of " + (i) + "th Student");
				main.student_registration(sc.nextFloat(), sc.next());
			}
		}
		System.out.println("----------All students registered Successfully------------");
		System.out.println("---------------------Enter your query---------------------");
		main.Print();
		while(main.get_exit()!= 0)
		{
			int q=sc.nextInt();
			if(q == 1) {
				System.out.print("Enter the name of your company : ");
				String s=sc.next();
				System.out.println("");
				System.out.println("Enter the course eligiblity");
				System.out.print("No of course eligible : ");
				int x=sc.nextInt();
				System.out.println("");
				System.out.println("Enter the course name");
				String[] crs=new String[x];
				for(int i=0;i<x;i++)
				{
					String str=sc.next().toUpperCase();
					if(str.equals("CSE")||str.equals("CSAM")||str.equals("CSD")||str.equals("CSSS")||str.equals("CSB")||str.equals("ECE")) {
						crs[i]=str;
					}
					else {
						System.out.println("-----------INVALID COURSE-----------");
						System.out.println("----PLEASE ENTER CORRECT COURSE----s");
						i--;
					}
					
				}
				System.out.print("Enter the number of required student : ");
				int req=sc.nextInt();
				System.out.println("");
				
				main.add_company(s, crs, req);
				System.out.println("-------Enter your next query------");
				
			}
			else if(q == 2) {
				main.remove_student();
				if(main.student.size()!=0) {
					System.out.println("-------Enter your next query------");
				}
				
			}
			else if(q == 3) {
				main.remove_company();
				System.out.println("-------Enter your next query------");
				
			}
			else if(q==4) {
				main.display_unplaced_students();
				System.out.println("-------Enter your next query------");
				
			}
			else if(q==5) {
				main.display_active_companies();
				System.out.println("-------Enter your next query------");
				
			}
			else if(q==6) {
				System.out.println("Enter the company name");
				String str=sc.next();
				if(main.search_company(str)==true) {
					main.selected_student(str);
					System.out.println("-------Enter your next query------");
				}
				else {
					System.out.println("----INVALID NAME----");
					System.out.println("-------Enter your next query------");
				}
				
			}
			else if(q==7) {
				System.out.println("Enter the company name");
				String str=sc.next();
				if(main.search_company(str)==true) {
					main.display_company_detail(str);
					System.out.println("-------Enter your next query------");
				}
				else {
					System.out.println("----INVALID NAME----");
					System.out.println("-------Enter your next query------");
				}
			}
			else if(q==8) {
				System.out.println("Enter the student roll_no");
				int roll=sc.nextInt();
				if(main.search_student(roll)==true) {
					main.display_student_detail(roll);
					System.out.println("-------Enter your next query------");
				}
				else {
					System.out.println("----INVALID Roll-No----");
					System.out.println("-------Enter your next query------");
				}
			}
			else if(q==9) {
				System.out.println("Enter the student roll_no");
				int roll=sc.nextInt();
				if(main.search_student(roll)==true) {
					main.display_student_applied_companies(roll);
					System.out.println("-------Enter your next query------");
				}
				else {
					System.out.println("----INVALID Roll_No----");
					System.out.println("-------Enter your next query------");
				}
			}
			else {
				System.out.println("----INVALID QUERY----");
			}
			
		}
		System.out.println("----Finally All Students are Placed----");
		System.out.println("----------Application closed now!----------");
		
		

	}

}
