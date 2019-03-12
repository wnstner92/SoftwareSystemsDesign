
import java.util.Enumeration;
import java.util.Vector;

class Customer {
	private String _name;
	private Vector _rentals = new Vector();

	public Customer(String name) {
		_name = name;
	};

	public void addRental(Rental arg) {
		_rentals.addElement(arg);
	}

	public String getName() {
		return _name;
	};

	public String statement() {
		//함수 하나에 역할이 4개가 있다. 안 좋은 코드다
		
		double totalAmount = 0;
		Enumeration rentals = _rentals.elements();
		int totalFrequentRenterPoints =0;
		String result = "Rental Record for " + getName() + "\n";
		while (rentals.hasMoreElements()) {
			Rental each = (Rental) rentals.nextElement();

			// show figures for this rental
			result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(each.getCharge()) + "\n"; // inline을 통해서 thisAmount를 없앤다.
			totalAmount += each.getCharge();
			totalFrequentRenterPoints += each.getPoint();
		}

		// add footer lines
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(totalFrequentRenterPoints) + " frequent renter points";

		return result;
	}
	//수정 요망
	double getTotalAmount()
	{
		double totalAmount = 0;
		
		Enumeration rentals = _rentals.elements();
		
		while (rentals.hasMoreElements()) {
			Rental each = (Rental) rentals.nextElement();
			totalAmount += each.getCharge();
		}
		
		return totalAmount;
	}
	
}
