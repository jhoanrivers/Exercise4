package com.example.exercise4

import com.example.exercise4.data.local.model.EmployeeEntity
import com.example.exercise4.data.local.model.SalaryEntity
import com.example.exercise4.data.local.repository.EmployeeRepository
import com.example.exercise4.data.local.repository.EmployeeRepositoryImpl
import io.realm.Realm
import io.realm.RealmQuery
import io.realm.RealmResults
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {


    @Mock
    lateinit var mockRealm: Realm

    lateinit var employeeRepository: EmployeeRepository


    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        employeeRepository = EmployeeRepositoryImpl()
    }

    @After
    fun tearDown() {
        mockRealm.close()
    }


    @Test
    fun addition_isCorrect() {

        val dummyEmployee = EmployeeEntity("jhoan", "jhoan@gmail.com")




    }
}