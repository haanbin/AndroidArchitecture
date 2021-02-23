package com.example.androidarchitecture

import android.os.SystemClock
import android.util.Log.e
import com.example.androidarchitecture.data.entities.Log
import com.example.androidarchitecture.data.source.AppRepository
import com.example.androidarchitecture.data.source.local.AppDatabase
import com.example.androidarchitecture.data.source.local.LocalDataSource
import com.example.androidarchitecture.data.source.local.LocalDataSourceImpl
import com.example.androidarchitecture.data.source.remote.RemoteDataSource
import com.example.androidarchitecture.ui.random.RandomViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import kotlinx.coroutines.runBlocking
import org.junit.*
import javax.inject.Inject

@HiltAndroidTest
class AppRepositoryTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var appRepository: AppRepository

    private val queryMap = HashMap<String, String>()
//    @Inject
//    lateinit var localDataSource: LocalDataSourceImpl
//    @Inject
//    lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun init() {
        hiltRule.inject()
        with(queryMap) {
            put("page", "1")
            put("results", "20")
            put("seed", "abc")
        }
    }

    @Test
    fun dbTest() = runBlocking {
        appRepository.addLog("TEST LOG")
        val log = appRepository.getLastLog()
        Assert.assertEquals(log?.msg, "TEST LOG")
    }

    @Test
    fun randomUserTest(){
        appRepository.getRandomUser(queryMap, "https://randomuser.me/api/")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                println(it.results)
            }, {
                println(it.message.toString())
            })
    }




}