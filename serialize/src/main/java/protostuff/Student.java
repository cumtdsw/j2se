package protostuff;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 一个最简单的javabean，不用实现任何接口，不用继承任何类 2016年2月1日 16:34:07
 */
public class Student {

	private int number;
	private String name;
	private Date birth;
	private BigDecimal score;
	private List<String> interests;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Student [number=" + number + ", name=" +
	        name + ", birth=" + birth + ", score=" + score + ", interests=");
		if(interests == null) {
			sb.append("null");
		} else {
			for(String interest : interests) {
				sb.append(interest).append(";");
			}
		}
		sb.append("]");
		return sb.toString();
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public List<String> getInterests() {
		return interests;
	}

	public void setInterests(List<String> interests) {
		this.interests = interests;
	}

}
