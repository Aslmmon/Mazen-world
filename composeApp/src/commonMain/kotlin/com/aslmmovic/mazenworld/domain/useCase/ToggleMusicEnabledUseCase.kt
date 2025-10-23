import com.aslmmovic.mazenworld.data.source.PreferencesService // Inject the service for direct writing
import com.aslmmovic.mazenworld.domain.respository.GameRepository
import kotlinx.coroutines.flow.first // Crucial for getting the current value from the Flow

class ToggleMusicEnabledUseCase(
    private val gameRepository: GameRepository,
    // Inject the service to write the single, specific property
    private val preferencesService: PreferencesService
) {
    suspend operator fun invoke() {
        // 1. Load current state by collecting the first (most recent) value from the Flow.
        //    This uses the asynchronous architecture correctly.
        val currentProfile = gameRepository.getUserProfile().first()

        // 2. Toggle the value.
        val newState = !currentProfile.musicEnabled

        // 3. Save the specific property directly via the PreferencesService.
        //    This avoids creating and saving an entire UserProfile object.
        preferencesService.setMusicEnabled(newState)

        // Note: The UI updates automatically because the PreferencesService's
        // Flow will emit the new value, which the HomeViewModel is observing.
    }
}