package com.nicolasmilliard.playground.presenter

import com.google.common.truth.Truth.assertThat
import com.nicolasmilliard.playground.service.HomeService
import com.nicolasmilliard.playground.service.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomePresenterTest {

    lateinit var presenter: HomePresenter

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        presenter = HomePresenter(FakeHomeService())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
    }

    @Test
    fun start() = runBlocking {

        val modelLoading = presenter.models.poll()
        assertThat(modelLoading).isNotNull()
        assertThat(modelLoading!!.isLoading).isTrue()
        assertThat(modelLoading!!.data).isEmpty()

        delay(100)

        val model = presenter.models.poll()
        assertThat(model).isNotNull()
        assertThat(model!!.isLoading).isFalse()
        assertThat(model!!.data).isNotEmpty()
        assertThat(model!!.data[0]).isEqualTo(TEST_DATA)
    }

    class FakeHomeService : HomeService {
        override suspend fun loadData(): List<Item> {
            delay(50)
            return listOf(TEST_DATA)
        }
    }

    companion object {
        val TEST_DATA = Item("id", "name", "description", "image")
    }
}