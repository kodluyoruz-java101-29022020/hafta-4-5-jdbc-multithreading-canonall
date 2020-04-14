package Soru2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) {

		List<Integer> numbers = putNumbers(Constants.value);
		List<Integer> subList1 = numbers.subList(0, Constants.quarterOfValue);
		List<Integer> subList2 = numbers.subList(Constants.quarterOfValue, Constants.halfOfValue);
		List<Integer> subList3 = numbers.subList(Constants.halfOfValue,
				Constants.quarterOfValue + Constants.halfOfValue);
		List<Integer> subList4 = numbers.subList(Constants.quarterOfValue + Constants.halfOfValue, Constants.value);

		ListOperations listOperations1 = new ListOperations(subList1);
		ListOperations listOperations2 = new ListOperations(subList2);
		ListOperations listOperations3 = new ListOperations(subList3);
		ListOperations listOperations4 = new ListOperations(subList4);

		ExecutorService executor = Executors.newFixedThreadPool(Constants.numberOfThreads);

		executor.execute(listOperations1);
		executor.execute(listOperations2);
		executor.execute(listOperations3);
		executor.execute(listOperations4);

		ThreadSleeper.sleep(250);

		System.out.println();

		List<Integer> totalEvenNumbers = new ArrayList<Integer>();

		totalEvenNumbers = listOperations1.getEvenNumbersList();
		totalEvenNumbers.addAll(listOperations2.getEvenNumbersList());
		totalEvenNumbers.addAll(listOperations3.getEvenNumbersList());
		totalEvenNumbers.addAll(listOperations4.getEvenNumbersList());

		System.out.println(String.format("List from 1 to %d\n", Constants.value));

		System.out.println("Total even number count: " + totalEvenNumbers.size());
		listOperations1.printList(totalEvenNumbers);

		System.out.println();

		List<Integer> totalOddNumbers = new ArrayList<Integer>();

		totalOddNumbers = listOperations1.getOddNumbersList();
		totalOddNumbers.addAll(listOperations2.getOddNumbersList());
		totalOddNumbers.addAll(listOperations3.getOddNumbersList());
		totalOddNumbers.addAll(listOperations4.getOddNumbersList());

		System.out.println("Total odd number count: " + totalOddNumbers.size());
		listOperations1.printList(totalOddNumbers);
	}

	public static List<Integer> putNumbers(int value) {

		List<Integer> finaList = new ArrayList<Integer>();

		for (int i = 1; i <= value; i++) {
			finaList.add(i);
			;
		}

		return finaList;
	}

}
