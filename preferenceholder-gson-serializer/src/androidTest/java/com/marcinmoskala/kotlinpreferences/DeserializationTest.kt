package com.marcinmoskala.kotlinpreferences

import android.support.test.runner.AndroidJUnit4
import com.google.gson.Gson
import com.marcinmoskala.kotlinpreferences.gson.GsonSerializer
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DeserializationTest : GsonBaseTest() {

    data class User(var name: String, var age: Int = 0)

    @Test
    fun listDeserializationTest() {
        val json = "[{\"age\"=\"0\", \"name\"=\"Name0\"}, {\"age\"=\"1\", \"name\"=\"Name1\"}, {\"age\"=\"2\", \"name\"=\"Name2\"}]"
        val users = GsonSerializer(Gson()).deserialize(json, object : TypeToken<List<User>>() {}.type)
        val expected = List(3) { i -> User("Name$i", i) }
        Assert.assertEquals(expected, users)
    }
}
