package taskexample.task.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import taskexample.task.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Вызываем запуск фрагмента
        launchFirstFragment()
    }

    //Метод запуска первого фрагмента
    private fun launchFirstFragment() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.home_fragment_container, EnterNameFragment.newInstance())
            addToBackStack(null)
                .commit()
        }
    }
}