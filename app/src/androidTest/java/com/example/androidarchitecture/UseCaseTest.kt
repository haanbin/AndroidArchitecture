package com.example.androidarchitecture

import com.test.domain.GetMovieUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.*
import javax.inject.Inject

@HiltAndroidTest
class UseCaseTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var getMovieUseCase: GetMovieUseCase

    private val queryMap = HashMap<String, String>().apply {
        put("display", "10")
        put("start", "1")
        put("query", "기생충")
    }
    private val headerMap = HashMap<String, String>().apply {
        put("X-Naver-Client-Id", BuildConfig.clientId)
        put("X-Naver-Client-Secret", BuildConfig.clientSecret)
    }

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun getMovieUseCaseTest() = runBlocking {
        val response = getMovieUseCase(headerMap, queryMap)
            Assert.assertEquals(true, response.isSuccessful)
    }
}

