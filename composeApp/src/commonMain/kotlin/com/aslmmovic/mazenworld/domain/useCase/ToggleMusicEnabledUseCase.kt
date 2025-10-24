import com.aslmmovic.mazenworld.domain.respository.GameRepository
import kotlinx.coroutines.flow.first // Crucial for getting the current value from the Flow

class ToggleMusicEnabledUseCase(
    private val gameRepository: GameRepository,
) {
    suspend operator fun invoke() {
        val currentProfile = gameRepository.getUserProfile().first()
        gameRepository.saveMusicState(!currentProfile.musicEnabled)
    }
}