import android.annotation.SuppressLint
import android.content.Context
import androidx.work.ListenableWorker.Result.success
import androidx.work.Worker
import androidx.work.WorkerParameters

class WorkerM(context: Context, workParams : WorkerParameters) : Worker(context, workParams) {

    @SuppressLint("SimpleDateFormat")
    override fun doWork(): Result {
        val timeNow = System.currentTimeMillis()
        Utils.setTimeNow(timeNow)
     return success()
    }
}