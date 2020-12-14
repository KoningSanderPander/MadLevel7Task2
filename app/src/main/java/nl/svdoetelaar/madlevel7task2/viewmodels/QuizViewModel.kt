package nl.svdoetelaar.madlevel7task2.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nl.svdoetelaar.madlevel7task2.models.Quiz
import nl.svdoetelaar.madlevel7task2.repositories.QuizRepository

class QuizViewModel(application: Application) : AndroidViewModel(application) {
    private val TAG = "FIRESTORE"
    private val quizRepository = QuizRepository()

    val quiz = quizRepository.quiz
    val createSuccess = quizRepository.createSuccess

    fun getQuiz() {
        viewModelScope.launch {
            try {
                quizRepository.getQuiz()
            } catch (e: QuizRepository.QuizRetrievalError) {
                val errorMsg = "Something went wrong while saving profile"
                Log.e(TAG, e.message ?: errorMsg)
            }
        }
    }

    fun createQuiz(quiz: Quiz) {
        viewModelScope.launch {
            try {
                quizRepository.createQuiz(quiz)
            } catch (e: QuizRepository.QuizSaveError) {
                val errorMsg = "Something went wrong while saving profile"
                Log.e(TAG, e.message ?: errorMsg)
            }
        }
    }

}
