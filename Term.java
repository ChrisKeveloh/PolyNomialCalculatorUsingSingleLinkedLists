/*
 * Created on: 01/27/22
 *
 * ULID: <CKEVELO>
 * Class: IT 168 
 */

package edu.ilstu;

public class Term
{

	private int coefficient; // instance variables
	private int exponent;

	public Term(int coefficient, int exponent) // default constructor that accepts both instance variables
	{
		this.coefficient = coefficient;
		this.exponent = exponent;
	}

	public int getCoe() // getters
	{
		return coefficient;
	}

	public int getExp()
	{
		return exponent;
	}

	public String toString() // toString method that tests the current values of the coefficient and exponent
								// to print out properly
	{

		if (coefficient == 0)
		{
			if (exponent > 0)
				return "x^" + exponent;
			else
				return "";
		}

		else if (exponent == 0)
		{
			if (coefficient > 0)
				return "" + coefficient;
			else
				return coefficient + "";
		}

		else if (coefficient == 1)
		{
			if (exponent > 1)
				return "x^" + exponent;
			else
				return "x";

		}

		else if (coefficient == -1)
		{
			if (exponent == 1)
			{
				return "-x";
			} else
				return "-x^" + exponent;
		}

		else if (exponent == 1)
		{
			if (coefficient > 0)
				return coefficient + "x";
			else
				return coefficient + "x";
		}

		else
			return coefficient + "x^" + exponent;

	}
}
