package com.rl.calc

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import java.lang.NumberFormatException

class MainActivity() : AppCompatActivity() , View.OnClickListener,View.OnTouchListener{


    private var equalClicked = false
    private var dotUse = false
    private var pranthesisUsed = 0

    lateinit var buttonNumber0: Button
    lateinit var buttonNumber1: Button
    lateinit var buttonNumber2: Button
    lateinit var buttonNumber3: Button
    lateinit var buttonNumber4: Button
    lateinit var buttonNumber5: Button
    lateinit var buttonNumber6: Button
    lateinit var buttonNumber7: Button
    lateinit var buttonNumber8: Button
    lateinit var buttonNumber9: Button

    lateinit var buttonClear: Button
    lateinit var buttonParantheses: Button
    lateinit var buttonPercent: Button
    lateinit var buttonDivision: Button
    lateinit var buttonMultiplication: Button
    lateinit var buttonSubtraction: Button
    lateinit var buttonAddition: Button
    lateinit var buttonEqual: Button
    lateinit var buttonDot: Button

    lateinit var textViewInputNumbers: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initVariables()
        setonClickListeners()
        setonTouchClickListeners()


    }




    private fun initVariables() {

        /**
         *  Numbers Init
         *  */
        buttonNumber0 = findViewById(R.id.button_zero)
        buttonNumber1 = findViewById(R.id.button_one)
        buttonNumber2 = findViewById(R.id.button_two)
        buttonNumber3 = findViewById(R.id.button_three)
        buttonNumber4 = findViewById(R.id.button_four)
        buttonNumber5 = findViewById(R.id.button_five)
        buttonNumber6 = findViewById(R.id.button_six)
        buttonNumber7 = findViewById(R.id.button_seven)
        buttonNumber8 = findViewById(R.id.button_eight)
        buttonNumber9 = findViewById(R.id.button_nine)

        /**
         * Operators Init
         */

        buttonClear = findViewById(R.id.button_clear)
        buttonAddition = findViewById(R.id.button_addition)
        buttonSubtraction = findViewById(R.id.button_subtraction)
        buttonDivision = findViewById(R.id.button_division)
        buttonMultiplication = findViewById(R.id.button_multiplication)
        buttonPercent = findViewById(R.id.button_percent)
        buttonParantheses = findViewById(R.id.button_parentheses)
        buttonEqual = findViewById(R.id.button_equal)
        buttonDot = findViewById(R.id.button_dot)

        /**
         * TextView for Entering numbers
         */
        textViewInputNumbers = findViewById(R.id.textView_input_numbers)


    }

    private fun setonClickListeners() {

        buttonNumber0.setOnClickListener(this)
        buttonNumber1.setOnClickListener(this)
        buttonNumber2.setOnClickListener(this)
        buttonNumber3.setOnClickListener(this)
        buttonNumber4.setOnClickListener(this)
        buttonNumber5.setOnClickListener(this)
        buttonNumber6.setOnClickListener(this)
        buttonNumber7.setOnClickListener(this)
        buttonNumber8.setOnClickListener(this)
        buttonNumber9.setOnClickListener(this)

        buttonClear.setOnClickListener(this)
        buttonParantheses.setOnClickListener(this)
        buttonPercent.setOnClickListener(this)
        buttonDivision.setOnClickListener(this)
        buttonMultiplication.setOnClickListener(this)
        buttonSubtraction.setOnClickListener(this)
        buttonAddition.setOnClickListener(this)
        buttonEqual.setOnClickListener(this)
        buttonDot.setOnClickListener(this)





    }


    private fun setonTouchClickListeners() {
        buttonNumber0.setOnTouchListener(this)
        buttonNumber1.setOnTouchListener(this)
        buttonNumber2.setOnTouchListener(this)
        buttonNumber3.setOnTouchListener(this)
        buttonNumber4.setOnTouchListener(this)
        buttonNumber5.setOnTouchListener(this)
        buttonNumber6.setOnTouchListener(this)
        buttonNumber7.setOnTouchListener(this)
        buttonNumber8.setOnTouchListener(this)
        buttonNumber9.setOnTouchListener(this)

        buttonClear.setOnTouchListener(this)
        buttonParantheses.setOnTouchListener(this)
        buttonPercent.setOnTouchListener(this)
        buttonDivision.setOnTouchListener(this)
        buttonMultiplication.setOnTouchListener(this)
        buttonSubtraction.setOnTouchListener(this)
        buttonAddition.setOnTouchListener(this)
        buttonEqual.setOnTouchListener(this)
        buttonDot.setOnTouchListener(this)
    }



    override fun onClick(v: View?) {

        when(v?.id){

            R.id.button_zero -> if (addNumber("0")) equalClicked = false
            R.id.button_one ->  if(addNumber( "1")) equalClicked = false
            R.id.button_two -> if(addNumber("2")) equalClicked =false
            R.id.button_three -> if (addNumber("3")) equalClicked = false
            R.id.button_four -> if (addNumber("4")) equalClicked = false
            R.id.button_five -> if (addNumber("5")) equalClicked = false
            R.id.button_six -> if (addNumber("6")) equalClicked = false
            R.id.button_seven -> if (addNumber("7")) equalClicked = false
            R.id.button_eight -> if(addNumber("8")) equalClicked = false
            R.id.button_nine -> if (addNumber("9")) equalClicked = false
            R.id.button_addition -> if(addOperand("+")) equalClicked = false




        }







    }

    private fun addOperand(operand: String): Boolean {

        var done = false
        val operationLength = textViewInputNumbers!!.text.length

        if (operationLength > 0){

            val lastInput = textViewInputNumbers.text[operationLength-1].toString() + ""
            if(lastInput == "+" || lastInput =="-" || lastInput == "*" || lastInput == "%" ) {

                textViewInputNumbers!!.text = textViewInputNumbers!!.text.toString() + operand
                dotUse = false
                equalClicked = false
                done = true

            }

            else if (operand != "%"){

                textViewInputNumbers.text = textViewInputNumbers!!.text.toString() + operand
                dotUse = false
                equalClicked = false
                done = true
            }

        }else {

            Toast.makeText(applicationContext,
                "Wrong Format",
                Toast.LENGTH_SHORT
            ).show()
        }

        return done
    }

    private fun addNumber(num: String): Boolean {

        var done = false
        val operationLength = textViewInputNumbers!!.text.length

        if (operationLength > 0) {

         val lastChar = textViewInputNumbers!!.text[operationLength-1].toString() + ""

            val lastCharState = defineLastCharacter(lastChar)

            if (operationLength == 1 && lastCharState == IS_NUMBER && lastChar == "0") {
                textViewInputNumbers!!.text = num
                done = true
            } else if ( lastCharState == OPEN_PARAN ) {
                textViewInputNumbers!!.text = textViewInputNumbers!!.text.toString() + num
                done = true
            } else if (lastCharState == CLOSE_PARAN || lastChar == "%") {
                textViewInputNumbers!!.text = textViewInputNumbers!!.text.toString() + "x" + num
                done = true
            } else if (lastCharState == IS_NUMBER || lastCharState == OPERAND || lastCharState == DOT) {
                textViewInputNumbers!!.text = textViewInputNumbers!!.text.toString() + num
                done = true
            }
        } else {
            textViewInputNumbers!!.text = textViewInputNumbers!!.text.toString() + num
            done = true
        }
        return done


        }


    private fun defineLastCharacter(lastChar: String): Int {

        try {
            lastChar.toInt()
            return IS_NUMBER
        }catch (e: NumberFormatException){

        }



        if( lastChar =="+"||lastChar == "-"||lastChar == "x"||lastChar == "%") return  OPERAND

        if (lastChar == "(") return OPEN_PARAN

        if (lastChar == ")") return CLOSE_PARAN
        return if (lastChar ==".") DOT else -1


    }

    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {

        when (p1?.action) {

            MotionEvent.ACTION_DOWN -> {
                p0?.background?.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
                p0?.invalidate()

            }
            MotionEvent.ACTION_UP -> {
                p0?.background?.clearColorFilter()
                p0?.invalidate()
            }

        }

        return false

    }

     companion object{

        private const val IS_NUMBER =0
        private const val OPERAND = 1
        private const val OPEN_PARAN =2
        private const val CLOSE_PARAN = 3
        private const val DOT = 4
    }



}

