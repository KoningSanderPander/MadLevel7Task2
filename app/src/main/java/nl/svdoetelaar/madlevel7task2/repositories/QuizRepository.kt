package nl.svdoetelaar.madlevel7task2.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withTimeout
import nl.svdoetelaar.madlevel7task2.models.Question
import nl.svdoetelaar.madlevel7task2.models.Quiz

class QuizRepository {
    private var firestore = FirebaseFirestore.getInstance()
    private var document = firestore.collection("quizzes2").document("quiz")

    private val _quiz: MutableLiveData<Quiz> = MutableLiveData()
    val quiz: LiveData<Quiz> get() = _quiz

    suspend fun getQuiz() {
        try {
            withTimeout(5_000L) {
                val data = document
                    .get()
                    .await()

                @Suppress("UNCHECKED_CAST")
                val dataMap = data.get("questions") as List<HashMap<String, *>>
                val questions = mutableListOf<Question>()

                for (dataOfQuestion in dataMap) {
                    @Suppress("UNCHECKED_CAST")
                    questions.add(
                        Question(
                            dataOfQuestion["question"].toString(),
                            dataOfQuestion["answers"] as List<String>,
                            dataOfQuestion["correctAnswer"].toString(),
                        )
                    )
                }
                _quiz.value = Quiz(questions)
            }
        } catch (e: Exception) {
            throw QuizRetrievalError("Retrieval-firebase-task was unsuccessful")
        }
    }

    private val _createSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val createSuccess: LiveData<Boolean> get() = _createSuccess

    suspend fun createQuiz(quiz: Quiz) {
        try {
            withTimeout(5_000L) {
                document.set(quiz).await()

                _createSuccess.value = true
            }
        } catch (e: Exception) {
            throw QuizSaveError(e.message.toString(), e)
        }
    }


    class QuizRetrievalError(s: String) : Exception(s)
    class QuizSaveError(s: String, cause: Throwable) : Throwable(s, cause)
}