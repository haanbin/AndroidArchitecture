package com.example.androidarchitecture

import com.example.androidarchitecture.data.api.RetrofitService
import com.example.androidarchitecture.data.source.remote.RemoteDataSource
import com.example.androidarchitecture.data.source.remote.RemoteDataSourceImpl
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach

class RemoteDataSourceTest {

    private val retrofitService: RetrofitService = mockk(relaxed = true)
    private lateinit var remoteDataSource: RemoteDataSource

    @BeforeEach
    fun setup() {
        remoteDataSource = RemoteDataSourceImpl(retrofitService)
    }

}
