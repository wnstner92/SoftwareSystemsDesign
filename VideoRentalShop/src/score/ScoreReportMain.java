package score;
// 1. refactoring 변수명을 사전에 있는 용어로
// 2. true false에서는 다 바꿔라(bool)
// 3. const로 다 추출
// 4. 문장을 method로 바꿔라
// 5. 해당 클래스에있는 변수를 사용하지 않으면 다른 클래스로 옮기는 것이 낫다. 응집도가 떨어진다.
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScoreReportMain {
	private static final int START_OF_SCORE = 0;
	private static final String PROMPT_MSG_FOR_STUDENT = "Enter student name: ";
	private static final int END_OF_SCORE = -1;
	
	private static Scanner scanner = new Scanner(System.in) ;
	private List<Student> studentList = new ArrayList<Student>();// sts 변수 이름 변경 필요 Refactor

	public static void main(String[] args) {
		ScoreReportMain scoreReport = new ScoreReportMain() ;

		boolean isEnd = false ;
		while ( !isEnd ) {
			int selectOption = scoreReport.showCmd() ;//cmd 이런 것도 줄이면 안된다
			switch ( selectOption ) 
			{
			//case에 숫자 대신에 enum으로 사용가능 
			case 0: isEnd = true ; break ;
			case 1: scoreReport.add("student") ; break ;
			case 2: scoreReport.add("scores") ; break ;
			case 3: scoreReport.showStudentSumScore() ; break ;
			case 4: scoreReport.clearStudentScore() ; break ;
			case -1: scoreReport.init() ; break ;
			default: break ;
			}
		}
		System.out.println("Bye");
	}
	private int showCmd() {
		System.out.println("\nSelect a command !");
		System.out.println("\t -1. Init");
		System.out.println("\t 0. Quit");
		System.out.println("\t 1. Add student");
		System.out.println("\t 2. Add scores");
		System.out.println("\t 3. Show student report");
		System.out.println("\t 4. Clear student scores");
		
		int selectedOption = scanner.nextInt() ;
		return selectedOption ;
	}
	private void init() { // init도 줄이면 안된다.
		Student james = new Student("James") ;
		james.getScore().add(20) ;
		james.getScore().add(10) ;

		Student brown = new Student("Brown") ;
		brown.getScore().add(30) ;
		brown.getScore().add(10) ;
		brown.getScore().add(20) ;
		brown.getScore().add(60) ;
		brown.getScore().add(50) ;

		studentList.add(james) ;
		studentList.add(brown) ;
	}
	private void clearStudentScore() {
		 //  매개변수를 만들기 위한 작업
		String selectedName = getStudentName(PROMPT_MSG_FOR_STUDENT);
		
		for ( Student student : studentList ) {
			final boolean isEqualStudentName = student.getName().equals(selectedName); // 이름으로만 찾을게 아니기 때문에 match로 바꾸는게 낫다.
			if ( isEqualStudentName) {
				//student.getScore().clear();
				student.clearScore();
				System.out.println("Score cleared");
				break;
			}
		}
	}
	private String getStudentName(final String prompt) {
		System.out.println(prompt); // String을 constant로 뺀다 -> const값이 달라질 수 있기 때문에 이름을 잘 줘야한다. 
		String studentName = scanner.next() ;
		return studentName;
	}
	
	private void showStudentSumScore() {
		String selectedName = getStudentName(PROMPT_MSG_FOR_STUDENT);
		for ( Student student : studentList ) {	// for루프를 쪼개서 찾은 다음에 일을 하는것으로 변경 필요
			final boolean isEqualStudentName = student.getName().equals(selectedName);
			if ( isEqualStudentName) {
				int studentScoreSum = START_OF_SCORE ;
				for ( Integer studentScore: student.getScore()) {
					System.out.print(studentScore + " ");
					studentScoreSum += studentScore ;
				}
				System.out.println("Sum: " + studentScoreSum);
				break;
			}
		}
	}
	
	private void add(String kind) {
		final boolean isStudent = kind.equals("student");
		if ( isStudent ) {
			String studentName = getStudentName(PROMPT_MSG_FOR_STUDENT);
			Student newStudent = new Student(studentName) ;
			studentList.add(newStudent) ;
		}
		else {
			String studentName = getStudentName(PROMPT_MSG_FOR_STUDENT);
			for ( Student student : studentList ) {
				if ( student.getName().equals(studentName)) {
					System.out.println("Enter scores( "+ END_OF_SCORE +" for quit): ") ;
					while ( true ) {
						int studentScore = scanner.nextInt() ;
						final boolean isInvalidScore = studentScore == END_OF_SCORE; // -1 을 Extract const로 // 비교가 수단 목적은 invalid한가?
						if ( isInvalidScore ) break ; // 목적을 주면 안되고 Extract Local variable 해야 한다. 예를 들면 isInvalidScore
						student.getScore().add(studentScore) ;
					}
					break;
				}
			}
		}
	}
}