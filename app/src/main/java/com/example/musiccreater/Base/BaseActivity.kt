package com.example.musiccreater.Base

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType
import kotlin.jvm.java
import kotlin.jvm.javaClass


abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    private var _binding: VB? = null
    val binding: VB
        get() = _binding
            ?: throw kotlin.IllegalStateException("Binding is only valid between onCreate and onDestroy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val superclass = javaClass.genericSuperclass
        val clazz = (superclass as ParameterizedType).actualTypeArguments[0] as Class<*>
        val method = clazz.getMethod(
            "inflate", LayoutInflater::class.java
        )
        @Suppress("UNCHECKED_CAST") _binding = method.invoke(null, layoutInflater) as VB
        setContentView(binding.root)
        init()
    }

    fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    abstract fun init()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }

}
