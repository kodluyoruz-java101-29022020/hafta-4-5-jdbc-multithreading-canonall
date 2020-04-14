package Soru2;

import java.util.ArrayList;
import java.util.List;

public class ListOperations implements Runnable {

	private Object LOCK = new Object();

	private List<Integer> numbers;
	volatile List<Integer> evenNumbersList;
	volatile List<Integer> oddNumbersList;

	public ListOperations(List<Integer> numbers) {
		this.numbers = numbers;
		this.evenNumbersList = new ArrayList<>();
		this.oddNumbersList = new ArrayList<>();
	}

	public void findEvenOrOddNumbers(List<Integer> list) {

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) % 2 == 0) {
				evenNumbersList.add(list.get(i));
			} else {
				oddNumbersList.add(list.get(i));
			}
		}
	}

	@Override
	public void run() {
		synchronized (LOCK) {

			StringBuilder builder = new StringBuilder();

			builder.append(Thread.currentThread().getName());
			builder.append(" started");
			System.out.println(builder.toString());

			findEvenOrOddNumbers(this.numbers);

		}

	}

	public <T> void printList(List<T> list) {

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
			// Konsol yan yana 5000 elemanı gösteremediği için alt alta gelecek şekilde
			// ekrana bastırıldı fakat alttaki satır boyut küçültüldüğü taktirde yan yana
			// sıralanabilir.
			// System.out.print(list.get(i) + " ");
		}
		System.out.println();
	}

	public List<Integer> getEvenNumbersList() {
		return evenNumbersList;
	}

	public void setEvenNumbersList(List<Integer> evenNumbersList) {
		this.evenNumbersList = evenNumbersList;
	}

	public List<Integer> getOddNumbersList() {
		return oddNumbersList;
	}

	public void setOddNumbersList(List<Integer> oddNumbersList) {
		this.oddNumbersList = oddNumbersList;
	}
}
