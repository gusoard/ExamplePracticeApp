package personal.gusorivera.examplepracticeapp.VolleyJsonExample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import personal.gusorivera.examplepracticeapp.R
import java.io.Console

class VolleyJsonExample : AppCompatActivity() {

    var volleyRequest: RequestQueue? = null
    val stringLink = "http://magadistudio.com/complete-android-developer-course-source-files/string.html"
    val arrayLink = "https://jsonplaceholder.typicode.com/posts"
    val objectLink = "https://jsonplaceholder.typicode.com/posts/1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volley_json_example)

        volleyRequest = Volley.newRequestQueue(this)
        getString(stringLink)
        getArray(arrayLink)
        getObject(objectLink)
    }

    private fun getString(Url: String) {
        val stringReq = StringRequest(Request.Method.GET ,
                Url ,
                Response.Listener {
                    // The response expects a string
                    response: String? ->
                    try {
                        Log.d("Response" , response)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                } ,
                Response.ErrorListener { error: VolleyError? ->
                    try {
                        Log.d("Error" , error.toString())
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
        )
        volleyRequest!!.add(stringReq)
    }

    fun getArray(Url: String) {
        val arrayRequest = JsonArrayRequest(Request.Method.GET ,
                Url ,
                null ,
                Response.Listener { response: JSONArray? ->
                    try {
                        Log.d("Array Response" , response.toString())

                        if (response != null) {
                            for (i in 0 until response.length()){
                                var jsonObject = response.getJSONObject(i)
                                Log.d("INICIO DE OBJETO", "Inicia el objeto")
                                jsonObject.keys().forEach {
                                    s ->
                                    Log.d("PROPIEDAD DE OBJETO", s)
                                }
                            }
                        }

                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                } ,
                Response.ErrorListener { error: VolleyError? ->
                    try {
                        Log.d("Error" , error.toString())
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
        )
        volleyRequest!!.add(arrayRequest)
    }

    fun getObject(Url: String) {
        val objectRequest = JsonObjectRequest(Request.Method.GET ,
                Url ,
                null ,
                Response.Listener { response: JSONObject? ->
                    try {
                        Log.d("Object Response" , response.toString())
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                } ,
                Response.ErrorListener { error: VolleyError? ->
                    try {
                        Log.d("Error" , error.toString())
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                })
        volleyRequest!!.add(objectRequest)
    }


}
