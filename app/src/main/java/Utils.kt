import android.annotation.SuppressLint
import java.text.SimpleDateFormat

object Utils {
    private var timeNow : Long = System.currentTimeMillis()

    fun setTimeNow(selectedTime: Long) {
        timeNow = selectedTime
    }

    @SuppressLint("SimpleDateFormat")
    fun getTimeNow() : String {
        val time = SimpleDateFormat("HH:mm:ss").format(timeNow)
        return time.toString()
    }
}