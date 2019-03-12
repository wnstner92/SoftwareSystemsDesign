package score;

import java.util.ArrayList;
import java.util.List;

public class Student {
	private String name;
	private List<Integer> scores = new ArrayList<>() ;
	
	public Student(String name) {
		super();
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Integer> getScore() {
		return scores;
	}
	public void setScore(List<Integer> scores) {
		this.scores = scores;
	}
	public void clearScore() {
		// TODO Auto-generated method stub
		scores.clear();
	}
}
