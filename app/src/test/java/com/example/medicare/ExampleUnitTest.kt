package com.example.medicare

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        findViewById<Button>(R.id.btnLogin).setOnClickListener {
            // Login screen - coming soon
        }

        findViewById<Button>(R.id.btnSignup).setOnClickListener {
            // Signup screen - coming soon
        }
    }
}