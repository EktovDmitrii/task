package taskexample.task.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import taskexample.task.databinding.FragmentShowDataBinding

class ShowDataFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    //Инициальзируем Binding и обрабатываем исключение
    private var _binding: FragmentShowDataBinding? = null
    private val binding: FragmentShowDataBinding
        get() = _binding ?: throw RuntimeException("FragmentShowDataBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //инфлэйтим наш лэйаут
        _binding = FragmentShowDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val serName = getSerName()
        val name = getName()
        //подписка на LiveData
        setObservers()
        viewModel.updateName(name)
        viewModel.updateSerName(serName)
    }

    private fun setObservers() {
        viewModel.nameLiveData.observe(viewLifecycleOwner) {
            binding.tvName.text = it
        }
        viewModel.serNameLiveData.observe(viewLifecycleOwner) {
            binding.tvSerName.text = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //Получаем аргументы из первого фрагмента
    private fun getSerName(): String {
        return requireArguments().getString(SER_NAME, NO_SER_NAME)
    }

    private fun getName(): String {
        return requireArguments().getString(NAME, NO_NAME)
    }

    companion object {
        private const val SER_NAME = "serName"
        private const val NO_SER_NAME: String = ""
        private const val NAME = "name"
        private const val NO_NAME: String = ""

        //Указываем, какие аргумаенты необходимо передать во второй фрагмент
        fun newInstance(name: String, serName: String): Fragment {
            return ShowDataFragment().apply {
                arguments = Bundle().apply {
                    putString(SER_NAME, serName)
                }.apply {
                    putString(NAME, name)
                }
            }
        }
    }
}