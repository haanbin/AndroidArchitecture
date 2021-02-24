package com.example.androidarchitecture

import com.example.androidarchitecture.data.source.AppRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Assert.assertNotNull(it)
            }, {
                Assert.assertNotNull(it.message)
            })
    }
}