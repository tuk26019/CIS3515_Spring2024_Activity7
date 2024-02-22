package edu.temple.activities

import android.app.Instrumentation.ActivityResult
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts

class DisplayActivity : AppCompatActivity() {

    private lateinit var lyricsDisplayTextView: TextView
    private lateinit var textSizeSelectorButton: Button

    private val textSizeLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            result.data?.getIntExtra("selectedTextSize", -1)?.takeIf { it != -1 }?.let { textSize ->
                lyricsDisplayTextView.textSize = textSize.toFloat()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        lyricsDisplayTextView = findViewById(R.id.lyricsDisplayTextView)
        textSizeSelectorButton = findViewById(R.id.textSizeSelectorButton)

        // Step 1: Launch TextSizeActivity when textSizeSelectorButton is clicked
        textSizeSelectorButton.setOnClickListener {
            launchTextSizeActivity()
        }
    }

    private fun launchTextSizeActivity() {
        val intent = Intent(this, TextSizeActivity::class.java)
        textSizeLauncher.launch(intent)
    }
}
