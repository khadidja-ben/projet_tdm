package com.example.projet_tdm.service


import android.annotation.SuppressLint
import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import androidx.work.impl.utils.futures.SettableFuture
import com.google.common.util.concurrent.ListenableFuture
import com.example.projet_tdm.entity.Advice
import com.example.projet_tdm.retrofit.RetrofitService
import com.example.projet_tdm.roomdao.RoomService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("RestrictedApi")
class SyncService(val ctx: Context, val workParamters: WorkerParameters):ListenableWorker(ctx, workParamters){

    lateinit var  future:SettableFuture<Result>



    override fun startWork(): ListenableFuture<Result> {

        future = SettableFuture.create()
        val advices = RoomService.appDataBase.getAdviceDao().getAdvicesToSynchronize()
        addAdvices(advices)
        return future
    }

    fun addAdvices(advices:List<Advice>) {
    val result = RetrofitService.endpoint.addAdvices(advices)
    result.enqueue(object: Callback<String> {

        override fun onFailure(call: Call<String>?, t: Throwable?) {
          future.set(Result.retry())
        }

        override fun onResponse(call: Call<String>?, response: Response<String>?) {

            if(response?.isSuccessful!!) {
                for (item in advices) {
                    item.isSynchronized = 1
                }
               RoomService.appDataBase.getAdviceDao().updateAdvice(advices)
               future.set(Result.success())
            }
            else
            {
               future.set(Result.retry())
            }
        }

    })
}


}
