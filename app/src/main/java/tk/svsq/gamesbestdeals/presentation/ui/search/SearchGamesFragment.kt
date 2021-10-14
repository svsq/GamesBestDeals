package tk.svsq.gamesbestdeals.presentation.ui.search

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import tk.svsq.gamesbestdeals.R
import tk.svsq.gamesbestdeals.data.common.Status
import tk.svsq.gamesbestdeals.databinding.FragmentSearchGamesBinding
import tk.svsq.gamesbestdeals.presentation.base.BaseFragment
import tk.svsq.gamesbestdeals.presentation.common.viewBinding
import tk.svsq.gamesbestdeals.presentation.utils.textChanges

@AndroidEntryPoint
class SearchGamesFragment : BaseFragment(R.layout.fragment_search_games) {

    private val binding by viewBinding(FragmentSearchGamesBinding::bind)
    private val viewModel: SearchGamesViewModel by viewModels()

    private var adapter: SearchGamesAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = SearchGamesAdapter()
        initRecyclerView()
        initListeners()
        observeGames()
        searchExercises()
    }

    private fun searchExercises() {
        binding.etSearch
            .textChanges()
            .debounce(1000)
            .onEach { query ->
                query?.let {
                    if (it.trim().length > 2) {
                        viewModel.searchGames(
                            query = binding.etSearch.text.toString(),
                            steamAppID = null,
                            limit = 60,
                            exact = false
                        )
                    }
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun initRecyclerView() {
        with(binding.rvSearch) {
            layoutManager = LinearLayoutManager(context)
            adapter = this@SearchGamesFragment.adapter
            setHasFixedSize(true)
        }
    }

    private fun initListeners() {

    }

    private fun observeGames() {
        lifecycleScope.launchWhenStarted {
            viewModel.games.collect {
                when(it.status) {
                    Status.SUCCESS -> {
                        adapter?.submitList(it.data)
                    }
                    Status.ERROR -> Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
                    Status.LOADING -> Toast.makeText(requireContext(), "loading", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}