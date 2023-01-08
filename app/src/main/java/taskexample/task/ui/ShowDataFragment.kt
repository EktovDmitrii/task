package taskexample.task.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import taskexample.task.databinding.FragmentShowDataBinding

@AndroidEntryPoint
class ShowDataFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

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
        //подписка на LiveData
        setObservers()
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

    companion object {

        fun newInstance(): Fragment{
            return  ShowDataFragment()
        }
    }
}