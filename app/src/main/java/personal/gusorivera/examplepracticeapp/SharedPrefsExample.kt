package personal.gusorivera.examplepracticeapp

import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_shared_prefs_example.*

class SharedPrefsExample : AppCompatActivity() {

    val PREFS_NAME = "myPrefs"
    var myPrefs: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_prefs_example)

        myPrefs = getSharedPreferences(PREFS_NAME, 0)
        var editor: SharedPreferences.Editor = myPrefs!!.edit()


saveButton.setOnClickListener {

    if (!TextUtils.isEmpty(editSomethingId.text)){
        var message = editSomethingId.text.toString()
        editor.putString("message", message)
        editor.apply()
    }else
    {
        Toast.makeText(this, "Please enter something", Toast.LENGTH_SHORT).show()
    }
}

        // Get data back
        var dataBack: SharedPreferences = getSharedPreferences(PREFS_NAME, 0)
        if (dataBack.contains("message")){
            var myMessage = dataBack.getString("message","not found!")
            result.text = myMessage
        }
    }
}
