package com.example.recyclertest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.recyclertest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var bindingMain: ActivityMainBinding? = null
    private val binding get() = bindingMain!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.commit {
            add(R.id.fragmentContainer, ListFragment())
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bindingMain = null
    }
}
