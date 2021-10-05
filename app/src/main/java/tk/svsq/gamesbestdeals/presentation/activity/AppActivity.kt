package tk.svsq.gamesbestdeals.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import tk.svsq.gamesbestdeals.R
import tk.svsq.gamesbestdeals.databinding.ActivityAppBinding

@AndroidEntryPoint
class AppActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAppBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        findNavController(R.id.nav_host_fragment).apply {
            binding.bottomNavigationView.setupWithNavController(this)
        }
    }
}