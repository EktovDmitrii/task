package taskexample.task.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import taskexample.task.R
import taskexample.task.databinding.FragmentEnterNameBinding

@AndroidEntryPoint
class EnterNameFragment : Fragment() {

    //ленивая инициализация viewModel
    private val viewModel: MainViewModel by activityViewModels()

    //Инициальзируем Binding и обрабатываем исключение
    private var _binding: FragmentEnterNameBinding? = null
    private val binding: FragmentEnterNameBinding
        get() = _binding ?: throw RuntimeException("FragmentEnterNameBinding is null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //инфлэйтим наш лэйаут
        _binding = FragmentEnterNameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //ставим слушатель клика на кнопку
        binding.btnSaveName.setOnClickListener {
            viewModel.updateName(binding.etName.text.toString())
            viewModel.updateSerName(binding.etSerName.text.toString())
            //Проверка на ввод данных
            if (binding.etName.text.isNullOrBlank() || binding.etSerName.text.isNullOrBlank()) {
                Toast.makeText(requireContext(), EMPTY_DATA, Toast.LENGTH_SHORT).show()
            } else {
                //запускаем фрагмент с данными
                launchShowDataFragment()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // присваеваем значение Null, чтобы избежать утечек памяти
        _binding = null
    }

    private fun launchShowDataFragment() {
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.home_fragment_container, ShowDataFragment.newInstance())
            addToBackStack(null)
                .commit()
        }
    }

    companion object {

        private const val EMPTY_DATA = "please check your information again"
        fun newInstance(): Fragment {
            return EnterNameFragment()
        }
    }
}