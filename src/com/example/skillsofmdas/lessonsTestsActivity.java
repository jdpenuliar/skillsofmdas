package com.example.skillsofmdas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.skillsofmdas.Registerx.CreateUser;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class lessonsTestsActivity extends Activity {

	TextView tvTopic, tvLessonsQuestions;
	Button btnNext, btnPrevious;
	RadioButton rbChoice1, rbChoice2, rbChoice3, rbChoice4;
	ImageView ivLessonsTests;
	RadioGroup rgChoices;
	int lessonsTestsCounter = 0;
	public int x;
	String selectedAnswer;
	public int score;

	Random r = new Random();

	// first element of second dimension array is the lesson then second element
	// is the image name
	final String[] arrayLessonsCounting = new String[] {
			"Any number you can use for counting things: 0,1, 2, 3, 4, 5, ... (and so on).",
			"Does not include negative numbers.",
			"Does not include fractions (such as 1/2 or 3/7)",
			"Does not include decimals (such as 0.95 or 1.3)",
			"0,1,2,3,4,5,6,7,8,9",
			"10,20,30,40,50,60,70,80,90,100",
			"0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20 and so on"};
	final int[] arrayLessonsCountingImages = { R.drawable.c1, R.drawable.c2, R.drawable.c3,
			R.drawable.c3, R.drawable.c4, R.drawable.c4, R.drawable.c4,
			R.drawable.c4, R.drawable.c4, R.drawable.c4 };
	final String[] arrayLessonsAddition = new String[] {
			"Addition is bringing two or more numbers (or things) together to make a new total.",
			"Here 1 ball is added to 1 ball to make 2 balls", "Using Numerals it is 1 + 1 = 2",
			"And in words it is \"One plus one equals two\"",
			"Swapping the position of the numbers we are adding still gets the same result!", "3 + 2 = 5 is also",
			"2 + 3 = 5", "To add larger numbers use Column Addition",
			"Other names for Addition are Sum, Plus, Increase, Total",
			"And the numbers to be added together are called the \"Addends\"" };
	final int[] arrayLessonsAdditionImages = { R.drawable.addx, R.drawable.add, R.drawable.addx,
			R.drawable.addx, R.drawable.addx, R.drawable.add_3_2, R.drawable.add_2_3,
			R.drawable.add_carry_below, R.drawable.addx, R.drawable.addition };
	final String[] arrayLessonsSubtraction = new String[] { "Subtraction is taking one number away from another",
			"Start with 5 apples, then subtract 2, we are left with 3 apples. This can be written as 5 - 2 = 3",
			"Other names used in subtraction are Minus, Less, Difference, Decrease, Take Away, Deduct.",
			"The names of the numbers in a subtraction fact are: Minuend - Subtrahend = Difference",
			"Minuend: The number that is to be subtracted from.", "Subtrahend: The number that is to be subtracted.",
			"Difference: The result of subtracting one number from another.",
			"Subtraction by \"Regrouping\" (Also called \"borrowing\" or \"trading\")",
			"Subtraction by \"Regrouping\" (Also called \"borrowing\" or \"trading\")" };
	final int[] arrayLessonsSubtractionImages = { R.drawable.sub, R.drawable.subtraction,
			R.drawable.subtraction_names, R.drawable.subtraction_names, R.drawable.subtraction_names,
			R.drawable.subtraction_names, R.drawable.subtraction_names, R.drawable.cb, R.drawable.sub44 };
	final String[] arrayLessonsMultiplication = new String[] {
			"Multiplication (often denoted by the cross symbol \"×\", by a point \"·\" or by the absence of symbol) is one of the four elementary, mathematical operations of arithmetic",
			"The basic idea of multiplication is repeated addition.",
			"But as well as multiplying by whole numbers, we can also multiply by fractions, decimals and more.",
			"When we multiply two numbers, it does not matter which is first or second, the answer is always the same.",
			"Your life will be a lot easier when you can simply remember the multiplication tables. So ... train your memory!",
			"Long Multiplication is a special method for multiplying larger numbers." };
	final int[] arrayLessonsMultiplicationImages = { R.drawable.mul, R.drawable.mul,
			R.drawable.mul, R.drawable.mcommutative, R.drawable.mtable, R.drawable.mlong };
	final String[] arrayLessonsDivision = new String[] { "Division is splitting into equal parts or groups.",
			"There are special names for each number in a division: dividend ÷ divisor = quotient",
			"Example: in 12 ÷ 3 = 4", "12 is the dividend", "3 is the divisor", "4 is the quotient",
			"Sometimes we cannot divide things up evenly ... there may be something left over.",
			"There are 7 bones to share with 2 pups. But 7 cannot be divided exactly into 2 groups, so each pup gets 3 bones, but there will be 1 left over",
			"Long Division", "Long Division" };
	final int[] arrayLessonsDivisionImages = { R.drawable.chocolatesdiv, R.drawable.chocolatesdiv,
			R.drawable.chocolatesdiv, R.drawable.chocolatesdiv, R.drawable.chocolatesdiv, R.drawable.chocolatesdiv,
			R.drawable.divisionremeinder, R.drawable.divisionremeinder, R.drawable.longdivision42525,
			R.drawable.longdivision4 };
	boolean isImageFitToScreen;

	final String[][] arrayTestsCountingTest1 = new String[][] { { "_, 12, 13, 14, 15", "11", "12", "10", "13" }, // 11
		{ "8, 9, 10, _, 12", "110", "10", "11", "100" }, // 11
		{ "1, 2, 3, 4, 5, _, 7, 8", "6", "10", "8", "2" }, // 6
		{ "4, 5, 6, 7, _, 8, 9", "15", "8", "9", "2" }, // 8
		{ "8, 9, _, 11, 12", "10", "16", "8", "2" }, // 10
		{ "10, 11, 12, 13, _, 15, 16", "6", "10", "8", "14" }, // 14
		{ "10, _, 12, 13", "12", "10", "11", "24" }, // 11
		{ "11, _, 9, 8, 7", "6", "8", "10", "14" }, // 10
		{ "7, 6, _, 4, 3", "5", "10", "1", "3" }, // 5
		{ "_, 9, 8, 7", "6", "10", "8", "14" },// 10
};
	final String[][] arrayTestsCountingTest2 = new String[][] { { "2, _, 6, 8", "9", "7", "2", "4" }, // 4
			{ "26, _, 30, 32, 34", "25", "24", "27", "28" }, // 28
			{ "30, 28, _, 24", "25", "27", "28", "26" }, // 26
			{ "26, _, 30, 32", "28", "8", "2", "208" }, // 28
			{ "2, 4, 6, 8, _", "10", "16", "8", "2" }, // 10
			{ "22, _, 26, 28", "10", "14", "24", "34" }, // 24
			{ "_, 40, 42, 44", "16", "38", "28", "24" }, // 38
			{ "42, _, 38, 36", "41", "31", "21", "40" }, // 40
			{ "_, 32, 34, 36", "30", "40", "21", "11" }, // 30
			{ "46, _, 50, 52", "47", "40", "48", "44" }// 48
	};
	final String[][] arrayTestsCountingTest3 = new String[][] { { "12, _, 18, 21", "15", "16", "14", "13" }, // 15
			{ "18, _, 24, 27", "25", "24", "21", "28" }, // 21
			{ "30, 33, _, 39", "28", "38", "58", "36" }, // 36
			{ "3, 6, 9, _", "7", "5", "13", "12" }, // 12
			{ "27, 24, _, 18", "20", "22", "21", "24" }, // 21
			{ "_, 18, 21", "16", "15", "12", "14" }, // 15
			{ "_, 30, 27, 24", "37", "38", "28", "33" }, // 33
			{ "36, _, 42, 45", "41", "31", "21", "39" }, // 39
			{ "_, 24, _, 30", "21,27", "23,26", "25,26", "26,27" }, // 21,27
			{ "3, _, 9, _", "21,27", "6,12", "12,25", "4,6" }, // 6,12
	};
	final String[][] arrayTestsCountingTest4 = new String[][] { { "_, 82, 84, 86", "70", "60", "80", "90" }, // 80
			{ "40, _, 48, _, 56", "44,52", "44,53", "44,54", "55,44" }, // 44,52
			{ "36, _, 44, _, 52", "36,48", "36,40", "34,36", "32,32" }, // 36,48
			{ "_, 56, 58", "57", "52", "51", "61" }, // 52
			{ "60, _, 68, 72", "64", "60", "68", "62" }, // 64
			{ "80, _, 88, 92", "82", "83", "80", "84" }, // 84
			{ "68, _, 60", "68", "58", "48", "64" }, // 64
			{ "40, 44, _, 52", "68", "48", "56", "58" }, // 48
			{ "36, _, 28", "35", "41", "30", "32" }, // 32
			{ "_, 56, 60, 64", "41", "52", "51", "55" }// 52
	};
	
	final String[][] arrayTestsAdditionTest1 = new String[][] { { "5 + 6", "11", "12", "10", "13" }, // 11
			{ "10 + 1", "110", "10", "11", "100" }, // 11
			{ "4 + 2", "6", "10", "8", "2" }, // 6
			{ "5 + 3", "15", "8", "9", "2" }, // 8
			{ "8 + 2", "10", "16", "8", "2" }, // 10
			{ "8 + 6", "6", "10", "8", "14" }, // 14
			{ "8 + 3", "12", "10", "11", "24" }, // 11
			{ "4 + 6", "6", "8", "10", "14" }, // 10
			{ "2 + 3", "5", "10", "1", "3" }, // 5
			{ "6 + 4", "6", "10", "8", "14" },// 10
	};
	final String[][] arrayTestsAdditionTest2 = new String[][] { { "13 + 16", "29", "27", "28", "30" }, // 29
			{ "24 + 4", "25", "24", "27", "28" }, // 28
			{ "21 + 5", "25", "27", "28", "26" }, // 26
			{ "20 + 8", "28", "8", "2", "208" }, // 28
			{ "8 + 2", "10", "16", "8", "2" }, // 10
			{ "10 + 14", "10", "14", "24", "34" }, // 24
			{ "22 + 16", "16", "38", "28", "24" }, // 38
			{ "21 + 20", "41", "31", "21", "40" }, // 41
			{ "11 + 20", "31", "41", "21", "11" }, // 31
			{ "24 + 23", "47", "40", "48", "44" }// 47
	};
	final String[][] arrayTestsAdditionTest3 = new String[][] { { "9 + 6", "15", "16", "14", "13" }, // 15
			{ "13 + 8", "25", "24", "21", "28" }, // 21
			{ "19 + 16", "28", "38", "58", "30" }, // 38
			{ "5 + 7", "7", "5", "13", "12" }, // 12
			{ "8 + 14", "20", "22", "28", "24" }, // 22
			{ "8 + 8", "16", "18", "12", "14" }, // 16
			{ "19 + 12", "37", "38", "28", "24" }, // 38
			{ "22 + 18", "41", "31", "21", "40" }, // 40
			{ "17 + 8", "35", "41", "25", "15" }, // 25
			{ "5 + 6", "11", "22", "10", "9" }// 11
	};
	final String[][] arrayTestsAdditionTest4 = new String[][] { { "32 + 38", "70", "60", "80", "90" }, // 70
			{ "56 + 10", "56", "50", "560", "66" }, // 66
			{ "22 + 14", "38", "36", "34", "32" }, // 36
			{ "31 + 20", "57", "52", "51", "61" }, // 51
			{ "49 + 13", "64", "60", "68", "62" }, // 62
			{ "45 + 37", "82", "83", "80", "84" }, // 82
			{ "48 + 20", "68", "58", "48", "64" }, // 68
			{ "25 + 33", "68", "48", "56", "58" }, // 58
			{ "13 + 17", "35", "41", "30", "32" }, // 30
			{ "19 + 33", "41", "52", "51", "55" }// 52
	};

	final String[][] arrayTestsSubtractionTest1 = new String[][] { { "16 - 11", "5", "4", "3", "2" }, // 5
			{ "9 - 2", "11", "7", "8", "9" }, // 7
			{ "18 - 16", "10", "12", "2", "14" }, // 2
			{ "19 - 9", "15", "10", "9", "2" }, // 10
			{ "2 - 1", "0", "1", "3", "2" }, // 1
			{ "6 - 5", "1", "7", "8", "20" }, // 1
			{ "5 - 1", "1", "4", "2", "5" }, // 4
			{ "25 - 14", "22", "11", "33", "54" }, // 11
			{ "15 - 1", "15", "10", "14", "13" }, // 14
			{ "6 - 4", "6", "10", "8", "2" }// 2
	};
	final String[][] arrayTestsSubtractionTest2 = new String[][] { { "10 - 3", "3", "14", "13", "7" }, // 7
			{ "12 - 6", "11", "7", "6", "9" }, // 6
			{ "22 - 9", "13", "12", "2", "14" }, // 13
			{ "11 - 7", "5", "4", "9", "2" }, // 4
			{ "15 - 6", "10", "11", "13", "9" }, // 9
			{ "24 - 19", "5", "7", "8", "10" }, // 5
			{ "23 - 18", "1", "4", "2", "5" }, // 5
			{ "22 - 19", "2", "1", "3", "4" }, // 3
			{ "20 - 5", "11", "7", "5", "8" }, // 5
			{ "24 - 9", "15", "10", "18", "12" }// 15
	};
	final String[][] arrayTestsSubtractionTest3 = new String[][] { { "16 - 12", "3", "4", "1", "7" }, // 4
			{ "10 - 3", "11", "7", "6", "9" }, // 7
			{ "12 - 6", "7", "12", "6", "14" }, // 6
			{ "18 - 17", "5", "4", "1", "2" }, // 1
			{ "19 - 10", "10", "11", "13", "9" }, // 9
			{ "22 - 11", "15", "11", "18", "10" }, // 11
			{ "7 - 3", "1", "4", "2", "5" }, // 4
			{ "15 - 2", "2", "1", "3", "4" }, // 3
			{ "6 - 6", "6", "0", "5", "8" }, // 0
			{ "6 - 2", "3", "1", "4", "2" }// 4
	};
	final String[][] arrayTestsSubtractionTest4 = new String[][] { { "42 - 31", "13", "14", "11", "17" }, // 11
			{ "13 - 7", "11", "7", "6", "9" }, // 6
			{ "58 - 22", "37", "12", "36", "14" }, // 36
			{ "51 - 41", "50", "40", "10", "20" }, // 10
			{ "54 - 51", "0", "1", "3", "9" }, // 3
			{ "43 - 25", "15", "11", "18", "10" }, // 18
			{ "40 - 15", "21", "24", "25", "15" }, // 25
			{ "31 - 15", "16", "11", "13", "14" }, // 16
			{ "54 - 34", "26", "20", "25", "28" }, // 20
			{ "57 - 13", "34", "41", "45", "42" }// 42
	};

	final String[][] arrayTestsMultiplicationTest1 = new String[][] { { "2 x 4", "5", "4", "6", "8" }, // 8
			{ "4 x 4", "16", "17", "18", "19" }, // 16
			{ "5 x 3", "10", "15", "12", "14" }, // 15
			{ "3 x 5", "15", "10", "9", "2" }, // 15
			{ "2 x 2 ", "4", "1", "3", "2" }, // 4
			{ "3 x 6", "11", "17", "18", "20" }, // 18
			{ "2 x 5", "10", "14", "12", "15" }, // 10
			{ "4 x 5", "20", "10", "30", "40" }, // 20
			{ "4 x 6", "15", "24", "14", "13" }, // 24
			{ "2 x 3", "6", "10", "8", "2" }// 6
	};
	final String[][] arrayTestsMultiplicationTest2 = new String[][] { { "2 x 6", "12", "14", "16", "18" }, // 12
			{ "6 x 5", "26", "27", "28", "30" }, // 30
			{ "7 x 5", "15", "45", "35", "34" }, // 35
			{ "3 x 8", "25", "20", "29", "24" }, // 24
			{ "9 x 5 ", "44", "41", "43", "45" }, // 44
			{ "2 x 2", "4", "7", "8", "0" }, // 4
			{ "3 x 10", "33", "30", "32", "35" }, // 30
			{ "2 x 9", "20", "18", "10", "16" }, // 18
			{ "7 x 7", "49", "54", "64", "63" }, // 49
			{ "7 x 10", "60", "10", "70", "20" }// 70
	};
	final String[][] arrayTestsMultiplicationTest3 = new String[][] { { "2 x 7", "12", "14", "16", "18" }, // 14
			{ "6 x 7", "42", "47", "48", "40" }, // 42
			{ "8 x 6", "15", "45", "48", "34" }, // 48
			{ "4 x 10", "25", "40", "29", "44" }, // 40
			{ "10 x 6 ", "64", "66", "60", "65" }, // 60
			{ "2 x 2", "4", "7", "8", "0" }, // 4
			{ "4 x 12", "43", "40", "48", "45" }, // 48
			{ "2 x 10", "20", "18", "10", "16" }, // 20
			{ "8 x 8", "49", "54", "64", "63" }, // 64
			{ "8 x 12", "96", "86", "80", "88" }// 96
	};
	final String[][] arrayTestsMultiplicationTest4 = new String[][] { { "3 x 9", "18", "21", "27", "24" }, // 27
			{ "9 x 8", "72", "77", "78", "72" }, // 72
			{ "10 x 8", "85", "86", "80", "72" }, // 80
			{ "5 x 12", "60", "40", "70", "50" }, // 60
			{ "13 x 7 ", "94", "96", "91", "92" }, // 91
			{ "4 x 4", "24", "16", "18", "20" }, // 16
			{ "5 x 15", "75", "60", "78", "65" }, // 30
			{ "3 x 13", "30", "38", "39", "36" }, // 39
			{ "10 x 11", "11", "101", "110", "111" }, // 110
			{ "10 x 15", "105", "150", "510", "515" }// 150
	};

	final String[][] arrayTestsDivisionTest1 = new String[][] { { "2 ÷ 2", "1", "4", "6", "2" }, // 1
			{ "15 ÷ 3", "6", "7", "8", "5" }, // 5
			{ "10 ÷ 5", "0", "5", "2", "4" }, // 2
			{ "6 ÷ 6", "8", "1", "7", "5" }, // 1
			{ "4 ÷ 2 ", "4", "1", "3", "2" }, // 2
			{ "6 ÷ 3", "4", "7", "8", "2" }, // 2
			{ "36 ÷ 6", "3", "4", "6", "5" }, // 6
			{ "4 ÷ 4", "1", "2", "3", "4" }, // 1
			{ "24 ÷ 6", "5", "4", "1", "3" }, // 4
			{ "10 ÷ 2", "6", "5", "8", "2" }// 5
	};
	final String[][] arrayTestsDivisionTest2 = new String[][] { { "3 ÷ 3", "3", "4", "6", "1" }, // 1
			{ "45 ÷ 5", "6", "7", "8", "9" }, // 9
			{ "18 ÷ 9", "0", "5", "2", "4" }, // 2
			{ "32 ÷ 4", "8", "1", "7", "5" }, // 8
			{ "20 ÷ 10 ", "4", "1", "3", "2" }, // 2
			{ "32 ÷ 8", "4", "7", "8", "2" }, // 4
			{ "12 ÷ 4", "3", "4", "2", "5" }, // 3
			{ "90 ÷ 9", "20", "10", "30", "40" }, // 10
			{ "7 ÷ 7", "5", "4", "1", "3" }, // 24
			{ "60 ÷ 10", "6", "10", "8", "2" }// 6
	};
	final String[][] arrayTestsDivisionTest3 = new String[][] { { "35 ÷ 7", "3", "2", "5", "1" }, // 5
			{ "252 ÷ 12", "21", "17", "28", "19" }, // 21
			{ "147 ÷ 9", "0", "5", "2", "4" }, // 2
			{ "209 ÷ 11", "15", "17", "18", "19" }, // 19
			{ "150 ÷ 25 ", "6", "2", "5", "8" }, // 6
			{ "200 ÷ 20", "40", "20", "2", "10" }, // 10
			{ "88 ÷ 11", "3", "8", "4", "5" }, // 8
			{ "550 ÷ 22", "25", "35", "50", "45" }, // 25
			{ "51 ÷ 17", "5", "4", "1", "3" }, // 3
			{ "336 ÷ 24", "6", "14", "18", "12" }// 14
	};
	final String[][] arrayTestsDivisionTest4 = new String[][] { { "231 x 7", "24", "21", "27", "33" }, // 33
			{ "432 x 12", "32", "36", "38", "32" }, // 36
			{ "297 x 9", "33", "32", "34", "35" }, // 33
			{ "330 x 11", "60", "40", "30", "50" }, // 30
			{ "456 x 12 ", "34", "36", "31", "38" }, // 38
			{ "280 x 7", "24", "10", "40", "20" }, // 40
			{ "341 x 11", "31", "30", "38", "35" }, // 31
			{ "448 x 8", "30", "38", "39", "56" }, // 56
			{ "50 x 3", "16 r2", "17 r3", "19 r1", "20 r0" }, // 16 r2
			{ "242 x 7", "34 r4", "34 r3", "35 r2", "34 r2" }// 34 r3
	};

	
	int[] arrayRandomGeneratedNumbers = new int[10];
	boolean arrayChecker = false;
	boolean arrayCheckerx = false;
	
	// question numbers divisible by only 1 and not 2, 3, or 4 -> answer is rb2
	// question numbers divisible by only 2 and not 3, or 4 except for 1 ->
	// answer is rb 1
	// question numbers divisible by only 3 and not 2, or 4 except for 1 ->
	// answer is 4
	// question numbers divisible by only 4 and not 2, or 3 except for 1 ->
	// answer 3

	// divisible only by 1 -> rb2
	// divisible only by 2 -> rb 1
	// divisible by 2 and 4 -> rb 4
	//

	// Progress Dialog
	private ProgressDialog pDialog;

	// JSON parser class
	JSONParser jsonParser = new JSONParser();

	// php login script

	// localhost :
	// testing on your device
	// put your local ip instead, on windows, run CMD > ipconfig
	// or in mac's terminal type ifconfig and look for the ip under en0 or en1
	// private static final String LOGIN_URL =
	// "http://xxx.xxx.x.x:1234/webservice/register.php";

	// testing on Emulator:
	private static final String LOGIN_URL = "http://10.0.2.2/Projects/skillsofmdas/updatescore.php";

	// testing from a real server:
	// private static final String LOGIN_URL =
	// "http://www.yourdomain.com/webservice/register.php";

	// ids
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MESSAGE = "message";
	public MediaPlayer mp;
	MediaPlayer mpButton;
	int firstRandom;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("abmkss", "abmkss");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lessonstests);

		mpButton = MediaPlayer.create(lessonsTestsActivity.this, R.raw.click);
		final SharedPreferences skillsOfMDASSharedPreferences = getSharedPreferences("skillsOfMDASData",
				Context.MODE_PRIVATE);
		final String optionsActivitySelectedButton = skillsOfMDASSharedPreferences
				.getString("optionsActivitySelectedButton", "0");
		final String mainActivitySelectedButton = skillsOfMDASSharedPreferences.getString("mainActivitySelectedButton",
				"0");
		final String testNumber = skillsOfMDASSharedPreferences.getString("testNumber", "0");

		for(x=0;x<10;x++){
			arrayRandomGeneratedNumbers[x] = 11;
		}
		/*Toast m = Toast.makeText(lessonsTestsActivity.this,
				mainActivitySelectedButton + ", " + optionsActivitySelectedButton + ", " + testNumber,
				Toast.LENGTH_SHORT);
		m.show();*/

		tvTopic = (TextView) findViewById(R.id.tvTopic);
		tvLessonsQuestions = (TextView) findViewById(R.id.tvLessonsQuestions);
		ivLessonsTests = (ImageView) findViewById(R.id.ivLessonsTests);
		btnNext = (Button) findViewById(R.id.btnNext);
		btnPrevious = (Button) findViewById(R.id.btnPrevious);
		rbChoice1 = (RadioButton) findViewById(R.id.rbChoice1);
		rbChoice2 = (RadioButton) findViewById(R.id.rbChoice2);
		rbChoice3 = (RadioButton) findViewById(R.id.rbChoice3);
		rbChoice4 = (RadioButton) findViewById(R.id.rbChoice4);
		rgChoices = (RadioGroup) findViewById(R.id.rgChoices);
		

		Log.d("mainActivitySelectedButton", mainActivitySelectedButton);
		Log.d("optionsActivitySelectedButton", optionsActivitySelectedButton);

		RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) ivLessonsTests.getLayoutParams();
		layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		ivLessonsTests.setLayoutParams(layoutParams);

		if (optionsActivitySelectedButton.equals("lessonsCounting")
				|| optionsActivitySelectedButton.equals("testsCounting")) {
			tvTopic.setText("Counting");
		} else if (optionsActivitySelectedButton.equals("lessonsAddition")
				|| optionsActivitySelectedButton.equals("testsAddition")) {
			tvTopic.setText("Addition");
		} else if (optionsActivitySelectedButton.equals("lessonsSubtraction")
				|| optionsActivitySelectedButton.equals("testsSubtraction")) {
			tvTopic.setText("Subtraction");
		} else if (optionsActivitySelectedButton.equals("lessonsMultiplication")
				|| optionsActivitySelectedButton.equals("testsMultiplication")) {
			tvTopic.setText("Multiplication");
		} else if (optionsActivitySelectedButton.equals("lessonsDivision")
				|| optionsActivitySelectedButton.equals("testsDivision")) {
			tvTopic.setText("Division");
		}

		if (mainActivitySelectedButton.equals("Lessons")) {
			rbChoice1.setVisibility(View.INVISIBLE);
			rbChoice2.setVisibility(View.INVISIBLE);
			rbChoice3.setVisibility(View.INVISIBLE);
			rbChoice4.setVisibility(View.INVISIBLE);
			ivLessonsTests.setVisibility(View.VISIBLE);
			btnPrevious.setVisibility(View.VISIBLE);
			if (lessonsTestsCounter == 0) {
				btnPrevious.setEnabled(false);
				setViewText(lessonsTestsCounter);
			} else {
				btnPrevious.setEnabled(true);
			}
		} else if (mainActivitySelectedButton.equals("Tests")) {
			rbChoice1.setVisibility(View.VISIBLE);
			rbChoice2.setVisibility(View.VISIBLE);
			rbChoice3.setVisibility(View.VISIBLE);
			rbChoice4.setVisibility(View.VISIBLE);
			ivLessonsTests.setVisibility(View.INVISIBLE);
			btnPrevious.setVisibility(View.INVISIBLE);
			btnNext.setVisibility(View.INVISIBLE);
			btnNext.setEnabled(false);
			btnPrevious.setEnabled(false);
			int randomGeneratedInt = r.nextInt(10);
			
			firstRandom = randomGeneratedInt;
			for(x=0; x <10;x++){
				if(arrayRandomGeneratedNumbers[x] == randomGeneratedInt){
					arrayChecker = true;
				}else{
					arrayChecker = false;
				}
			}
			
			if(arrayChecker == false){
				setViewText(randomGeneratedInt);
				arrayRandomGeneratedNumbers[0] = randomGeneratedInt;
			}
		}

		
		

		Log.d("lessonTestCounterbeforebuttonclick", Integer.toString(lessonsTestsCounter));
		btnNext.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mpButton.start();
				if (mainActivitySelectedButton.equals("Lessons")) {
					if (optionsActivitySelectedButton.equals("lessonsCounting")) {
						if (lessonsTestsCounter < arrayLessonsCounting.length - 1) {
							lessonsTestsCounter++;
							setViewText(lessonsTestsCounter);
							btnPrevious.setEnabled(true);
						} else {
							Log.d("lastarraylessonTextCounter", Integer.toString(lessonsTestsCounter));
						}
					} else if (optionsActivitySelectedButton.equals("lessonsAddition")) {
						if (lessonsTestsCounter < arrayLessonsAddition.length - 1) {
							lessonsTestsCounter++;
							setViewText(lessonsTestsCounter);
							btnPrevious.setEnabled(true);
						} else {
							Log.d("lastarraylessonTextCounter", Integer.toString(lessonsTestsCounter));
						}
					} else if (optionsActivitySelectedButton.equals("lessonsSubtraction")) {
						if (lessonsTestsCounter < arrayLessonsSubtraction.length - 1) {
							lessonsTestsCounter++;
							setViewText(lessonsTestsCounter);
							btnPrevious.setEnabled(true);
						} else {
						}
					} else if (optionsActivitySelectedButton.equals("lessonsMultiplication")) {
						if (lessonsTestsCounter < arrayLessonsMultiplication.length - 1) {
							lessonsTestsCounter++;
							setViewText(lessonsTestsCounter);
							btnPrevious.setEnabled(true);
						} else {
							Log.d("lastarraylessonTextCounter", Integer.toString(lessonsTestsCounter));
						}
					} else if (optionsActivitySelectedButton.equals("lessonsDivision")) {
						if (lessonsTestsCounter < arrayLessonsDivision.length - 1) {
							lessonsTestsCounter++;
							setViewText(lessonsTestsCounter);
							btnPrevious.setEnabled(true);
						} else {
							Log.d("lastarraylessonTextCounter", Integer.toString(lessonsTestsCounter));
						}
					}
				} else if (mainActivitySelectedButton.equals("Tests")) {
					if (lessonsTestsCounter == 0) {
						checkAnswers(lessonsTestsCounter);
						lessonsTestsCounter++;
						setViewText(lessonsTestsCounter);
					} else if (lessonsTestsCounter < 9) {
						checkAnswers(lessonsTestsCounter);
						lessonsTestsCounter++;
						setViewText(lessonsTestsCounter);
						btnPrevious.setEnabled(true);
					} else {
						checkAnswers(lessonsTestsCounter);
						Log.d("lastarraylessonTextCounter", Integer.toString(lessonsTestsCounter));
						Log.d("SCORE", Integer.toString(score));
						Toast m = Toast.makeText(lessonsTestsActivity.this, "Score: " + Integer.toString(score), Toast.LENGTH_SHORT);
						m.show();
						new UpdateScore().execute();
						
					}

				}

			}
		});
		btnPrevious.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				mpButton.start();
				if (lessonsTestsCounter <= 0) {
					btnPrevious.setEnabled(false);
				} else {
					Log.d("lessonTextCounter", Integer.toString(lessonsTestsCounter));
					lessonsTestsCounter--;
					Log.d("lessonTextCounter", Integer.toString(lessonsTestsCounter));
					if (lessonsTestsCounter == 0) {
						btnPrevious.setEnabled(false);
					}
					setViewText(lessonsTestsCounter);
				}

				int selectedRadioButton = rgChoices.getCheckedRadioButtonId();
				if (selectedRadioButton == rbChoice1.getId()) {
					Log.d("rbtest", String.valueOf(rbChoice1.getId()) + "=" + rbChoice1.getText());
				} else if (selectedRadioButton == rbChoice2.getId()) {
					Log.d("rbtest", String.valueOf(rbChoice2.getId()) + "=" + rbChoice2.getText());
				} else if (selectedRadioButton == rbChoice3.getId()) {
					Log.d("rbtest", String.valueOf(rbChoice3.getId()) + "=" + rbChoice3.getText());
				} else if (selectedRadioButton == rbChoice4.getId()) {
					Log.d("rbtest", String.valueOf(rbChoice4.getId()) + "=" + rbChoice4.getText());
				} else {
					Log.d("rbtest", "none");
				}
			}
		});
		ivLessonsTests.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (isImageFitToScreen) {
					isImageFitToScreen = false;
					ivLessonsTests.setLayoutParams(new RelativeLayout.LayoutParams(
							RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
					ivLessonsTests.setAdjustViewBounds(true);
					RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) ivLessonsTests
							.getLayoutParams();
					layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
					ivLessonsTests.setLayoutParams(layoutParams);
				} else {
					isImageFitToScreen = true;
					ivLessonsTests.setLayoutParams(new RelativeLayout.LayoutParams(
							RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
					RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) ivLessonsTests
							.getLayoutParams();
					layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
					ivLessonsTests.setLayoutParams(layoutParams);
					ivLessonsTests.setScaleType(ImageView.ScaleType.FIT_XY);

					ivLessonsTests.setAdjustViewBounds(true);
				}
				Toast m = Toast.makeText(lessonsTestsActivity.this, "Full screen test", Toast.LENGTH_SHORT);
				m.show();
			}
		});
	
		rgChoices.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.rbChoice1:
					Log.d("radiobuttonchecker", "1");

					mpButton.start();
					/*Log.d("random", String.valueOf(randomGeneratedInt));
					for(x=0;x<=9;x++){
						if(arrayRandomGeneratedNumbers[x] == randomGeneratedInt){
							arrayCheckerx = true;
						}
					}*/
					if (lessonsTestsCounter == 0) {
						checkAnswers(arrayRandomGeneratedNumbers[0]);
						lessonsTestsCounter++;
						int randomGeneratedIntx = r.nextInt(10);
						setViewText(randomGeneratedIntx);
						arrayRandomGeneratedNumbers[lessonsTestsCounter] = randomGeneratedIntx;
						rgChoices.clearCheck();
					} else if (lessonsTestsCounter < 9) {
						int randomGeneratedIntx = r.nextInt(10);
						checkAnswers(arrayRandomGeneratedNumbers[lessonsTestsCounter]);
						lessonsTestsCounter++;
						arrayRandomGeneratedNumbers[lessonsTestsCounter] = randomGeneratedIntx;
						setViewText(randomGeneratedIntx);
						rgChoices.clearCheck();
					} else {
						int randomGeneratedIntx = r.nextInt(10);
						checkAnswers(arrayRandomGeneratedNumbers[lessonsTestsCounter]);
						Log.d("lastarraylessonTextCounter", Integer.toString(lessonsTestsCounter));
						Log.d("SCORE", Integer.toString(score));
						new UpdateScore().execute();
						rgChoices.clearCheck();
					}
					
					x = 1;
					break;
				case R.id.rbChoice2:
					Log.d("radiobuttonchecker", "2");
					mpButton.start();
					x = 2;
					if (lessonsTestsCounter == 0) {
						checkAnswers(arrayRandomGeneratedNumbers[0]);
						lessonsTestsCounter++;
						int randomGeneratedIntx = r.nextInt(10);
						setViewText(randomGeneratedIntx);
						arrayRandomGeneratedNumbers[lessonsTestsCounter] = randomGeneratedIntx;
						rgChoices.clearCheck();
					} else if (lessonsTestsCounter < 9) {
						int randomGeneratedIntx = r.nextInt(10);
						checkAnswers(arrayRandomGeneratedNumbers[lessonsTestsCounter]);
						lessonsTestsCounter++;
						arrayRandomGeneratedNumbers[lessonsTestsCounter] = randomGeneratedIntx;
						setViewText(randomGeneratedIntx);
						rgChoices.clearCheck();
					} else {
						int randomGeneratedIntx = r.nextInt(10);
						checkAnswers(arrayRandomGeneratedNumbers[lessonsTestsCounter]);
						Log.d("lastarraylessonTextCounter", Integer.toString(lessonsTestsCounter));
						Log.d("SCORE", Integer.toString(score));
						new UpdateScore().execute();
						rgChoices.clearCheck();
					}
					break;
				case R.id.rbChoice3:
					Log.d("radiobuttonchecker", "3");

					mpButton.start();
					x = 3;
					if (lessonsTestsCounter == 0) {
						checkAnswers(arrayRandomGeneratedNumbers[0]);
						lessonsTestsCounter++;
						int randomGeneratedIntx = r.nextInt(10);
						setViewText(randomGeneratedIntx);
						arrayRandomGeneratedNumbers[lessonsTestsCounter] = randomGeneratedIntx;
						rgChoices.clearCheck();
					} else if (lessonsTestsCounter < 9) {
						int randomGeneratedIntx = r.nextInt(10);
						checkAnswers(arrayRandomGeneratedNumbers[lessonsTestsCounter]);
						lessonsTestsCounter++;
						arrayRandomGeneratedNumbers[lessonsTestsCounter] = randomGeneratedIntx;
						setViewText(randomGeneratedIntx);
						rgChoices.clearCheck();
					} else {
						int randomGeneratedIntx = r.nextInt(10);
						checkAnswers(arrayRandomGeneratedNumbers[lessonsTestsCounter]);
						Log.d("lastarraylessonTextCounter", Integer.toString(lessonsTestsCounter));
						Log.d("SCORE", Integer.toString(score));
						new UpdateScore().execute();
						rgChoices.clearCheck();
					}
					break;
				case R.id.rbChoice4:
					Log.d("radiobuttonchecker", "4");
					mpButton.start();
					x = 4;
					if (lessonsTestsCounter == 0) {
						checkAnswers(arrayRandomGeneratedNumbers[0]);
						lessonsTestsCounter++;
						int randomGeneratedIntx = r.nextInt(10);
						setViewText(randomGeneratedIntx);
						arrayRandomGeneratedNumbers[lessonsTestsCounter] = randomGeneratedIntx;
						rgChoices.clearCheck();
					} else if (lessonsTestsCounter < 9) {
						int randomGeneratedIntx = r.nextInt(10);
						checkAnswers(arrayRandomGeneratedNumbers[lessonsTestsCounter]);
						lessonsTestsCounter++;
						arrayRandomGeneratedNumbers[lessonsTestsCounter] = randomGeneratedIntx;
						setViewText(randomGeneratedIntx);
						rgChoices.clearCheck();
					} else {
						int randomGeneratedIntx = r.nextInt(10);
						checkAnswers(arrayRandomGeneratedNumbers[lessonsTestsCounter]);
						Log.d("lastarraylessonTextCounter", Integer.toString(lessonsTestsCounter));
						Log.d("SCORE", Integer.toString(score));
						new UpdateScore().execute();
						rgChoices.clearCheck();
					}
					break;
				default:
					x = 0;
					break;
				}
			}
		});
	
	}

	@Override
	public void onBackPressed() {
		// your code.

		mpButton.start();
		final SharedPreferences skillsOfMDASSharedPreferences = getSharedPreferences("skillsOfMDASData",
				Context.MODE_PRIVATE);
		SharedPreferences.Editor skillsOfMDASEditor = skillsOfMDASSharedPreferences.edit();
		skillsOfMDASEditor.putString("userID", null);
		skillsOfMDASEditor.putString("userName", null);
		skillsOfMDASEditor.putString("firstName", null);
		skillsOfMDASEditor.putString("middleName", null);
		skillsOfMDASEditor.putString("lastName", null);
		skillsOfMDASEditor.putString("userLevel", null);
		skillsOfMDASEditor.putString("addition1", null);
		skillsOfMDASEditor.putString("addition2", null);
		skillsOfMDASEditor.putString("addition3", null);
		skillsOfMDASEditor.putString("addition4", null);
		skillsOfMDASEditor.putString("subtraction1", null);
		skillsOfMDASEditor.putString("subtraction2", null);
		skillsOfMDASEditor.putString("subtraction3", null);
		skillsOfMDASEditor.putString("subtraction4", null);
		skillsOfMDASEditor.putString("multiplication1", null);
		skillsOfMDASEditor.putString("multiplication2", null);
		skillsOfMDASEditor.putString("multiplication3", null);
		skillsOfMDASEditor.putString("multiplication4", null);
		skillsOfMDASEditor.putString("division1", null);
		skillsOfMDASEditor.putString("division2", null);
		skillsOfMDASEditor.putString("division3", null);
		skillsOfMDASEditor.putString("division4", null);
		skillsOfMDASEditor.putString("score", null);
		skillsOfMDASEditor.commit();
		Intent mInHome = new Intent(lessonsTestsActivity.this, mainActivity.class);
		this.startActivity(mInHome);
		this.finish();
	}

	public void setViewText(int lessonsTestsCounter) {
		final SharedPreferences skillsOfMDASSharedPreferences = getSharedPreferences("skillsOfMDASData",
				Context.MODE_PRIVATE);
		final String optionsActivitySelectedButton = skillsOfMDASSharedPreferences
				.getString("optionsActivitySelectedButton", "0");
		String mainActivitySelectedButton = skillsOfMDASSharedPreferences.getString("mainActivitySelectedButton", "0");
		String testNumber = skillsOfMDASSharedPreferences.getString("testNumber", "0");

		if (mainActivitySelectedButton.equals("Lessons")) {
			if (optionsActivitySelectedButton.equals("lessonsCounting")) {
				tvLessonsQuestions.setText(arrayLessonsCounting[lessonsTestsCounter]);
				ivLessonsTests
						.setImageDrawable(getResources().getDrawable(arrayLessonsCountingImages[lessonsTestsCounter]));
			}else if (optionsActivitySelectedButton.equals("lessonsAddition")) {
				tvLessonsQuestions.setText(arrayLessonsAddition[lessonsTestsCounter]);
				ivLessonsTests
						.setImageDrawable(getResources().getDrawable(arrayLessonsAdditionImages[lessonsTestsCounter]));
			} else if (optionsActivitySelectedButton.equals("lessonsSubtraction")) {
				tvLessonsQuestions.setText(arrayLessonsSubtraction[lessonsTestsCounter]);
				ivLessonsTests.setImageDrawable(
						getResources().getDrawable(arrayLessonsSubtractionImages[lessonsTestsCounter]));
			} else if (optionsActivitySelectedButton.equals("lessonsMultiplication")) {
				tvLessonsQuestions.setText(arrayLessonsMultiplication[lessonsTestsCounter]);
				ivLessonsTests.setImageDrawable(
						getResources().getDrawable(arrayLessonsMultiplicationImages[lessonsTestsCounter]));
			} else if (optionsActivitySelectedButton.equals("lessonsDivision")) {
				tvLessonsQuestions.setText(arrayLessonsDivision[lessonsTestsCounter]);
				ivLessonsTests
						.setImageDrawable(getResources().getDrawable(arrayLessonsDivisionImages[lessonsTestsCounter]));
			}
		} else if (mainActivitySelectedButton.equals("Tests")) {
			if (optionsActivitySelectedButton.equals("testsCounting")) {
				if (testNumber.equals("1")) {
					tvLessonsQuestions.setText(arrayTestsCountingTest1[lessonsTestsCounter][0]);
					rbChoice1.setText(arrayTestsCountingTest1[lessonsTestsCounter][1]);
					rbChoice2.setText(arrayTestsCountingTest1[lessonsTestsCounter][2]);
					rbChoice3.setText(arrayTestsCountingTest1[lessonsTestsCounter][3]);
					rbChoice4.setText(arrayTestsCountingTest1[lessonsTestsCounter][4]);
				} else if (testNumber.equals("2")) {
					tvLessonsQuestions.setText(arrayTestsCountingTest2[lessonsTestsCounter][0]);
					rbChoice1.setText(arrayTestsCountingTest2[lessonsTestsCounter][1]);
					rbChoice2.setText(arrayTestsCountingTest2[lessonsTestsCounter][2]);
					rbChoice3.setText(arrayTestsCountingTest2[lessonsTestsCounter][3]);
					rbChoice4.setText(arrayTestsCountingTest2[lessonsTestsCounter][4]);
				} else if (testNumber.equals("3")) {
					tvLessonsQuestions.setText(arrayTestsCountingTest3[lessonsTestsCounter][0]);
					rbChoice1.setText(arrayTestsCountingTest3[lessonsTestsCounter][1]);
					rbChoice2.setText(arrayTestsCountingTest3[lessonsTestsCounter][2]);
					rbChoice3.setText(arrayTestsCountingTest3[lessonsTestsCounter][3]);
					rbChoice4.setText(arrayTestsCountingTest3[lessonsTestsCounter][4]);
				} else if (testNumber.equals("4")) {
					tvLessonsQuestions.setText(arrayTestsCountingTest4[lessonsTestsCounter][0]);
					rbChoice1.setText(arrayTestsCountingTest4[lessonsTestsCounter][1]);
					rbChoice2.setText(arrayTestsCountingTest4[lessonsTestsCounter][2]);
					rbChoice3.setText(arrayTestsCountingTest4[lessonsTestsCounter][3]);
					rbChoice4.setText(arrayTestsCountingTest4[lessonsTestsCounter][4]);
				}
			}else if (optionsActivitySelectedButton.equals("testsAddition")) {
				if (testNumber.equals("1")) {
					tvLessonsQuestions.setText(arrayTestsAdditionTest1[lessonsTestsCounter][0]);
					rbChoice1.setText(arrayTestsAdditionTest1[lessonsTestsCounter][1]);
					rbChoice2.setText(arrayTestsAdditionTest1[lessonsTestsCounter][2]);
					rbChoice3.setText(arrayTestsAdditionTest1[lessonsTestsCounter][3]);
					rbChoice4.setText(arrayTestsAdditionTest1[lessonsTestsCounter][4]);
				} else if (testNumber.equals("2")) {
					tvLessonsQuestions.setText(arrayTestsAdditionTest2[lessonsTestsCounter][0]);
					rbChoice1.setText(arrayTestsAdditionTest2[lessonsTestsCounter][1]);
					rbChoice2.setText(arrayTestsAdditionTest2[lessonsTestsCounter][2]);
					rbChoice3.setText(arrayTestsAdditionTest2[lessonsTestsCounter][3]);
					rbChoice4.setText(arrayTestsAdditionTest2[lessonsTestsCounter][4]);
				} else if (testNumber.equals("3")) {
					tvLessonsQuestions.setText(arrayTestsAdditionTest3[lessonsTestsCounter][0]);
					rbChoice1.setText(arrayTestsAdditionTest3[lessonsTestsCounter][1]);
					rbChoice2.setText(arrayTestsAdditionTest3[lessonsTestsCounter][2]);
					rbChoice3.setText(arrayTestsAdditionTest3[lessonsTestsCounter][3]);
					rbChoice4.setText(arrayTestsAdditionTest3[lessonsTestsCounter][4]);
				} else if (testNumber.equals("4")) {
					tvLessonsQuestions.setText(arrayTestsAdditionTest4[lessonsTestsCounter][0]);
					rbChoice1.setText(arrayTestsAdditionTest4[lessonsTestsCounter][1]);
					rbChoice2.setText(arrayTestsAdditionTest4[lessonsTestsCounter][2]);
					rbChoice3.setText(arrayTestsAdditionTest4[lessonsTestsCounter][3]);
					rbChoice4.setText(arrayTestsAdditionTest4[lessonsTestsCounter][4]);
				}
			} else if (optionsActivitySelectedButton.equals("testsSubtraction")) {
				if (testNumber.equals("1")) {
					tvLessonsQuestions.setText(arrayTestsSubtractionTest1[lessonsTestsCounter][0]);
					rbChoice1.setText(arrayTestsSubtractionTest1[lessonsTestsCounter][1]);
					rbChoice2.setText(arrayTestsSubtractionTest1[lessonsTestsCounter][2]);
					rbChoice3.setText(arrayTestsSubtractionTest1[lessonsTestsCounter][3]);
					rbChoice4.setText(arrayTestsSubtractionTest1[lessonsTestsCounter][4]);
				} else if (testNumber.equals("2")) {
					tvLessonsQuestions.setText(arrayTestsSubtractionTest2[lessonsTestsCounter][0]);
					rbChoice1.setText(arrayTestsSubtractionTest2[lessonsTestsCounter][1]);
					rbChoice2.setText(arrayTestsSubtractionTest2[lessonsTestsCounter][2]);
					rbChoice3.setText(arrayTestsSubtractionTest2[lessonsTestsCounter][3]);
					rbChoice4.setText(arrayTestsSubtractionTest2[lessonsTestsCounter][4]);
				} else if (testNumber.equals("3")) {
					tvLessonsQuestions.setText(arrayTestsSubtractionTest3[lessonsTestsCounter][0]);
					rbChoice1.setText(arrayTestsSubtractionTest3[lessonsTestsCounter][1]);
					rbChoice2.setText(arrayTestsSubtractionTest3[lessonsTestsCounter][2]);
					rbChoice3.setText(arrayTestsSubtractionTest3[lessonsTestsCounter][3]);
					rbChoice4.setText(arrayTestsSubtractionTest3[lessonsTestsCounter][4]);
				} else if (testNumber.equals("4")) {
					tvLessonsQuestions.setText(arrayTestsSubtractionTest4[lessonsTestsCounter][0]);
					rbChoice1.setText(arrayTestsSubtractionTest4[lessonsTestsCounter][1]);
					rbChoice2.setText(arrayTestsSubtractionTest4[lessonsTestsCounter][2]);
					rbChoice3.setText(arrayTestsSubtractionTest4[lessonsTestsCounter][3]);
					rbChoice4.setText(arrayTestsSubtractionTest4[lessonsTestsCounter][4]);
				}
			} else if (optionsActivitySelectedButton.equals("testsMultiplication")) {
				if (testNumber.equals("1")) {
					tvLessonsQuestions.setText(arrayTestsMultiplicationTest1[lessonsTestsCounter][0]);
					rbChoice1.setText(arrayTestsMultiplicationTest1[lessonsTestsCounter][1]);
					rbChoice2.setText(arrayTestsMultiplicationTest1[lessonsTestsCounter][2]);
					rbChoice3.setText(arrayTestsMultiplicationTest1[lessonsTestsCounter][3]);
					rbChoice4.setText(arrayTestsMultiplicationTest1[lessonsTestsCounter][4]);
				} else if (testNumber.equals("2")) {
					tvLessonsQuestions.setText(arrayTestsMultiplicationTest2[lessonsTestsCounter][0]);
					rbChoice1.setText(arrayTestsMultiplicationTest2[lessonsTestsCounter][1]);
					rbChoice2.setText(arrayTestsMultiplicationTest2[lessonsTestsCounter][2]);
					rbChoice3.setText(arrayTestsMultiplicationTest2[lessonsTestsCounter][3]);
					rbChoice4.setText(arrayTestsMultiplicationTest2[lessonsTestsCounter][4]);
				} else if (testNumber.equals("3")) {
					tvLessonsQuestions.setText(arrayTestsMultiplicationTest3[lessonsTestsCounter][0]);
					rbChoice1.setText(arrayTestsMultiplicationTest3[lessonsTestsCounter][1]);
					rbChoice2.setText(arrayTestsMultiplicationTest3[lessonsTestsCounter][2]);
					rbChoice3.setText(arrayTestsMultiplicationTest3[lessonsTestsCounter][3]);
					rbChoice4.setText(arrayTestsMultiplicationTest3[lessonsTestsCounter][4]);
				} else if (testNumber.equals("4")) {
					tvLessonsQuestions.setText(arrayTestsMultiplicationTest4[lessonsTestsCounter][0]);
					rbChoice1.setText(arrayTestsMultiplicationTest4[lessonsTestsCounter][1]);
					rbChoice2.setText(arrayTestsMultiplicationTest4[lessonsTestsCounter][2]);
					rbChoice3.setText(arrayTestsMultiplicationTest4[lessonsTestsCounter][3]);
					rbChoice4.setText(arrayTestsMultiplicationTest4[lessonsTestsCounter][4]);
				}
			} else if (optionsActivitySelectedButton.equals("testsDivision")) {
				if (testNumber.equals("1")) {
					tvLessonsQuestions.setText(arrayTestsDivisionTest1[lessonsTestsCounter][0]);
					rbChoice1.setText(arrayTestsDivisionTest1[lessonsTestsCounter][1]);
					rbChoice2.setText(arrayTestsDivisionTest1[lessonsTestsCounter][2]);
					rbChoice3.setText(arrayTestsDivisionTest1[lessonsTestsCounter][3]);
					rbChoice4.setText(arrayTestsDivisionTest1[lessonsTestsCounter][4]);
				} else if (testNumber.equals("2")) {
					tvLessonsQuestions.setText(arrayTestsDivisionTest2[lessonsTestsCounter][0]);
					rbChoice1.setText(arrayTestsDivisionTest2[lessonsTestsCounter][1]);
					rbChoice2.setText(arrayTestsDivisionTest2[lessonsTestsCounter][2]);
					rbChoice3.setText(arrayTestsDivisionTest2[lessonsTestsCounter][3]);
					rbChoice4.setText(arrayTestsDivisionTest2[lessonsTestsCounter][4]);
				} else if (testNumber.equals("3")) {
					tvLessonsQuestions.setText(arrayTestsDivisionTest3[lessonsTestsCounter][0]);
					rbChoice1.setText(arrayTestsDivisionTest3[lessonsTestsCounter][1]);
					rbChoice2.setText(arrayTestsDivisionTest3[lessonsTestsCounter][2]);
					rbChoice3.setText(arrayTestsDivisionTest3[lessonsTestsCounter][3]);
					rbChoice4.setText(arrayTestsDivisionTest3[lessonsTestsCounter][4]);
				} else if (testNumber.equals("4")) {
					tvLessonsQuestions.setText(arrayTestsDivisionTest4[lessonsTestsCounter][0]);
					rbChoice1.setText(arrayTestsDivisionTest4[lessonsTestsCounter][1]);
					rbChoice2.setText(arrayTestsDivisionTest4[lessonsTestsCounter][2]);
					rbChoice3.setText(arrayTestsDivisionTest4[lessonsTestsCounter][3]);
					rbChoice4.setText(arrayTestsDivisionTest4[lessonsTestsCounter][4]);
				}
			}
			rgChoices.clearCheck();
		}
	}

	public void checkAnswers(int lessonsTestsCounter) {

		int selectedRadioButton = rgChoices.getCheckedRadioButtonId();
		Log.d("selectedRadioButton", Integer.toString(selectedRadioButton));
		if (selectedRadioButton == rbChoice1.getId()) {
			Log.d("rbtest", String.valueOf(rbChoice1.getId()) + "=" + rbChoice1.getText());
			selectedAnswer = rbChoice1.getText().toString();
		} else if (selectedRadioButton == rbChoice2.getId()) {
			Log.d("rbtest", String.valueOf(rbChoice2.getId()) + "=" + rbChoice2.getText());
			selectedAnswer = rbChoice2.getText().toString();
		} else if (selectedRadioButton == rbChoice3.getId()) {
			Log.d("rbtest", String.valueOf(rbChoice3.getId()) + "=" + rbChoice3.getText());
			selectedAnswer = rbChoice3.getText().toString();
		} else if (selectedRadioButton == rbChoice4.getId()) {
			Log.d("rbtest", String.valueOf(rbChoice4.getId()) + "=" + rbChoice4.getText());
			selectedAnswer = rbChoice4.getText().toString();
		} else {
			Log.d("rbtest", "none");
			selectedAnswer = rbChoice1.getText().toString();
		}

		final SharedPreferences skillsOfMDASSharedPreferences = getSharedPreferences("skillsOfMDASData",
				Context.MODE_PRIVATE);
		final String optionsActivitySelectedButton = skillsOfMDASSharedPreferences
				.getString("optionsActivitySelectedButton", "0");
		String mainActivitySelectedButton = skillsOfMDASSharedPreferences.getString("mainActivitySelectedButton", "0");
		String testNumber = skillsOfMDASSharedPreferences.getString("testNumber", "0");

		Log.d("selectedAnswer", selectedAnswer);

		Log.d("lessontestcounter", Integer.toString(lessonsTestsCounter));

		if (optionsActivitySelectedButton.equals("testsCounting")) {
			if (testNumber.equals("1")) {
				if (lessonsTestsCounter == 0 && selectedAnswer.equals("11")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 1 && selectedAnswer.equals("11")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 2 && selectedAnswer.equals("6")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 3 && selectedAnswer.equals("8")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 4 && selectedAnswer.equals("10")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 5 && selectedAnswer.equals("14")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 6 && selectedAnswer.equals("11")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 7 && selectedAnswer.equals("10")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 8 && selectedAnswer.equals("5")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 9 && selectedAnswer.equals("10")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				}
			} else if (testNumber.equals("2")) {
				if (lessonsTestsCounter == 0 && selectedAnswer.equals("4")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 1 && selectedAnswer.equals("28")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 2 && selectedAnswer.equals("26")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 3 && selectedAnswer.equals("28")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 4 && selectedAnswer.equals("10")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 5 && selectedAnswer.equals("24")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 6 && selectedAnswer.equals("38")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 7 && selectedAnswer.equals("40")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 8 && selectedAnswer.equals("30")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 9 && selectedAnswer.equals("48")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				}
			} else if (testNumber.equals("3")) {
				if (lessonsTestsCounter == 0 && selectedAnswer.equals("15")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 1 && selectedAnswer.equals("21")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 2 && selectedAnswer.equals("36")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 3 && selectedAnswer.equals("12")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 4 && selectedAnswer.equals("21")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 5 && selectedAnswer.equals("15")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 6 && selectedAnswer.equals("33")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 7 && selectedAnswer.equals("39")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 8 && selectedAnswer.equals("21,27")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 9 && selectedAnswer.equals("6,12")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				}
			} else if (testNumber.equals("4")) {
				if (lessonsTestsCounter == 0 && selectedAnswer.equals("80")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 1 && selectedAnswer.equals("44,52")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 2 && selectedAnswer.equals("36,48")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 3 && selectedAnswer.equals("52")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 4 && selectedAnswer.equals("64")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 5 && selectedAnswer.equals("84")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 6 && selectedAnswer.equals("64")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 7 && selectedAnswer.equals("48")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 8 && selectedAnswer.equals("32")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 9 && selectedAnswer.equals("52")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				}
			}
		}else if (optionsActivitySelectedButton.equals("testsAddition")) {
			if (testNumber.equals("1")) {
				if (lessonsTestsCounter == 0 && selectedAnswer.equals("11")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 1 && selectedAnswer.equals("11")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 2 && selectedAnswer.equals("6")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 3 && selectedAnswer.equals("8")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 4 && selectedAnswer.equals("10")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 5 && selectedAnswer.equals("14")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 6 && selectedAnswer.equals("11")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 7 && selectedAnswer.equals("10")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 8 && selectedAnswer.equals("5")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 9 && selectedAnswer.equals("10")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				}
			} else if (testNumber.equals("2")) {
				if (lessonsTestsCounter == 0 && selectedAnswer.equals("29")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 1 && selectedAnswer.equals("28")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 2 && selectedAnswer.equals("26")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 3 && selectedAnswer.equals("28")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 4 && selectedAnswer.equals("10")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 5 && selectedAnswer.equals("24")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 6 && selectedAnswer.equals("38")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 7 && selectedAnswer.equals("41")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 8 && selectedAnswer.equals("31")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 9 && selectedAnswer.equals("47")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				}
			} else if (testNumber.equals("3")) {
				if (lessonsTestsCounter == 0 && selectedAnswer.equals("15")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 1 && selectedAnswer.equals("21")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 2 && selectedAnswer.equals("38")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 3 && selectedAnswer.equals("12")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 4 && selectedAnswer.equals("22")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 5 && selectedAnswer.equals("16")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 6 && selectedAnswer.equals("38")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 7 && selectedAnswer.equals("40")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 8 && selectedAnswer.equals("25")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 9 && selectedAnswer.equals("11")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				}
			} else if (testNumber.equals("4")) {
				if (lessonsTestsCounter == 0 && selectedAnswer.equals("70")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 1 && selectedAnswer.equals("66")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 2 && selectedAnswer.equals("36")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 3 && selectedAnswer.equals("51")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 4 && selectedAnswer.equals("62")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 5 && selectedAnswer.equals("82")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 6 && selectedAnswer.equals("88")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 7 && selectedAnswer.equals("58")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 8 && selectedAnswer.equals("30")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 9 && selectedAnswer.equals("52")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				}
			}
		} else if (optionsActivitySelectedButton.equals("testsSubtraction")) {
			if (testNumber.equals("1")) {
				if (lessonsTestsCounter == 0 && selectedAnswer.equals("5")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 1 && selectedAnswer.equals("7")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 2 && selectedAnswer.equals("2")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 3 && selectedAnswer.equals("10")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 4 && selectedAnswer.equals("1")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 5 && selectedAnswer.equals("1")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 6 && selectedAnswer.equals("4")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 7 && selectedAnswer.equals("11")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 8 && selectedAnswer.equals("14")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 9 && selectedAnswer.equals("2")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				}
			} else if (testNumber.equals("2")) {
				if (lessonsTestsCounter == 0 && selectedAnswer.equals("7")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 1 && selectedAnswer.equals("6")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 2 && selectedAnswer.equals("13")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 3 && selectedAnswer.equals("4")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 4 && selectedAnswer.equals("9")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 5 && selectedAnswer.equals("5")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 6 && selectedAnswer.equals("5")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 7 && selectedAnswer.equals("3")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 8 && selectedAnswer.equals("3")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 9 && selectedAnswer.equals("15")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				}
			} else if (testNumber.equals("3")) {
				if (lessonsTestsCounter == 0 && selectedAnswer.equals("4")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 1 && selectedAnswer.equals("7")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 2 && selectedAnswer.equals("6")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 3 && selectedAnswer.equals("1")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 4 && selectedAnswer.equals("9")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 5 && selectedAnswer.equals("11")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 6 && selectedAnswer.equals("4")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 7 && selectedAnswer.equals("3")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 8 && selectedAnswer.equals("0")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 9 && selectedAnswer.equals("4")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				}
			} else if (testNumber.equals("4")) {
				if (lessonsTestsCounter == 0 && selectedAnswer.equals("11")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 1 && selectedAnswer.equals("6")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 2 && selectedAnswer.equals("36")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 3 && selectedAnswer.equals("10")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 4 && selectedAnswer.equals("3")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 5 && selectedAnswer.equals("18")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 6 && selectedAnswer.equals("25")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 7 && selectedAnswer.equals("16")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 8 && selectedAnswer.equals("20")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 9 && selectedAnswer.equals("42")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				}
			}
		} else if (optionsActivitySelectedButton.equals("testsMultiplication")) {
			if (testNumber.equals("1")) {
				if (lessonsTestsCounter == 0 && selectedAnswer.equals("8")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 1 && selectedAnswer.equals("16")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 2 && selectedAnswer.equals("15")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 3 && selectedAnswer.equals("15")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 4 && selectedAnswer.equals("4")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 5 && selectedAnswer.equals("18")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 6 && selectedAnswer.equals("10")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 7 && selectedAnswer.equals("20")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 8 && selectedAnswer.equals("24")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 9 && selectedAnswer.equals("6")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				}
			} else if (testNumber.equals("2")) {
				if (lessonsTestsCounter == 0 && selectedAnswer.equals("12")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 1 && selectedAnswer.equals("30")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 2 && selectedAnswer.equals("35")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 3 && selectedAnswer.equals("24")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 4 && selectedAnswer.equals("44")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 5 && selectedAnswer.equals("4")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 6 && selectedAnswer.equals("30")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 7 && selectedAnswer.equals("18")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 8 && selectedAnswer.equals("49")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 9 && selectedAnswer.equals("70")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				}
			} else if (testNumber.equals("3")) {
				if (lessonsTestsCounter == 0 && selectedAnswer.equals("14")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 1 && selectedAnswer.equals("42")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 2 && selectedAnswer.equals("48")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 3 && selectedAnswer.equals("40")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 4 && selectedAnswer.equals("60")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 5 && selectedAnswer.equals("4")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 6 && selectedAnswer.equals("48")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 7 && selectedAnswer.equals("20")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 8 && selectedAnswer.equals("64")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 9 && selectedAnswer.equals("96")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				}
			} else if (testNumber.equals("4")) {
				if (lessonsTestsCounter == 0 && selectedAnswer.equals("27")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 1 && selectedAnswer.equals("72")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 2 && selectedAnswer.equals("80")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 3 && selectedAnswer.equals("60")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 4 && selectedAnswer.equals("91")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 5 && selectedAnswer.equals("16")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 6 && selectedAnswer.equals("30")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 7 && selectedAnswer.equals("39")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 8 && selectedAnswer.equals("110")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 9 && selectedAnswer.equals("150")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				}
			}
		} else if (optionsActivitySelectedButton.equals("testsDivision")) {
			if (testNumber.equals("1")) {
				if (lessonsTestsCounter == 0 && selectedAnswer.equals("1")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 1 && selectedAnswer.equals("5")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 2 && selectedAnswer.equals("2")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 3 && selectedAnswer.equals("1")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 4 && selectedAnswer.equals("2")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 5 && selectedAnswer.equals("2")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 6 && selectedAnswer.equals("6")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 7 && selectedAnswer.equals("1")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 8 && selectedAnswer.equals("4")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 9 && selectedAnswer.equals("5")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				}
			} else if (testNumber.equals("2")) {
				if (lessonsTestsCounter == 0 && selectedAnswer.equals("1")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 1 && selectedAnswer.equals("9")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 2 && selectedAnswer.equals("2")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 3 && selectedAnswer.equals("8")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 4 && selectedAnswer.equals("2")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 5 && selectedAnswer.equals("4")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 6 && selectedAnswer.equals("3")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 7 && selectedAnswer.equals("10")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 8 && selectedAnswer.equals("24")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 9 && selectedAnswer.equals("6")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				}
			} else if (testNumber.equals("3")) {
				if (lessonsTestsCounter == 0 && selectedAnswer.equals("5")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 1 && selectedAnswer.equals("21")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 2 && selectedAnswer.equals("2")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 3 && selectedAnswer.equals("19")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 4 && selectedAnswer.equals("6")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 5 && selectedAnswer.equals("10")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 6 && selectedAnswer.equals("8")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 7 && selectedAnswer.equals("25")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 8 && selectedAnswer.equals("3")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 9 && selectedAnswer.equals("14")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				}
			} else if (testNumber.equals("4")) {
				if (lessonsTestsCounter == 0 && selectedAnswer.equals("33")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 1 && selectedAnswer.equals("36")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 2 && selectedAnswer.equals("33")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 3 && selectedAnswer.equals("30")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 4 && selectedAnswer.equals("38")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 5 && selectedAnswer.equals("40")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 6 && selectedAnswer.equals("31")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 7 && selectedAnswer.equals("56")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 8 && selectedAnswer.equals("16 r2")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));
				} else if (lessonsTestsCounter == 9 && selectedAnswer.equals("34 r3")) {
					score++;
					Log.d("answer is:", "Correct " + String.valueOf(score));				}
			}
		}
		SharedPreferences.Editor skillsOfMDASEditor = skillsOfMDASSharedPreferences.edit();
		skillsOfMDASEditor.putString("score", Integer.toString(score));
		skillsOfMDASEditor.commit();
		Log.d("Score", Integer.toString(score));
	}

	class UpdateScore extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 */
		boolean failure = false;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(lessonsTestsActivity.this);
			pDialog.setMessage("Updating Score...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			// Check for success tag
			int success;

			final SharedPreferences skillsOfMDASSharedPreferences = getSharedPreferences("skillsOfMDASData",
					Context.MODE_PRIVATE);
			final String optionsActivitySelectedButton = skillsOfMDASSharedPreferences
					.getString("optionsActivitySelectedButton", "0");
			final String testNumber = skillsOfMDASSharedPreferences.getString("testNumber", "0");
			final String score = skillsOfMDASSharedPreferences.getString("score", null);
			final String userIDxx = skillsOfMDASSharedPreferences.getString("userID", null);

			try {
				// Building Parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("userID", userIDxx));
				params.add(new BasicNameValuePair("optionsActivitySelectedButton", optionsActivitySelectedButton));
				params.add(new BasicNameValuePair("testNumber", testNumber));
				params.add(new BasicNameValuePair("score", score));
				// skillsOfMDASEditor.putString("userID",
				// json.getString("userID"));
				Log.d("request!", "starting");

				// Posting user data to script
				JSONObject json = jsonParser.makeHttpRequest(LOGIN_URL, "POST", params);

				// full json response
				Log.d("Login attempt", json.toString());

				// json success element
				success = json.getInt(TAG_SUCCESS);
				if (success == 1) {
					Log.d("User Created!", json.toString());
					finish();
					return json.getString(TAG_MESSAGE);
				} else {
					Log.d("Login Failure!", json.getString(TAG_MESSAGE));
					return json.getString(TAG_MESSAGE);

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;

		}

		/**
		 * After completing background task Dismiss the progress dialog
		 **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once product deleted
			pDialog.dismiss();
			if (file_url != null) {
				Toast.makeText(lessonsTestsActivity.this, file_url, Toast.LENGTH_LONG).show();
			}
			final SharedPreferences skillsOfMDASSharedPreferences = getSharedPreferences("skillsOfMDASData",
					Context.MODE_PRIVATE);
			SharedPreferences.Editor skillsOfMDASEditor = skillsOfMDASSharedPreferences.edit();
			skillsOfMDASEditor.putString("userID", null);
			skillsOfMDASEditor.putString("userName", null);
			skillsOfMDASEditor.putString("firstName", null);
			skillsOfMDASEditor.putString("middleName", null);
			skillsOfMDASEditor.putString("lastName", null);
			skillsOfMDASEditor.putString("userLevel", null);
			skillsOfMDASEditor.putString("addition1", null);
			skillsOfMDASEditor.putString("addition2", null);
			skillsOfMDASEditor.putString("addition3", null);
			skillsOfMDASEditor.putString("addition4", null);
			skillsOfMDASEditor.putString("subtraction1", null);
			skillsOfMDASEditor.putString("subtraction2", null);
			skillsOfMDASEditor.putString("subtraction3", null);
			skillsOfMDASEditor.putString("subtraction4", null);
			skillsOfMDASEditor.putString("multiplication1", null);
			skillsOfMDASEditor.putString("multiplication2", null);
			skillsOfMDASEditor.putString("multiplication3", null);
			skillsOfMDASEditor.putString("multiplication4", null);
			skillsOfMDASEditor.putString("division1", null);
			skillsOfMDASEditor.putString("division2", null);
			skillsOfMDASEditor.putString("division3", null);
			skillsOfMDASEditor.putString("division4", null);
			skillsOfMDASEditor.putString("score", null);
			Intent mInHome = new Intent(lessonsTestsActivity.this, mainActivity.class);
			lessonsTestsActivity.this.startActivity(mInHome);
			lessonsTestsActivity.this.finish();
		}

	}
}
