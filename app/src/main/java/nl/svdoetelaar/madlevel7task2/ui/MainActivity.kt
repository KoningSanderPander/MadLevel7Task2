package nl.svdoetelaar.madlevel7task2.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import nl.svdoetelaar.madlevel7task2.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))


    }
}