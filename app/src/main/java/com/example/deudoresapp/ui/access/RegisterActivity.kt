package com.example.deudoresapp.ui.access
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.example.deudoresapp.DeudoresApp
import com.example.deudoresapp.R
import com.example.deudoresapp.data.dao.UserDao
import com.example.deudoresapp.data.entities.User
import com.example.deudoresapp.databinding.ActivityRegisterBinding
import kotlinx.coroutines.delay
import java.util.regex.Pattern

private lateinit var registerBinding: ActivityRegisterBinding

class RegisterActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)

        registerBinding.buttonRegister.setOnClickListener {
            var usuario = registerBinding.UsuarioEt.text.toString()
            var correo = registerBinding.CorreoEt.text.toString()
            var password = registerBinding.contraEt.text.toString()
            var confir = registerBinding.confirEt.text.toString()

            if (usuario.isNotEmpty()) {
                registerBinding.UsuarioTextLayout.error = null
                if (validarEmail(correo)) {
                    registerBinding.CorreoLayout.error = null
                    if (password.isNotEmpty()) {
                        registerBinding.contraLayout.error = null
                        if (password.length >= 6) {
                            registerBinding.contraLayout.error = null
                            if (confir.isNotEmpty()) {
                                registerBinding.contraLayout.error = null
                                if (password == confir) {
                                    UserCreate(usuario,password,correo)

                                 registerBinding.confirLayout.error = null
                                } else {
                                    registerBinding.confirLayout.error = getString(R.string.no_igua_contra)
                                }

                            } else {
                                registerBinding.confirLayout.error = getString(R.string.campo)
                            }
                        } else {
                            registerBinding.contraLayout.error = getString(R.string.contra_6_Dig)
                        }

                    } else {

                        registerBinding.contraLayout.error = getString(R.string.Error_message)
                    }
                } else {
                    registerBinding.CorreoLayout.error = getString(R.string.error_email)
                }
            } else {
                registerBinding.UsuarioTextLayout.error = getString(R.string.campo)

            }


        }
    }

    private fun envioLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    private fun validarEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
                return pattern.matcher(email).matches()
    }
    private fun UserCreate(name: String,password: String, email:String ){
        val usuario= User(id = name, password = password, email = email )
        val UserDAO: UserDao= DeudoresApp.database2.UserDao()
        UserDAO.createUser(usuario)
        envioLogin()


    }




}