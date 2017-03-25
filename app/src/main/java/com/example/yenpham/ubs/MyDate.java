package com.example.yenpham.ubs;

import java.util.*;
/**
* @author Yen Pham
*/
public class MyDate{
	private int day;//day (between 1 and daysOfMonth(month, year) 
	private int month;//month (betwwen 1 and 12)
	private int year;// year
/**
* Constructor: default: date 0/0/0
*/
public MyDate(){}
/**
* Constructor: set a date 
* @param m represent month
* @param d represent day
* @param y represent year
* 
*/
public MyDate(int m, int d, int y){
	month = m;
	day = d;
	year = y;
}
/**
* Constructor: take a string with the form mm/dd/yyyy to create a date 
* @param date represent date as a string. The string has to be follow the form mm/dd/yyyy
* 
*/

public void setDate(String date){
	try {
		String[] tok = date.split("-");
		month = Integer.parseInt(tok[0]);
		day = Integer.parseInt(tok[1]);
		year = Integer.parseInt(tok[2]);
	}catch (Exception e){
		month=0;
		day =0;
		year =0;
	}
}
/**
* Method: get day 
* @return day of a Date object  
*/
public int getDay(){
	return day;
}
/**
* Method: get month 
* @return month of a Date object  
*/
public int getMonth(){
	return month;
}
/**
* Method: get year
* @return year of a Date object  
*/
public int getYear(){
	return year;
}
/**
* Method: get year
* @return a Date object as a string with the form m/d/y  
*/
public String toString(){

	 return month+"-"+day+"-"+year;
}
/**
* Function: check if a year is leap year
* @return -true if the year is a leap year,
*         -false if the year is not a leap year 
*/
private static boolean isLeapYear(int year){
	if (year%400 ==0)
		return true;
	if (year%4==0 && year%100!=0)
		return true;
	return false;
}
/**
* Method: check if a Date object is Valid
*
* @return   false if year less than 0,
*           false if month greater than 12 or smaller than 1,
*           false if day greater than daysOfMonth(month,year) or smaller than 1, 
*           true anywhere else
*/
public boolean isValid(){
	if (year <0)
		return false;
	if (month >12 || month<1)
		return false;
	if(day<1 || day > daysOfMonth(month,year))
		return false;
	return true;
}
/**
* Function: to get the number of days of month m in year y
*
* @param m month specified
* @param y year 
* 
* @return daysOf[m] if month is not 2,
*          29 if month is 2 and the year is a leap year,
*          28 if month is 2 and the year is not a leap year
*/
private static int daysOfMonth(int m,int y){
	int[] daysOf = {0,31,28,31,30,31,30,31,31,30,31,30,31};
	if (m!=2)
		return daysOf[m];
	if(isLeapYear(y))
		return 29;
	return 28;
}
/** 
* Method: to check if a object Date is before the other object Date 
*
* @param d2 the Date object specified
*
* @return true if the year less than d2.year
* @return true if the years are same and month less than d2.month 
* @return true if the years and months of 2 object are same and day less than d2.day
* @return false anywhere else 
*/
private boolean isBefore(MyDate d2){
	if (year< d2.year)
		return true;
	if (year == d2.year){
		if (month <d2.month)
			return true;
		if (month == d2.month){
			if (day < d2.day)
			return true;
		}
	}
	return false;

}
/**
* Function: DaysOfYear  to get the number of days in a year
*
* @param y represent year want to get number of days
*
* @return 366 days if it is a leap year
* @return 365 days if it is not a leap year 
*/
private static int daysOfYear(int y){
	if (isLeapYear(y))
	return 366;
	return 365;
}
/**
* Function: daysBefore to calculate the number of days the Date object d1 before d2
*
* @param d1 Date object before
* @param d2 Date object after
*
* @return total number of days d1 before d2
*
*
*/
private static int daysBefore(MyDate d1, MyDate d2){
	int total=0;
	if (!d1.isValid() ||!d2.isValid()){
		System.out.println("Invalid date.");
		return total;
	}
	if (d2.isBefore(d1))
		return total;
	if (d1.year < d2.year){ //if the year is not the same
		for (int i =d1.year+1;i<d2.year;i++)//calcultate the total days of years after the d1.year to before the d2.year
		        total = total +daysOfYear(i);
		for (int i =d1.month+1; i<=12; i++)//add accumulatively the total days of months after the d1.month to December 
			total = total + daysOfMonth(i,d1.year); 

		for(int i = 1; i<d2.month;i++)//add accumulatively the total days of months from january to before d2.month 
			total = total + daysOfMonth(i,d2.year);
		total = total + daysOfMonth(d1.month,d1.year)-d1.day +d2.day-1;//add number of days after d1.day to the end of that month , add d2.day, and - 1 day (current day) 
	}
	else 	if(d1.month <d2.month){//when the year ia the same, check if the months is different
        	for (int i =d1.month+1; i<d2.month; i++)//add accumulatively the total days of months from after d1.month to before d2.month 
                	total = total + daysOfMonth(i,d1.year);

		total = total + daysOfMonth(d1.month,d1.year)-d1.day +d2.day-1;//add number of days after d1.day to the end of that month , add d2.day, and - 1 day (current day)
	}
	else if(d1.day<d2.day)//when the months and years are the same, check if d1.day less than d2.day)
		total = total+d2.day-d1.day-1;
 
	return total;

}
/**
* Method: daysBetween to calculate the days between a Date object and the other Date object
*
* @param d2 represent the other Date that we want to compute with 
*
* @return the total days betweens (the total of days d1 before d2 if d1 is before d2)
* 				( the total of days d2 before d1 if d2 is before d1)
*
*/
public int daysBetween(MyDate d2){
	MyDate d1 = new MyDate(month,day,year);
	if (!d1.isValid() ||!d2.isValid()){
                System.out.println("Invalid date.");
                return 0;
        }

	if(d1.isBefore(d2))
	return daysBefore(d1,d2);
	return daysBefore(d2,d1);
}
//public static void main (String [] args){
////	Scanner in = new Scanner(System.in);
//	String []a={"13/01/2010","03/31/2011","04/31/2012","02/29/2016","02/29/1500","02/29/1600"};
//	String []start= {"01/01/2013","12/31/2019","12/31/2016","01/01/1700","01/01/2000","02/12/2000"};
//	String []end={"12/30/2013","01/01/2020","01/01/2016","03/01/1700","03/01/2000","05/23/2005"};
//
//	for(int i=0; i< a.length; i++){
//		Date temp = new Date();
//		temp.setDate(a[i]);
//		System.out.printf(" Is %02d-%02d-%d a valid date ? : %b\n", temp.month, temp.day,temp.year, temp.isValid());
//	}
//	System.out.printf("\n");
//	for (int i=0; i<start.length;i++)
//	{
//	Date d1 = new Date();
//	Date d2 = new Date();
//	d1.setDate(start[i]);
//	d2.setDate(end[i]);
//	System.out.println("Days between two dates: [" +d1+"] and ["+d2+"] : "+d1.daysBetween(d2));
//
//}
//
//}
}
