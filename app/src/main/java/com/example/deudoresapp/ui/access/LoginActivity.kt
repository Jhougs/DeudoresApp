package com.example.deudoresapp.ui.access

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.deudoresapp.DeudoresApp
import com.example.deudoresapp.MainActivity
import com.example.deudoresapp.R
import com.example.deudoresapp.data.dao.UserDao
import com.example.deudoresapp.data.entities.User
import com.example.deudoresapp.databinding.ActivityLoginBinding
import java.util.regex.Pattern

private lateinit var loginBinding: ActivityLoginBinding
private var mail= false
private var pass= false

class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("metodo", "onCreate")
        setContentView(R.layout.activity_login)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        loginBinding.iniciosesButton.setOnClickListener {
            val email = loginBinding.correoEditText.text.toString()
            var contra = loginBinding.contraEditText.text.toString()
            var data = intent.extras
            var confircontra = data?.getString("Contra")
            var confircorr = data?.getString("email")

            variables(email,contra)




            if (email.isNotEmpty()) {
                if (validarEmail(email)) {
                    loginBinding.correoTextlayout.error = null
                    if (contra.isNotEmpty()) {
                        loginBinding.contraTextlayout.error = null
                        if ( mail== true || pass== true)
                             {
                                 inicioSesion()



                        } else {
                            Toast.makeText(this, R.string.error_register, Toast.LENGTH_LONG).show()
                        }
                    } else {

                        loginBinding.contraTextlayout.error = getString(R.string.Error_message)
                    }

                } else {
                    loginBinding.correoTextlayout.error = getString(R.string.error_email)
                }
            } else {
                loginBinding.correoTextlayout.error = getString(R.string.Email_required)
            }



        }


        loginBinding.RegisterTextview.setOnClickListener {
            var intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)






        }

    }

    private fun variables(email: String, contra: String) {
        val UserDAO: UserDao= DeudoresApp.database2.UserDao()
        val listEmail: MutableList<String> = UserDAO.getUserName()
        val listPassword: MutableList<String> =  UserDAO.getUserPassWord()
        for (item in listEmail){
            if(item == email) {
                for (item2 in listPassword){
                    if(item2==contra){
                        mail= true
                        pass= true


                    }
                }
            }

        }

    }

    private fun inicioSesion() {

        val intent = Intent(this, MainActivity::class.java)
        //intent.putExtra("email", email)
        //intent.putExtra("contraseña", contra)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    private fun validarEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }


}