package app.aoyagi.makkan.sosuu

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val QUESTION_COUNT: Int = 10
    }

    var random: Random = Random()
    val questions: IntArray = IntArray(QUESTION_COUNT)
    var point: Int = 0
    var answerCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in 0 until QUESTION_COUNT) {
            val number = random.nextInt(1000)
            Log.d("Number", number.toString())
            questions[i] = number
        }

        point = 0
        answerCount = 0

        numberText.text = questions[answerCount].toString() + ""
        numberText.setTextColor(Color.BLACK)

    }


    fun maru(v: View) {

        when (primeDecision(questions[answerCount])) {

            true -> {
                point++
                Toast.makeText(this, "正解", Toast.LENGTH_SHORT).show()
            }
            false -> {
                Toast.makeText(this, "不正解", Toast.LENGTH_SHORT).show()
            }
        }
        answerCount++
        continueDecision(answerCount)

    }

    fun batu(v: View) {

        when (primeDecision(questions[answerCount])) {

            false -> {
                point++
                Toast.makeText(this, "正解", Toast.LENGTH_SHORT).show()
            }
            true -> {
                Toast.makeText(this, "不正解", Toast.LENGTH_SHORT).show()
            }
        }
        answerCount++
        continueDecision(answerCount)

    }

    private fun primeDecision(num: Int): Boolean {

        for (i in 2 until num) {
            if (num % i == 0) {
                return false
                break
            }
        }
        return true

    }

    private fun continueDecision(num: Int) {
        if (num == QUESTION_COUNT) {
            numberText.text = point.toString() + "点"

            point = 0
            answerCount = 0
        } else {
            numberText.text = questions[answerCount].toString() + ""
            numberText.setTextColor(Color.BLACK)
        }
    }
}
