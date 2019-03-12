
class Rental {
	private Movie _movie;
	private int _daysRented;
	public Rental(Movie movie, int daysRented) {
		_movie = movie;
		_daysRented = daysRented;
	}
	public int getDaysRented() { return _daysRented; }
	public Movie getMovie() { return _movie; }
	
	public double getCharge() {// 기존에 있는 each그대로 쓰면 안된다.
		double charge = 0; //each로 되어 있을 때 특정 요금이라는 의미로 this를 썼는데 바꿨기 때문에 이것도 바꿔야 된다.
		switch (_movie.getPriceCode()) {
		case Movie.REGULAR:
			charge += 2;
			if (_daysRented > 2)
				charge += (_daysRented - 2) * 1.5;
			break;
		case Movie.NEW_RELEASE:
			charge += _daysRented * 3;
			break;
		case Movie.CHILDRENS:
			charge += 1.5;
			if (_daysRented > 3)
				charge += (_daysRented - 3) * 1.5;
			break;
		}
		return charge;
	}
	public int getPoint() {
		// add frequent renter points
		int points = 1;
		// add bonus for a two day new release rental
		if ((getMovie().getPriceCode() == Movie.NEW_RELEASE) && _daysRented > 1)
			points++;
		
		return points;
	}
}
