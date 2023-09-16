/*
 * Created on: 01/27/22
 *
 * ULID: <CKEVELO>
 * Class: IT 168 
 */

package edu.ilstu;

import java.util.ArrayList;
import java.util.Iterator;

public class PolyDriver
{
	static YourSingleLinkedList<Term> termList1 = new YourSingleLinkedList<>(); // making two term lists that each hold
																				// polynomials
	static YourSingleLinkedList<Term> termList2 = new YourSingleLinkedList<>();

	private static ArrayList addPoly() // add Polynomials method returns an ArrayList
	{

		Iterator<Term> firstIter = termList1.iterator(); // creating two iterators. one for each list
		Iterator<Term> secondIter = termList2.iterator();
		ArrayList<Term> result = new ArrayList<>(); // storing the result of the new polynomial in an ArrayList called
													// result

		Term thisTerm = firstIter.next(); // assigning a term object equal to the first term in each list. doing this
											// shortens some code a bit
		Term otherTerm = secondIter.next();

		while (thisTerm != null || otherTerm != null) // making sure that either term is not null
		{
			if (thisTerm == null) // if thisTerm is null then we assume we have reached end of the list and we
									// know that otherTerm is not null because we passed the while loop conditions.
									// so otherTerm has nothing to compare to and instead gets added to the result
			{
				result.add(otherTerm); // adds otherTerm to list
				otherTerm = null;
			} else if (otherTerm == null) // same logic as above
			{
				result.add(thisTerm);
				thisTerm = null;
			}

			else if (thisTerm.getExp() > otherTerm.getExp()) // if both items are not null we must compare the exponents
			{
				result.add(thisTerm);
				thisTerm = firstIter.next(); // advancing the iterator

			}

			else if (otherTerm.getExp() > thisTerm.getExp()) // comparing exponents
			{
				result.add(otherTerm);
				otherTerm = secondIter.next(); // advancing the iterator
			} else
			{
				int newCoe = thisTerm.getCoe() + otherTerm.getCoe(); // creating the new coefficient
				if (newCoe != 0)
				{
					Term newTerm = new Term(newCoe, thisTerm.getExp()); // creating a new term object out of the
																		// newCoefficient and exp
					result.add(newTerm); // adding that term to the list
					if (firstIter.hasNext()) // checking if first iter has next
					{
						thisTerm = firstIter.next(); // if it does then it advances the iterator to next item
					} else
						thisTerm = null;
					if (secondIter.hasNext()) // checking if second iterator has next
						otherTerm = secondIter.next();// if it does then it advances the iterator to next item
					else
						otherTerm = null;
				}

				if (newCoe == 0)
				{
					thisTerm = firstIter.next();
					otherTerm = secondIter.next();
				}
			}

		}
		return result; // returns result

	}

	public static void print(ArrayList array) // accepts an array - print array method to print out polynomials
	{
		int size = 0;
		String poly = ""; // creating a string
		poly += array.get(size); // adding first term to string to not print a + or - symbol in front of the
									// first coefficient
		for (int i = 1; i < array.size(); i++) // starting i at 1 since we already added the index of 0 term to
												// beginning
		{
			if (((Term) array.get(i)).getCoe() >= 1) // casting Term to getIndex of Term object to access the
														// coefficient
														// and asking if its greater than 1
			{ // if its greater than one then that means it's a positive coefficient
				poly += "+" + array.get(i); // which then prints a + sign in front of the value
			} else // if its not greater than 1 then it just prints the value
				poly += array.get(i);

		}

		System.out.println(poly); // prints out the string
	}

	public static String print(YourSingleLinkedList list) // same thing as above but for LinkedLists
	{
		int size = 0;
		String poly = "";
		poly += list.get(size);
		for (int i = 1; i < list.size(); ++i)
		{
			if (((Term) list.get(i)).getCoe() >= 1)
			{
				poly += "+" + list.get(i);
			} else
				poly += list.get(i);
		}

		return poly;
	}

	public static ArrayList multiply() // multiply method that returns an arrayList
	{
		Iterator<Term> firstIter = termList1.iterator(); // creates the firstIterator for the first list
		int exp;

		ArrayList<Term> result = new ArrayList<>(); // ArrayList that holds the result

		while (firstIter.hasNext())
		{
			Term thisTerm = firstIter.next(); // setting term
			int coefficient; // temp variable
			Iterator<Term> secondIter = termList2.iterator(); // initializing the secondIter inside the while loop
																// allows it to reset it for the next term

			while (secondIter.hasNext())
			{
				Term otherTerm = secondIter.next(); // assigning term object to current iteration in second list

				coefficient = thisTerm.getCoe() * otherTerm.getCoe(); // getting the coefficient

				int exponent = thisTerm.getExp() + otherTerm.getExp(); // getting the exponent
				boolean isAdded = false;

				Term newTerm = new Term(coefficient, exponent); // creates a new term object with the new updated
																// coefficient and exponent
				for (int i = 0; i < result.size(); i++) // this loops through the current polynomails and tests to see
														// if there is any current term object with the same degree of
														// power
				{
					if (newTerm.getExp() == result.get(i).getExp()) // searches for a current term in the results that
																	// matches the power of newterm being added
					{
						int coe = newTerm.getCoe() + result.get(i).getCoe(); // adds the two
						if (coe == 0)
						{
							exp = 0;
						} else
							exp = newTerm.getExp(); // exponent doesnt change just did this to make it less confusing

						Term newerTerm = new Term(coe, exp); // newerTerm is made of combined terms
						result.remove(result.get(i)); // removes that last term
						result.add(newerTerm); // adds the newerTerm
						isAdded = true; // isAdded goes to true
						break; // breaks out of loop
					}

				}
				if (!isAdded) // if it fails to find same like terms then it just adds the old newTerm
					result.add(newTerm);
			}

		}

		return result; // returns the ArrayList of new polynomial

	}

	public static void main(String[] args)
	{
		Term term1 = new Term(1, 1); // creating a bunch of term objects and adding them to the list
		Term term2 = new Term(1, 0); // testing first expression of adding and multiplying (x+1) and (-x+1)
		Term term3 = new Term(-1, 1);
		Term term4 = new Term(1, 0);
		termList1.add(term1);
		termList1.add(term2);
		termList2.add(term3);
		termList2.add(term4);

//		Term term1 = new Term(2, 3); // testing expression of adding and multiplying (2x^3+2x^2-x+3) (3x^2-2x+2)
//		Term term2 = new Term(2, 2);
//		Term term3 = new Term(-1, 1);
//		Term term4 = new Term(3, 0);
//		Term term5 = new Term(3, 2);
//		Term term6 = new Term(-2, 1);
//		Term term7 = new Term(2, 0);
//		termList1.add(term1);
//		termList1.add(term2);
//		termList1.add(term3);
//		termList1.add(term4);
//		termList2.add(term5);
//		termList2.add(term6);
//		termList2.add(term7);

		// hightlight and uncomment out each block of code to test different expressions

//		Term term1 = new Term(3, 4); // creating a bunch of term objects and adding them to the list
//		Term term2 = new Term(2, 2); // testing adding and multiplying (3x^4+2x^2+3x+7) and (2x^3-5x+5)
//		Term term3 = new Term(3, 1);
//		Term term4 = new Term(7, 0);
//		Term term5 = new Term(2, 3);
//		Term term6 = new Term(-5, 1);
//		Term term7 = new Term(5, 0);
//		termList1.add(term1);
//		termList1.add(term2);
//		termList1.add(term3);
//		termList1.add(term4);
//		termList2.add(term5);
//		termList2.add(term6);
//		termList2.add(term7);

		System.out.println("First polynomial = " + print(termList1)); // printing out
		// the two linked lists using the
		// print method
		System.out.println("Second polynomial = " + print(termList2));
		// System.out.print("Sum = ");
		// print(addPoly());
		System.out.print("Sum = ");
		print(addPoly()); // testing the add method
		System.out.print("Multiplication = ");
		print(multiply()); // testing the multiply method

	}

}
