package taskexample.task.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import taskexample.task.R

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val enterNameFragment = EnterNameFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Вызываем запуск фрагмента
        launchFirstFragment(enterNameFragment)
    }

    //Метод запуска первого фрагмента
    private fun launchFirstFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.home_fragment_container, fragment)
            addToBackStack(null)
                .commit()
        }
    }
}