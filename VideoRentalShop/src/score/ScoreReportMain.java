package score;
// 1. refactoring �������� ������ �ִ� ����
// 2. true false������ �� �ٲ��(bool)
// 3. const�� �� ����
// 4. ������ method�� �ٲ��
// 5. �ش� Ŭ�������ִ� ������ ������� ������ �ٸ� Ŭ������ �ű�� ���� ����. �������� ��������.
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScoreReportMain {
	private static final int START_OF_SCORE = 0;
	private static final String PROMPT_MSG_FOR_STUDENT = "Enter student name: ";
	private static final int END_OF_SCORE = -1;
	
	private static Scanner scanner = new Scanner(System.in) ;
	private List<Student> studentList = new ArrayList<Student>();// sts ���� �̸� ���� �ʿ� Refactor

	public static void main(String[] args) {
		ScoreReportMain scoreReport = new ScoreReportMain() ;

		boolean isEnd = false ;
		while ( !isEnd ) {
			int selectOption = scoreReport.showCmd() ;//cmd �̷� �͵� ���̸� �ȵȴ�
			switch ( selectOption ) 
			{
			//case�� ���� ��ſ� enum���� ��밡�� 
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
	private void init() { // init�� ���̸� �ȵȴ�.
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
		 //  �Ű������� ����� ���� �۾�
		String selectedName = getStudentName(PROMPT_MSG_FOR_STUDENT);
		
		for ( Student student : studentList ) {
			final boolean isEqualStudentName = student.getName().equals(selectedName); // �̸����θ� ã���� �ƴϱ� ������ match�� �ٲٴ°� ����.
			if ( isEqualStudentName) {
				//student.getScore().clear();
				student.clearScore();
				System.out.println("Score cleared");
				break;
			}
		}
	}
	private String getStudentName(final String prompt) {
		System.out.println(prompt); // String�� constant�� ���� -> const���� �޶��� �� �ֱ� ������ �̸��� �� ����Ѵ�. 
		String studentName = scanner.next() ;
		return studentName;
	}
	
	private void showStudentSumScore() {
		String selectedName = getStudentName(PROMPT_MSG_FOR_STUDENT);
		for ( Student student : studentList ) {	// for������ �ɰ��� ã�� ������ ���� �ϴ°����� ���� �ʿ�
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
						final boolean isInvalidScore = studentScore == END_OF_SCORE; // -1 �� Extract const�� // �񱳰� ���� ������ invalid�Ѱ�?
						if ( isInvalidScore ) break ; // ������ �ָ� �ȵǰ� Extract Local variable �ؾ� �Ѵ�. ���� ��� isInvalidScore
						student.getScore().add(studentScore) ;
					}
					break;
				}
			}
		}
	}
}