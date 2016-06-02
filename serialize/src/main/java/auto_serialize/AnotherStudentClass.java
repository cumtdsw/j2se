package auto_serialize;

import java.io.Serializable;

public class AnotherStudentClass implements Serializable {

	private static final long serialVersionUID = 1L;

	// 姓名
	private String name;

	// 成绩
	private Float score;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}
}
