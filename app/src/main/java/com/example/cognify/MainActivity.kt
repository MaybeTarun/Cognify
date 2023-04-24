package com.example.cognify

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.cognify.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val signup = findViewById<TextView>(R.id.signup)
        val login = findViewById<TextView>(R.id.login)
        val signuplayout = findViewById<LinearLayout>(R.id.signuplayout)
        val loginlayout = findViewById<LinearLayout>(R.id.loginlayout)
        val loginbutton = findViewById<Button>(R.id.loginbutton)
        val logingoogle = findViewById<ImageView>(R.id.google)
        val loginfacebook = findViewById<ImageView>(R.id.facebook)
        val oro = findViewById<TextView>(R.id.oro)
        val alredy = findViewById<TextView>(R.id.alredy)
        val sss = findViewById<LinearLayout>(R.id.sss)
        val usernameEditText = findViewById<EditText>(R.id.username)
        val passwordEditText = findViewById<EditText>(R.id.password)
        val username1EditText = findViewById<EditText>(R.id.username1)
        val emailEditText = findViewById<EditText>(R.id.email)
        val ageEditText = findViewById<EditText>(R.id.age)
        val password1EditText = findViewById<EditText>(R.id.password1)
        val confirmpasswordEditText = findViewById<EditText>(R.id.confirmpassword)
        var boollog = false
        firebaseAuth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        logingoogle.setOnClickListener {
            signInGoogle()
        }

        signup.setOnClickListener {
            signup.background = resources.getDrawable(R.drawable.switch_trcks, null)
            signup.setTextColor(resources.getColor(R.color.white, null))
            login.background = null
            signuplayout.visibility = View.VISIBLE
            loginlayout.visibility = View.GONE
            oro.visibility = View.GONE
            sss.visibility = View.GONE
            alredy.visibility = View.VISIBLE
            login.setTextColor(resources.getColor(R.color.pastelgreen, null))
            loginbutton.text = "Sign Up"
            boollog = true
        }
        login.setOnClickListener {
            signup.background = null
            signup.setTextColor(resources.getColor(R.color.pastelgreen, null))
            login.background = resources.getDrawable(R.drawable.switch_trcks, null)
            signuplayout.visibility = View.GONE
            loginlayout.visibility = View.VISIBLE
//            oro.visibility = View.VISIBLE
            sss.visibility = View.VISIBLE
            alredy.visibility = View.GONE
            login.setTextColor(resources.getColor(R.color.white, null))
            loginbutton.text = "Log In"
            boollog = false
        }

        binding.loginbutton.setOnClickListener {
            if (boollog) {
                // signup
                val username1 = username1EditText.text.toString()
                val email = emailEditText.text.toString()
                val age = ageEditText.text.toString()
                val password1 = password1EditText.text.toString()
                val confirmpassword = confirmpasswordEditText.text.toString()
                if (username1.isNotEmpty() && email.isNotEmpty() && age.isNotEmpty() && password1.isNotEmpty() && confirmpassword.isNotEmpty()) {
                    if (password1 == confirmpassword) {
                        firebaseAuth.createUserWithEmailAndPassword(email, password1).addOnCompleteListener{
                            if (it.isSuccessful) {
                                Toast.makeText(this@MainActivity, "Account Created", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, MainActivity2::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(this@MainActivity, "An error occured, please try again", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        Toast.makeText(this@MainActivity, "Passwords doesn't match", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Empty Fields are not Allowed", Toast.LENGTH_SHORT).show()
                }
            } else {
                // login
                val username = usernameEditText.text.toString()
                val password = passwordEditText.text.toString()
                if (username.isNotEmpty() && password.isNotEmpty()) {
                    firebaseAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener{
                        if (it.isSuccessful) {
                            Toast.makeText(this@MainActivity, "Welcome", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, MainActivity2::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this@MainActivity, "An error occured, please try again", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Empty Fields are not Allowed", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
    private fun signInGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                    handleResults(task)
                }
    }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful) {
            val account : GoogleSignInAccount? = task.result
            if (account != null) {
                updateUI(account)
            }
        } else {
            Toast.makeText(this@MainActivity, "An error occured, please try again", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val cred = GoogleAuthProvider.getCredential(account.idToken, null)
        firebaseAuth.signInWithCredential(cred).addOnCompleteListener {
            if (it.isSuccessful) {
                val intent = Intent(this, MainActivity2::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this@MainActivity, "An error occured, please try again", Toast.LENGTH_SHORT).show()
            }
        }
    }


}